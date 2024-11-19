package com.example.prak4;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketHandler {

    @MessageMapping("/webs")
    @SendTo("/topic/messages")
    public String handleMessage(String message) {
        System.out.println("Message: " + message);
        return "Сообщение вернулось от сервера: " + message;
    }
}