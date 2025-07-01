package com.gafapay.elasticsearch.api.reportexport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAllAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllReportExportFilesRequest extends CommonAllAPIDataRequest {
   private String report_name;
   private String download_url;
   private String user_id;
   private Integer user_type;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public String getReport_name() {
      return this.report_name;
   }

   public String getDownload_url() {
      return this.download_url;
   }

   public String getUser_id() {
      return this.user_id;
   }

   public Integer getUser_type() {
      return this.user_type;
   }

   public void setReport_name(final String report_name) {
      this.report_name = report_name;
   }

   public void setDownload_url(final String download_url) {
      this.download_url = download_url;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setUser_type(final Integer user_type) {
      this.user_type = user_type;
   }

   public GetAllReportExportFilesRequest() {
   }
}
