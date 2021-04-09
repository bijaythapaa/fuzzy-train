package com.bijay.example.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/5/21 - 10:25 AM
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractEntity<Long> {

    private Long customerId;

    private String fullName;

    private String mobileNumber;

}
