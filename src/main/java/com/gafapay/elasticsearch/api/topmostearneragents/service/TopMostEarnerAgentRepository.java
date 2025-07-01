package com.gafapay.elasticsearch.api.topmostearneragents.service;

import com.gafapay.elasticsearch.api.topmostearneragents.model.request.TopMostEarnerAgentsRequest;
import com.gafapay.elasticsearch.api.topmostearneragents.model.response.TopMostEarnerAgentsResponse;

public interface TopMostEarnerAgentRepository {
   TopMostEarnerAgentsResponse topMostEarnerAgents(TopMostEarnerAgentsRequest topMostEarnerAgentsRequest);
}
