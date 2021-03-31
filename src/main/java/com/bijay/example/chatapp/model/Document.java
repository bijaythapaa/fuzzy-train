package com.bijay.example.chatapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 3/31/21 - 10:56 AM
 */

@Entity
@Table(name = "documents")
@Getter
@Setter
public class Document extends AbstractEntity<Long> {

    private String identifier;

    private String extention;

}
