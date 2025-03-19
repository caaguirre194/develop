package com.caaguirre.develop.models;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import java.util.List;

@Data
@Builder
@Schema(name = "IntegersRequest", description = "Petición del servicio ejercicios listados.")
public class IntegersRequest {

    /**
     * Atributo provider de tipo List<Integer>
     */
    @Schema(name = "list", description = "Lista de números enteros", type = "array", example = "[1, 2, 3]")
    @ArraySchema(schema = @Schema(type = "integer", format = "int32"))
    private List<Integer> list;
}
