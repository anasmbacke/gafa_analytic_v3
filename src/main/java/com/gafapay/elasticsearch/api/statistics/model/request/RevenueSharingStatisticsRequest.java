package com.gafapay.elasticsearch.api.statistics.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class RevenueSharingStatisticsRequest extends CommonAPIDataRequest {
   private DateFilter date_filter;

   public boolean checkBadRequest() {
      return Objects.isNull(this.getDate_filter().getStart_date()) || Objects.isNull(this.getDate_filter().getEnd_date()) || StringUtils.isEmpty(this.getDate_filter().getDate_field_name()) || Objects.isNull(this.getDate_filter().getFilter_type()) || Objects.isNull(this.getCompany_id());
   }

   public DateFilter getDate_filter() {
      return this.date_filter;
   }

   public void setDate_filter(final DateFilter date_filter) {
      this.date_filter = date_filter;
   }

   public RevenueSharingStatisticsRequest() {
   }
}
