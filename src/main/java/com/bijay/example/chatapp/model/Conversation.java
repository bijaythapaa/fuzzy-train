package com.bijay.example.chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 10:47 AM
 */

@Entity
@Getter
@Setter
public class Conversation extends AbstractEntity<Long> {

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Message> messages;

    @OneToOne(fetch = FetchType.EAGER)
    private Message lastMessage;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
//    @JsonIgnore
//    private User user;
    @NotNull
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
//    @JsonIgnore
//    private Bank bank;
    @NotNull
    private Long bankId;

//    @Transient
//    @NonNull
//    private Long toUserId;
}
