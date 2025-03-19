package com.caaguirre.develop.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Property {
    private UUID id;
    private String address;
    private int stratum;
    private int identificationOwner;
}
