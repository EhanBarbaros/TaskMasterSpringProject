package com.example.demo.controller;

import com.example.demo.entity.ChatMessage;
import com.example.demo.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {

	@Autowired
    private ChatMessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return messageService.saveMessage(message.getFromUser(), message.getContent());
    }

    @GetMapping("/chat/messages")
    @ResponseBody
    public List<ChatMessage> getAllMessages() {
        return messageService.getAllMessages();
    }
}