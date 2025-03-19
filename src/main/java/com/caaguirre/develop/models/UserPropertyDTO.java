package com.caaguirre.develop.models;

import lombok.Data;

import java.util.List;

@Data
public class UserPropertyDTO {

    private int userId;
    private String name;
    private int propertyId;
    private String address;
    private List<Property> propertiesL;

    public UserPropertyDTO(List<User> users, List<Property> properties) {
        this.userId = users.get(0).getIdentification();
        this.address = properties.get(0).getAddress();
        this.name = users.get(0).getName();
        this.propertiesL = properties;
    }

}

