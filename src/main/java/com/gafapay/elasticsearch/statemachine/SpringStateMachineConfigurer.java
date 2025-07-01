package com.gafapay.elasticsearch.statemachine;

import com.gafapay.elasticsearch.statemachine.states.AppEvents;
import com.gafapay.elasticsearch.statemachine.states.AppState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.ExternalTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
public class SpringStateMachineConfigurer extends StateMachineConfigurerAdapter<AppState, AppEvents> {
   public SpringStateMachineConfigurer() {
   }

   public void configure(StateMachineTransitionConfigurer<AppState, AppEvents> transitions) throws Exception {
      ((ExternalTransitionConfigurer)((StateMachineTransitionConfigurer)((ExternalTransitionConfigurer)((ExternalTransitionConfigurer)transitions.withExternal().source(AppState.ACTIVE)).target(AppState.INACTIVE).event(AppEvents.TURN_OFF)).and()).withExternal().source(AppState.INACTIVE)).target(AppState.ACTIVE).event(AppEvents.TURN_ON);
   }

   public void configure(StateMachineStateConfigurer<AppState, AppEvents> states) throws Exception {
      states.withStates().initial(AppState.INACTIVE).state(AppState.ACTIVE);
   }
}
