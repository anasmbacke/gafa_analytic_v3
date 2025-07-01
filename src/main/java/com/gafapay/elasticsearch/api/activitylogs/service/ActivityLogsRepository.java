package com.gafapay.elasticsearch.api.activitylogs.service;

import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import com.gafapay.elasticsearch.api.activitylogs.model.response.GetAllActivityLogsDataResponse;

public interface ActivityLogsRepository {
   GetAllActivityLogsDataResponse getAllActivityLogs(GetAllActivityLogsDataRequest getAllActivityLogsDataRequest);
}
