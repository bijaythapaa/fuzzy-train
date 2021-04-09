package com.bijay.example.chatapp.service;

import com.bijay.example.chatapp.model.Conversation;
import com.bijay.example.chatapp.model.Document;
import com.bijay.example.chatapp.model.Message;
import com.bijay.example.chatapp.repository.ConversationRepository;
import com.bijay.example.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:08 AM
 */

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private IDocumentsApi iDocumentsApi;

    @Autowired
    private FileHandler fileHandler;

    public String saveMessage(String msg, Message message, String s, Long userId, Long bankId) {

        try {
            message.setMessage(msg);
            if (s.equals("Bank")) {
                message.setToUserId(userId);
            } else {
                message.setFromUserId(userId);
            }
            Conversation conversation = conversationRepository.findConversationByUserIdAndBankId(userId, bankId);
            if (conversation == null) {
                conversation = new Conversation();
                conversation.setUserId(userId);
                conversation.setBankId(bankId);
                conversation = conversationRepository.save(conversation);
            }
            message.setFromUserId(userId);
            message.setBankId(bankId);
            message.setSentBy(s);
            message.setConversation(conversation);

            if (message.getFile() != null) {
                Document document = iDocumentsApi.saveDocuments(fileHandler.multipartSingleNameResolver(
                        message.getFile()));
                fileHandler.chatImageHandler(message.getFile(), document);
                message.setDocument(document);
                String filename = document.getIdentifier() + "." + document.getExtention();
                message.setImageUrl("/mbank/chatimage/" + filename);
            } else {
                message.setImageUrl(null);
            }
            messageRepository.save(message);
            conversation.setLastMessage(message);
            conversationRepository.save(conversation);

            return "Message sent Successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            throw new EtBadRequestException("Message send Failed.");
        }
    }

    public List<Message> getAllUserMessage(Long userId) {
        return messageRepository.findAllByFromUserOrToUser(userId);
    }

    public Conversation getConversationById(Long msgId) {
        return messageRepository.findConversationByMessageId(msgId);
    }

    public Long getUserId(Long bankId, Long msgId) {
        Long userId = messageRepository.findToUserIdByMessageIdAndBankId(bankId, msgId);
        return userId;
    }
}
