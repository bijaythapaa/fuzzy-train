package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.dto.ConversationDTO;
import com.bijay.example.chatapp.dto.MessageDTO;
import com.bijay.example.chatapp.model.Conversation;
import com.bijay.example.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 11:46 AM
 */

public final class ToDTOConverter {

    public static List<ConversationDTO> convertToConversationDTO(List<Conversation> conversations) {
        List<ConversationDTO> conversationDTOS = new ArrayList<>();
        for (Conversation conversation : conversations) {
            ConversationDTO conversationDTO = new ConversationDTO();
            conversationDTO.setId(conversation.getId());
            conversationDTO.setLastMessage(conversation.getLastMessage().getMessage());
            conversationDTO.setBankId(conversation.getBankId());
            conversationDTO.setUserId(conversation.getUserId());

//            CustomerDTO customerDTO = convertUtil.convertCustomer(customerRepository.getCustomerByUser(
//            conversationRepository.findUserByConversationId(conversation.getId()).getId()));
//            conversationDTO.setCustomerFullName(customerDTO.getFullName());
//            conversationDTO.setCustomerMobileNumber(customerDTO.getMobileNumber());
//            conversationDTO.setBankFullName(conversation.getBank().getName());
            conversationDTOS.add(conversationDTO);
        }
        return conversationDTOS;
    }

    public static List<MessageDTO> convertToMessageDTO(List<Message> messages) {
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(message.getId());
            messageDTO.setMessage(message.getMessage());
            messageDTO.setStatus(message.getStatus());
            messageDTO.setCreatedDate(message.getCreated().toString());
            messageDTO.setSentBy(message.getSentBy());
            messageDTO.setImageUrl(message.getImageUrl());
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

}
