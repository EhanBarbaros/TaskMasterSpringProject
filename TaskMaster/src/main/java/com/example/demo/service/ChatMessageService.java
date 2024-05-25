package com.example.demo.service;

import com.example.demo.entity.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository messageRepository;

    public List<ChatMessage> getAllMessages() {
        return messageRepository.findAll();
    }

    public ChatMessage saveMessage(String fromUser, String content) {
    	ChatMessage message = new ChatMessage();
        message.setFromUser(fromUser);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
