package com.rdas.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode
public class Message implements Serializable {

    private String id;
    private String messageContent;
}
