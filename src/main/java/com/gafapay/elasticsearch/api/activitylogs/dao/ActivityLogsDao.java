package com.gafapay.elasticsearch.api.activitylogs.dao;

import com.gafapay.elasticsearch.api.activitylogs.model.ActivityLogs;
import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import java.util.List;

public interface ActivityLogsDao {
   List<ActivityLogs> getActivityLogs(GetAllActivityLogsDataRequest getAllActivityLogsDataRequest);

   void saveActivityLogs(ActivityLogs activityLogs);
}
