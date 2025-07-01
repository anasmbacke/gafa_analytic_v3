package com.gafapay.elasticsearch.datadog;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CustomAppender extends AppenderBase<ILoggingEvent> {
   private PatternLayoutEncoder encoder;
   Connector connector = new Connector(new KafkaConnector());
   ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);

   protected void append(ILoggingEvent loggingEventVO) {
      this.executor.execute(() -> this.connector.connector(loggingEventVO));
   }

   public CustomAppender() {
   }

   public PatternLayoutEncoder getEncoder() {
      return this.encoder;
   }

   public Connector getConnector() {
      return this.connector;
   }

   public ThreadPoolExecutor getExecutor() {
      return this.executor;
   }

   public void setEncoder(final PatternLayoutEncoder encoder) {
      this.encoder = encoder;
   }

   public void setConnector(final Connector connector) {
      this.connector = connector;
   }

   public void setExecutor(final ThreadPoolExecutor executor) {
      this.executor = executor;
   }

   public String toString() {
      PatternLayoutEncoder var10000 = this.getEncoder();
      return "CustomAppender(encoder=" + var10000 + ", connector=" + this.getConnector() + ", executor=" + this.getExecutor() + ")";
   }

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomAppender)) {
         return false;
      } else {
         CustomAppender other = (CustomAppender)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$encoder = this.getEncoder();
            Object other$encoder = other.getEncoder();
            if (this$encoder == null) {
               if (other$encoder != null) {
                  return false;
               }
            } else if (!this$encoder.equals(other$encoder)) {
               return false;
            }

            Object this$connector = this.getConnector();
            Object other$connector = other.getConnector();
            if (this$connector == null) {
               if (other$connector != null) {
                  return false;
               }
            } else if (!this$connector.equals(other$connector)) {
               return false;
            }

            Object this$executor = this.getExecutor();
            Object other$executor = other.getExecutor();
            if (this$executor == null) {
               if (other$executor != null) {
                  return false;
               }
            } else if (!this$executor.equals(other$executor)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof CustomAppender;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = super.hashCode();
      Object $encoder = this.getEncoder();
      result = result * 59 + ($encoder == null ? 43 : $encoder.hashCode());
      Object $connector = this.getConnector();
      result = result * 59 + ($connector == null ? 43 : $connector.hashCode());
      Object $executor = this.getExecutor();
      result = result * 59 + ($executor == null ? 43 : $executor.hashCode());
      return result;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void append(Object loggingEventVO) {
      this.append((ILoggingEvent)loggingEventVO);
   }
}
