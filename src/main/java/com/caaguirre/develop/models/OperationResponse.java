package com.caaguirre.develop.models;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "OperationResponse", description = "Respuesta del servicio ejercicios listados.")
public record OperationResponse(
        @Schema(name = "data", description = "Contenido de respuesta", type = "int", example = "5")
        int data,

        @Schema(name = "error", description = "Error de respuesta", type = "String", example = "No content")
        String error,

        @Schema(name = "status", description = "Status de respuesta", type = "int", example = "200")
        int status
) {}
