package com.bijay.example.chatapp.dto;

import com.bijay.example.chatapp.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 5:27 PM
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private Long id;
    private String message;
    private String createdDate;
    private Status status;
    private String sentBy;
    private String imageUrl;

}
