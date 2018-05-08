package com.rdas.model;

import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
public class Person {
    private Integer id;

    private String name;

    private int age;

    private PersonType type;
}
