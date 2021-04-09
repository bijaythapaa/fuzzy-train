package com.bijay.example.chatapp.controller;

import com.bijay.example.chatapp.dto.ResponseDTO;
import com.bijay.example.chatapp.model.Message;
import com.bijay.example.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:20 AM
 */

@RestController
@RequestMapping(value = "/chatServer/user")
public class ConversationUserController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message/save")
    @ResponseBody
    public ResponseEntity<ResponseDTO> saveUserMessage(@RequestParam(value = "userId") String userId,
                                                       @RequestParam(value = "bankId") String bankId,
                                                       @RequestParam(value = "message") String msg,
                                                       @RequestParam(value = "file", required = false)
                                                               MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Message message = new Message();
            if (file != null) {
                message.setFile(file);
            }
            String saveResponse = messageService.saveMessage(msg, message, "User",
                    Long.valueOf(userId), Long.valueOf(bankId));
            responseDTO.setMessage("Conversations Saved Successfully");
            responseDTO.setCode(HttpStatus.OK.getReasonPhrase());
            responseDTO.setDetail(saveResponse);
            responseDTO.setStatus(HttpStatus.OK.name());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setMessage("Something went Wrong");
            responseDTO.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/message/get/{userId}")
    @ResponseBody
    public ResponseEntity<ResponseDTO> getAllUserMessages(@PathVariable("userId") Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Message> messages = messageService.getAllUserMessage(userId);
            responseDTO.setMessage("Messages Fetched Successfully");
            responseDTO.setCode(HttpStatus.OK.getReasonPhrase());
            responseDTO.setDetail(messages);
            responseDTO.setStatus(HttpStatus.OK.name());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

}
