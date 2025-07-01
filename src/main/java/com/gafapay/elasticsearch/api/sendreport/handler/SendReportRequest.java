package com.gafapay.elasticsearch.api.sendreport.handler;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class SendReportRequest extends CommonAPIDataRequest {
   private MultipartFile report_file;
   private String image_url;
   private Integer report_type;
   private String report_name;

   public SendReportRequest() {
   }

   public boolean checkBadRequest() {
      if (Objects.isNull(this.report_file) && StringUtils.isEmpty(this.image_url)) {
         return true;
      } else if (StringUtils.isEmpty(this.getCompany_id())) {
         return true;
      } else {
         return Objects.isNull(this.getReport_type());
      }
   }

   public MultipartFile getReport_file() {
      return this.report_file;
   }

   public String getImage_url() {
      return this.image_url;
   }

   public Integer getReport_type() {
      return this.report_type;
   }

   public String getReport_name() {
      return this.report_name;
   }

   public void setReport_file(final MultipartFile report_file) {
      this.report_file = report_file;
   }

   public void setImage_url(final String image_url) {
      this.image_url = image_url;
   }

   public void setReport_type(final Integer report_type) {
      this.report_type = report_type;
   }

   public void setReport_name(final String report_name) {
      this.report_name = report_name;
   }
}
