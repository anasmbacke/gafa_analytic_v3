package com.gafapay.elasticsearch.api.eventlogs.service.impl;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.eventlogs.dao.EventLogsQueryDao;
import com.gafapay.elasticsearch.api.eventlogs.model.EventLogs;
import com.gafapay.elasticsearch.api.eventlogs.model.request.DeleteEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.SaveEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.UpdateEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.response.EventLogsMaster;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetAllEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.SaveEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.service.EventLogsRepository;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class JPAEventLogsRepository implements EventLogsRepository {
   @Autowired
   private EventLogsQueryDao eventLogsQueryDao;

   public JPAEventLogsRepository() {
   }

   public SaveEventLogsResponse saveEventLogsData(SaveEventLogsRequest saveEventLogsRequest) {
      SaveEventLogsResponse saveEventLogsResponse = new SaveEventLogsResponse();
      EventLogs eventLogs = ((EventLogs.EventLogsBuilder)((EventLogs.EventLogsBuilder)EventLogs.builder().eventName(saveEventLogsRequest.getEventName()).eventType(saveEventLogsRequest.getEventType()).eventDate(saveEventLogsRequest.getEventDate()).oldValue(saveEventLogsRequest.getOldValue()).newValue(saveEventLogsRequest.getNewValue()).eventLogNumber(saveEventLogsRequest.getEventLogNumber()).objectType(saveEventLogsRequest.getObjectType()).ObjectTypeId(saveEventLogsRequest.getObjectTypeId()).companyId(saveEventLogsRequest.getCompany_id())).createdBy(saveEventLogsRequest.getToken_user_id())).build();
      this.eventLogsQueryDao.save(eventLogs);
      saveEventLogsResponse.setId(eventLogs.getId());
      saveEventLogsResponse.setMessage("EVENT_LOGS_SAVED_SUCCESSFULLY");
      return saveEventLogsResponse;
   }

   public CommonAPIDataResponse deleteEventLogsData(DeleteEventLogsRequest deleteEventLogsRequest) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      int isRemove = this.eventLogsQueryDao.deleteById(deleteEventLogsRequest.getId());
      if (isRemove == 0) {
         commonAPIDataResponse.setValidationMessage("EVENT_LOGS_NOT_FOUND");
         commonAPIDataResponse.setCheckValidationFailed(true);
         return commonAPIDataResponse;
      } else {
         commonAPIDataResponse.setMessage("EVENT_LOGS_DELETED_SUCCESSFULLY");
         return commonAPIDataResponse;
      }
   }

   public GetEventLogsResponse getEventLogsData(GetEventLogsRequest getEventLogsRequest) {
      GetEventLogsResponse getEventLogsResponse = new GetEventLogsResponse();
      EventLogs eventLogs = this.eventLogsQueryDao.findById(getEventLogsRequest.getId());
      if (Objects.isNull(eventLogs)) {
         getEventLogsResponse.setValidationMessage("EVENET_LOGS_NOT_FOUND");
         getEventLogsResponse.setCheckValidationFailed(true);
         return getEventLogsResponse;
      } else {
         getEventLogsResponse.setCheckForUnAuthorized(false);
         getEventLogsResponse.setMessage("EVENET_LOGS_DATA_FOUND");
         getEventLogsResponse.setEventLogsMaster(new EventLogsMaster(eventLogs));
         return getEventLogsResponse;
      }
   }

   public CommonAPIDataResponse updateEventLogsData(UpdateEventLogsRequest updateEventLogsRequest) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      String id = updateEventLogsRequest.getUpdateEvnentdataMap().containsKey("id") ? updateEventLogsRequest.getUpdateEvnentdataMap().get("id").toString() : null;
      Update updateDocument = new Update();

      for(String key : updateEventLogsRequest.getUpdateEvnentdataMap().keySet()) {
         if (!key.equalsIgnoreCase("id")) {
            updateDocument.set(key, updateEventLogsRequest.getUpdateEvnentdataMap().getOrDefault(key, (Object)null));
         }
      }

      updateDocument.set("updated_by", updateEventLogsRequest.getToken_user_id());
      updateDocument.set("updated_date", Instant.now().getEpochSecond());
      long updateStatus = this.eventLogsQueryDao.update(id, updateDocument);
      if (updateStatus == 0L) {
         commonAPIDataResponse.setCheckValidationFailed(true);
         commonAPIDataResponse.setValidationMessage("EVENT_LOGS_NOT_FOUND");
         return commonAPIDataResponse;
      } else {
         commonAPIDataResponse.setMessage("EVENT_LOGS_UPDATED_SUCCESSFULLY");
         return commonAPIDataResponse;
      }
   }

   public GetAllEventLogsResponse getEventLogsData(GetAllEventLogsRequest getAllEventLogsRequest) {
      GetAllEventLogsResponse getAllEventLogsResponse = new GetAllEventLogsResponse();
      List<EventLogs> eventLogsDataList = this.eventLogsQueryDao.findAllByFilter(getAllEventLogsRequest);
      if (!Objects.isNull(eventLogsDataList) && !eventLogsDataList.isEmpty()) {
         List<EventLogsMaster> eventLogsMastersList = (List)eventLogsDataList.stream().map(EventLogsMaster::new).collect(Collectors.toList());
         getAllEventLogsResponse.setEventLogsMasters(eventLogsMastersList);
         return getAllEventLogsResponse;
      } else {
         getAllEventLogsResponse.setValidationMessage("EVENT_LOGS_DATA_NOT_FOUND");
         getAllEventLogsResponse.setCheckValidationFailed(true);
         return getAllEventLogsResponse;
      }
   }
}
