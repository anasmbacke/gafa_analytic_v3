package com.gafapay.elasticsearch.database.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class EmptyPage implements Pageable {
   public static final EmptyPage INSTANCE = new EmptyPage();

   public int getPageNumber() {
      return 0;
   }

   public int getPageSize() {
      return 0;
   }

   public long getOffset() {
      return 0L;
   }

   public Sort getSort() {
      return null;
   }

   public Pageable next() {
      return null;
   }

   public Pageable previousOrFirst() {
      return null;
   }

   public Pageable first() {
      return null;
   }

   public Pageable withPage(int pageNumber) {
      return null;
   }

   public boolean hasPrevious() {
      return false;
   }

   public EmptyPage() {
   }
}
