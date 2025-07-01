package com.gafapay.elasticsearch.api.eventlogs.service;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.request.DeleteEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.SaveEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.UpdateEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetAllEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.SaveEventLogsResponse;

public interface EventLogsRepository {
   SaveEventLogsResponse saveEventLogsData(SaveEventLogsRequest saveEventLogsRequest);

   CommonAPIDataResponse updateEventLogsData(UpdateEventLogsRequest updateEventLogsRequest);

   CommonAPIDataResponse deleteEventLogsData(DeleteEventLogsRequest deleteEventLogsRequest);

   GetEventLogsResponse getEventLogsData(GetEventLogsRequest getEventLogsRequest);

   GetAllEventLogsResponse getEventLogsData(GetAllEventLogsRequest getAllEventLogsRequest);
}
