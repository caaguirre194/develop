package com.caaguirre.develop.controller;

import com.caaguirre.develop.models.*;
import com.caaguirre.develop.service.ExcerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;


/**
 * @author Carlos Andrés Aguirre
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@Tag(name = "excercises", description = "Agrupación de los ejercicios.")
@RequestMapping("api/v1/excercises")
public class ExcerciseController {

    private final ExcerciseService excerciseService;

    public ExcerciseController(ExcerciseService excerciseService) {
        this.excerciseService = excerciseService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Servicio que consulta los usuarios del sistema.",
            summary = "Consultar usuarios.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en los parametros porporcionados"),
            @ApiResponse(responseCode = "500", description = "Error del Servidor")
    })
    ResponseEntity<Mono<List<User>>> users(@RequestParam Optional<Integer> id,
                                           @RequestParam Optional<Integer> age,
                                           @RequestParam Optional<String> name) throws InterruptedException {

        return ResponseEntity.ok(excerciseService.users(id, age, name));
    }

    @GetMapping(value = "/properties", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Servicio que consulta los propiedades de un usuario.",
            summary = "Consultar propiedades.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en los parametros porporcionados"),
            @ApiResponse(responseCode = "500", description = "Error del Servidor")
    })
    ResponseEntity<Mono<List<Property>>> properties(@RequestParam Optional<Integer> owner) throws InterruptedException {
        return ResponseEntity.ok(excerciseService.properties(owner));
    }

    @GetMapping(value = "/user/properties", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Servicio que consulta los propiedades de un usuario.",
            summary = "Consultar propiedades.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en los parametros porporcionados"),
            @ApiResponse(responseCode = "500", description = "Error del Servidor")
    })
    public Mono<UserPropertyDTO> obtenerTodasPropiedadesPorUsuario(@RequestParam Optional<Integer> owner) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultDate = new Date(System.currentTimeMillis());
        log.info("WebFlux inicio -> " + sdf.format(resultDate));

        return Mono.zip(excerciseService.users(owner, Optional.empty(),
                        Optional.empty()), excerciseService.properties(owner))
                .map(tuple -> new UserPropertyDTO(tuple.getT1(), tuple.getT2()))
                .doOnSuccess(i -> log.info("WebFlux fin -> " + sdf.format(new Date(System.currentTimeMillis()))));
    }

}
