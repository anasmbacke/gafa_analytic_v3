package com.gafapay.elasticsearch.statemachine.states;

public enum AppEvents {
   TURN_ON,
   TURN_OFF;

   private AppEvents() {
   }

   // $FF: synthetic method
   private static AppEvents[] $values() {
      return new AppEvents[]{TURN_ON, TURN_OFF};
   }
}
