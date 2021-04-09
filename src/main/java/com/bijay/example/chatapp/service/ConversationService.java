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

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:10 AM
 */

@Service
public class ConversationService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

//    @Autowired
//    private CustomerRepository customerRepository;

//    @Autowired
//    private ConvertUtil convertUtil;

    @Transactional
    public Page<ConversationDTO> getAllConversationPages(Long bankId, Pageable pageable) {
        Page<Conversation> conversations = conversationRepository.findAllByBankId(bankId, pageable);
        List<ConversationDTO> conversationDTOS = ToDTOConverter.convertToConversationDTO(conversations.getContent());
        return new PageImpl<>(conversationDTOS, pageable, conversations.getSize());
    }

    @Transactional
    public List<MessageDTO> getAllMessages(Long convId) {
        List<Message> messages = messageRepository.findAllByConversationId(convId);
        for (Message message : messages) {
            message.setStatus(Status.DELIVERED);
        }
        messageRepository.saveAll(messages);
        List<Message> messagesList = messageRepository.findAllByConversationId(convId);
        return ToDTOConverter.convertToMessageDTO(messagesList);
    }

}
