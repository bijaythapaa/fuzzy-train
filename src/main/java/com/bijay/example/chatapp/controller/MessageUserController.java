package com.bijay.example.chatapp.controller;

import com.bijay.example.chatapp.model.Message;
import com.bijay.example.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:11 AM
 */

@RestController
@RequestMapping("/api/user/message")
public class MessageUserController {

    @Autowired
    private MessageService messageService;

//    @PostMapping("/save")
//    public ResponseEntity<ResponseDTO> saveUserMessage(@RequestBody Message message){
//        ResponseDTO responseDTO = new ResponseDTO();
//
//        return null;
//    }

}
