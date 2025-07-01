package com.gafapay.elasticsearch.api.commonrequest;

import java.util.Map;

public class ChangeStream {
   private String operationType;
   private Map<String, Object> clusterTime;
   private Map<String, Object> fullDocument;
   private Map<String, Object> ns;
   private Map<String, Object> documentKey;
   private Map<String, Object> updateDescription;
   private Map<String, Object> fullDocumentBeforeChange;

   public ChangeStream() {
   }

   public String getOperationType() {
      return this.operationType;
   }

   public Map<String, Object> getClusterTime() {
      return this.clusterTime;
   }

   public Map<String, Object> getFullDocument() {
      return this.fullDocument;
   }

   public Map<String, Object> getNs() {
      return this.ns;
   }

   public Map<String, Object> getDocumentKey() {
      return this.documentKey;
   }

   public Map<String, Object> getUpdateDescription() {
      return this.updateDescription;
   }

   public Map<String, Object> getFullDocumentBeforeChange() {
      return this.fullDocumentBeforeChange;
   }

   public void setOperationType(final String operationType) {
      this.operationType = operationType;
   }

   public void setClusterTime(final Map<String, Object> clusterTime) {
      this.clusterTime = clusterTime;
   }

   public void setFullDocument(final Map<String, Object> fullDocument) {
      this.fullDocument = fullDocument;
   }

   public void setNs(final Map<String, Object> ns) {
      this.ns = ns;
   }

   public void setDocumentKey(final Map<String, Object> documentKey) {
      this.documentKey = documentKey;
   }

   public void setUpdateDescription(final Map<String, Object> updateDescription) {
      this.updateDescription = updateDescription;
   }

   public void setFullDocumentBeforeChange(final Map<String, Object> fullDocumentBeforeChange) {
      this.fullDocumentBeforeChange = fullDocumentBeforeChange;
   }
}
