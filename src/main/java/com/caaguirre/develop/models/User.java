package com.caaguirre.develop.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {

    private UUID id;
    private int identification;
    private String name;
    private String lastname;
    private int age;

}
