package com.caaguirre.develop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import java.util.List;

/**
 * Representa la petición del servicio para operaciones con números enteros.
 *
 * @param list Lista de números enteros proporcionada en la petición.
 */
@Schema(name = "OperationRequest", description = "Petición del servicio ejercicios listados.")
public record OperationRequest(

        @Schema(name = "list", description = "Lista de números enteros", type = "array", example = "[1, 2, 3]")
        @ArraySchema(schema = @Schema(type = "integer", format = "int32"))
        List<Integer> list

) {}