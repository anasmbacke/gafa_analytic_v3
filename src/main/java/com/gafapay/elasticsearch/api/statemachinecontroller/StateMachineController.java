package com.gafapay.elasticsearch.api.statemachinecontroller;

import com.gafapay.elasticsearch.statemachine.StateMachineAccessor;
import com.gafapay.elasticsearch.statemachine.states.AppEvents;
import com.gafapay.elasticsearch.statemachine.states.AppState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class StateMachineController {
   @Autowired
   private StateMachineAccessor stateMachineAccessor;

   public StateMachineController() {
   }

   @GetMapping({"switch/{id}"})
   public String performSwitch(@PathVariable String id) {
      StateMachine<AppState, AppEvents> stateMachine = this.stateMachineAccessor.getStateMachine();
      if (id.equals("on")) {
         stateMachine.sendEvent(AppEvents.TURN_ON);
         return ((AppState)stateMachine.getState().getId()).name();
      } else if (id.equals("off")) {
         stateMachine.sendEvent(AppEvents.TURN_OFF);
         return ((AppState)stateMachine.getState().getId()).name();
      } else {
         return ((AppState)stateMachine.getState().getId()).name();
      }
   }
}
