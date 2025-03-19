package com.caaguirre.develop.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    String name;
    String lastname;
    int age;

}
