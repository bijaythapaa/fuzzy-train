package com.bijay.example.chatapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 4:49 PM
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationDTO {

    private Long id;
    private String lastMessage;
    private Long bankId;
    private Long userId;

//    private String customerFullName;
//    private String customerMobileNumber;
//    private String bankFullName;
//    private List<Message> messages;
//    private Message lastMessage;
//    private CustomerDTO customerDTO;
//    private BankDTO bankDTO;

}
