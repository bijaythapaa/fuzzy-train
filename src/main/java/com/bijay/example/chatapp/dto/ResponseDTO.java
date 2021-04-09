package com.bijay.example.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 11:15 AM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO {

    private String status;
    private String code;
    private String message;
    private Object details;
    private Object detail;

    public ResponseDTO(String message) {
        this.message = message;
    }


}
