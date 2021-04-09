package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.dto.ConversationDTO;
import com.bijay.example.chatapp.dto.MessageDTO;
import com.bijay.example.chatapp.model.Conversation;
import com.bijay.example.chatapp.model.Message;
import com.bijay.example.chatapp.model.Status;
import com.bijay.example.chatapp.repository.ConversationRepository;
import com.bijay.example.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 11:41 AM
 */

@Service
public class ReactiveConversationService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Transactional
    public Flux<MessageDTO> getAllMessages(Long convId) {
        List<Message> messages = messageRepository.findAllByConversationId(convId);
        for (Message message : messages) {
            message.setStatus(Status.DELIVERED);
        }
        messageRepository.saveAll(messages);
        List<Message> messagesList = messageRepository.findAllByConversationId(convId);
        return Flux.fromIterable(ToDTOConverter.convertToMessageDTO(messagesList));
    }

    @Transactional
    public Page<ConversationDTO> getAllConversationPages(Long bankId, Pageable pageable) {
        Page<Conversation> conversations = conversationRepository.findAllByBankId(bankId, pageable);
        List<ConversationDTO> conversationDTOS = ToDTOConverter.convertToConversationDTO(
                conversations.getContent());
        return new PageImpl<>(conversationDTOS, pageable, conversations.getSize());
    }

}
