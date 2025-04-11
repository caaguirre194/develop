package com.caaguirre.develop.models;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


/**
 * Representa la petición del servicio de suma de números enteros.
 *
 * @param nums
 * @param target
 */
@Schema(name = "ExcerciseSumRequest", description = "Petición del servicio suma.")
public record ExcerciseSumRequest(
        @Schema(name = "list", description = "Lista de números enteros", type = "array", example = "[1, 2, 3]")
        @ArraySchema(schema = @Schema(type = "integer", format = "int32"))
        List<Integer> nums,

        @Schema(name = "target", description = "Valor a buscar en el listado.", type = "int", example = "11")
        int target

) {}