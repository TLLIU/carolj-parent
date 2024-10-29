package idv.tlliu.springboot.stomp.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import idv.tlliu.springboot.stomp.entity.Message;

@Controller
public class MessageController {
  //简单的消息发送，接收 JSON 对象并返回
  @MessageMapping("/send")
  @SendTo("/topic/messages")
  public Message send(Message message) {
    return new Message(message.getContent() + " Received!"); 
  }
  
  //异步消息处理
  @MessageMapping("/asyncMessage")
  @SendTo("/topic/asyncMessages")
  public CompletableFuture<Message> asyncMessage(Message message) {
    CompletableFuture<Message> futureTask = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return message;
    }) ;
    return futureTask;
  }
  
  // 发布/订阅模式
  @MessageMapping("/broadcast")
  @SendTo("/topic/broadcasts")
  public String broadcast(String content) {
      return "广播消息: " + content;
  }

  // 处理带异常的消息
  @MessageMapping("/errorHandling")
  @SendTo("/queue/errors")
  public Message handleError(Message message) throws Exception {
      if ("error".equalsIgnoreCase(message.getContent())) {
          throw new Exception("消息包含错误内容");
      }
      return message;
  }  
}
