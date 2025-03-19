package com.caaguirre.develop.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {

    UUID id;
    String name;
    String lastname;
    int age;

}
