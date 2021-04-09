package com.bijay.example.chatapp.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 10:25 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractEntity<Long> {

    private Long customerId;

    private String fullName;

    private String mobileNumber;

}
