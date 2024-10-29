package idv.tlliu.springboot.logger.service;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import idv.tlliu.springboot.logger.event.CarolLogEvent;

@Component
public class CarolLogAppender {
  
  @Async
  @EventListener(CarolLogEvent.class)
  public void log(CarolLogEvent log) {
    
  }
  

}
