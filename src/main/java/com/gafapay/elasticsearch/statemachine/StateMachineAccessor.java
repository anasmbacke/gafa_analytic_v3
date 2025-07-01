package com.gafapay.elasticsearch.statemachine;

import com.gafapay.elasticsearch.statemachine.states.AppEvents;
import com.gafapay.elasticsearch.statemachine.states.AppState;
import org.springframework.statemachine.StateMachine;

public interface StateMachineAccessor {
   StateMachine<AppState, AppEvents> getStateMachine();
}
