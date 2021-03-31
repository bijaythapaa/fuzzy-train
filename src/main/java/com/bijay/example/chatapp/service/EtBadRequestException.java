package com.bijay.example.chatapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:59 AM
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EtBadRequestException extends RuntimeException {
    public EtBadRequestException(String message) {
        super(message);
    }
}
