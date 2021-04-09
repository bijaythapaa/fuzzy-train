package com.bijay.example.chatapp.controller;

import com.bijay.example.chatapp.dto.ConversationDTO;
import com.bijay.example.chatapp.dto.MessageDTO;
import com.bijay.example.chatapp.service.DynamicOrderMessage;
import com.bijay.example.chatapp.service.ReactiveConversationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 11:22 AM
 */

@RestController
@RequestMapping("/chatServer/reactive")
public class ReactiveAdminController {

    private final ReactiveConversationService conversationService;

    public ReactiveAdminController(ReactiveConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/messages/all")
    public Flux<MessageDTO> getAllMessages(@RequestParam(value = "convId") Long convId) {
        try {
            return conversationService.getAllMessages(convId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/conversations/all")
    public Flux<ConversationDTO> getAllConversationAdminPages(
            @RequestParam(value = "bankId") String bankId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "created, desc") String[] sort) {
        try {
            List<Sort.Order> orders = DynamicOrderMessage.getOrders(sort);
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<ConversationDTO> conversationDTOPage = conversationService.getAllConversationPages(
                    Long.valueOf(bankId), pageable);
            return Flux.fromIterable(conversationDTOPage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
