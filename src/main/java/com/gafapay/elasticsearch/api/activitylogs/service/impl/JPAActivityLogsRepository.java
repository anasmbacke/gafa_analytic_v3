package com.gafapay.elasticsearch.api.activitylogs.service.impl;

import com.gafapay.elasticsearch.api.activitylogs.dao.ActivityLogsDao;
import com.gafapay.elasticsearch.api.activitylogs.model.ActivityLogs;
import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import com.gafapay.elasticsearch.api.activitylogs.model.response.GetAllActivityLogsDataResponse;
import com.gafapay.elasticsearch.api.activitylogs.model.response.Logs;
import com.gafapay.elasticsearch.api.activitylogs.service.ActivityLogsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAActivityLogsRepository implements ActivityLogsRepository {
   @Autowired
   private ActivityLogsDao activityLogsDao;

   public JPAActivityLogsRepository() {
   }

   public GetAllActivityLogsDataResponse getAllActivityLogs(GetAllActivityLogsDataRequest getAllActivityLogsDataRequest) {
      GetAllActivityLogsDataResponse getAllActivityLogsDataResponse = new GetAllActivityLogsDataResponse();

      List<ActivityLogs> activityLogsList;
      try {
         activityLogsList = this.activityLogsDao.getActivityLogs(getAllActivityLogsDataRequest);
      } catch (Exception var7) {
         getAllActivityLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_ACTIVITY_NOT_FOUND");
         getAllActivityLogsDataResponse.setCheckValidationFailed(true);
         return getAllActivityLogsDataResponse;
      }

      if (!Objects.isNull(activityLogsList) && !activityLogsList.isEmpty()) {
         List<Logs> activeLogsArrayList = new ArrayList(activityLogsList.size());

         for(ActivityLogs activityLogs : activityLogsList) {
            activeLogsArrayList.add(Logs.setActivityLogs(activityLogs));
         }

         getAllActivityLogsDataResponse.setActivityLogs(activeLogsArrayList);
         return getAllActivityLogsDataResponse;
      } else {
         getAllActivityLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_ACTIVITY_NOT_FOUND");
         getAllActivityLogsDataResponse.setCheckValidationFailed(true);
         return getAllActivityLogsDataResponse;
      }
   }
}
