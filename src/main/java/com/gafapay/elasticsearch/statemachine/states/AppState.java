package com.gafapay.elasticsearch.statemachine.states;

public enum AppState {
   ACTIVE,
   INACTIVE;

   private AppState() {
   }

   // $FF: synthetic method
   private static AppState[] $values() {
      return new AppState[]{ACTIVE, INACTIVE};
   }
}
