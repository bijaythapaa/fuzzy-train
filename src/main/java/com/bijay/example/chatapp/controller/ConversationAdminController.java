package com.bijay.example.chatapp.controller;

import com.bijay.example.chatapp.dto.ConversationDTO;
import com.bijay.example.chatapp.model.Message;
import com.bijay.example.chatapp.service.ConversationService;
import com.bijay.example.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 4:30 PM
 */

@RestController
@RequestMapping("/chatServer/admin")
public class ConversationAdminController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    private Sort.Direction getSortingDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private List<Order> getOrders(String[] sort) {
        List<Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortingDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Order(getSortingDirection(sort[1]), sort[0]));
        }
        return orders;
    }

    @GetMapping("/conversations/all")
    public ResponseEntity<Map<String, Object>> getAllConversationAdminPages(
            @RequestParam(value = "bankId") String bankId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "created, desc") String[] sort) {
        try {
            List<Order> orders = getOrders(sort);
//          Pageable pageable = new PageRequest(page, size, new Sort(orders));
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<ConversationDTO> conversationDTOPage = conversationService.getAllConversationPages(
                    Long.valueOf(bankId), pageable);
            List<ConversationDTO> conversationDTOS = conversationDTOPage.getContent();
            if (conversationDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("conversations", conversationDTOS);
            map.put("currentPage", conversationDTOPage.getNumber());
            map.put("totalItems", conversationDTOPage.getTotalElements());
            map.put("totalPages", conversationDTOPage.getTotalPages());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/messages/all")
    public ResponseEntity<Map<String, Object>> getAllMessages(@RequestParam(value = "convId") Long convId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("messages", conversationService.getAllMessages(convId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/message/save")
    public ResponseEntity<Map<String, Object>> saveAdminMessages(@RequestParam(value = "bankId") String bankId,
                                                                 @RequestParam(value = "msgId") String msgId,
                                                                 @RequestParam(value = "message") String msg,
            /*@RequestParam(value = "userId") String userId,*/
                                                                 @RequestParam(value = "file", required = false)
                                                                         MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            Message message = new Message();
            if (file != null) {
                message.setFile(file);
            }
            Long userId = messageService.getUserId(Long.valueOf(bankId), Long.valueOf(msgId));
            map.put("apiResponse", messageService.saveMessage(msg, message, "Bank", userId, Long.valueOf(bankId)));
            map.put("conv", messageService.getConversationById(Long.valueOf(msgId)));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
