package com.caaguirre.develop.models;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;


/**
 * Representa la información relevante de las propiedades de un usuario.
 * @param userId
 * @param name
 * @param propertyId
 * @param address
 * @param propertiesL
 */
@Schema(name = "UserPropertyDTO", description = "Propiedades de un usuario.")
public record UserPropertyDTO(
        @Schema(name = "userId", description = "Identificador del usuario.", type = "int", example = "6973")
        int userId,

        @Schema(name = "name", description = "Nombre del usuario.", type = "String", example = "Miguel Ramirez")
        String name,

        @Schema(name = "propertyId", description = "Identificador de la propiedad.", type = "UUID", example = "290e4c48-0e44-4c0d-b835-0242af6040ba")
        UUID propertyId,

        @Schema(name = "address", description = "Dirección de la propiedad.", type = "String", example = "5495 Maple Ave, Phoenix, TX 46126")
        String address,

        @Schema(name = "propertiesL", description = "Lista de propiedades asociadas al usuario")
        @ArraySchema(schema = @Schema(implementation = Property.class))
        List<Property> propertiesL

) {
    public UserPropertyDTO(List<User> users, List<Property> properties) {
        this(
                users.get(0).getIdentification(),
                users.get(0).getName(),
                properties.get(0).getId(),   // Assuming Property has an `id` field
                properties.get(0).getAddress(),
                properties
        );
    }
}
