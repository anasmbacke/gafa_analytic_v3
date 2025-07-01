package com.gafapay.elasticsearch.api.eventlogs.dao;

import com.gafapay.elasticsearch.api.eventlogs.model.EventLogs;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import java.util.List;
import org.springframework.data.mongodb.core.query.Update;

public interface EventLogsQueryDao {
   void save(EventLogs eventLogs);

   long update(String id, Update updateDocument);

   int deleteById(String id);

   EventLogs findById(String id);

   List<EventLogs> findAllByFilter(GetAllEventLogsRequest getAllEventLogsRequest);
}
