package com.caaguirre.develop.controller;

import com.caaguirre.develop.models.IntegersRequest;
import com.caaguirre.develop.models.User;
import com.caaguirre.develop.models.ExcerciseSumRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.tags.Tag;


import static com.caaguirre.develop.common.Constant.*;

@RestController
@Tag(name = "excercises", description = "Agrupación de los ejercicios.")
@RequestMapping("api/v1/excercises")
public class ExcerciseController {

    @PostMapping("/1")
    int[] sumExcercise(@Valid @RequestBody ExcerciseSumRequest request) {

        Map<Integer, Integer> validations = new HashMap<>();
        for (int i = 0; i < request.getNums().length; i++) {
            Integer complementIndex = validations.get(request.getNums()[i]);

            if (complementIndex != null) {
                return new int[]{complementIndex, i};
            }

            validations.put(request.getTarget() - request.getNums()[i], i);

        }

        return request.getNums();

    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Servicio que consulta los usuarios del sistema.",
            summary = "Consultar usuarios.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en los parametros porporcionados"),
            @ApiResponse(responseCode = "500", description = "Error del Servidor")
    })
    ResponseEntity<List<User>> filterExcercise(@RequestParam Optional<Integer> age, @RequestParam Optional<String> name) {

        // Combine filters into one predicate
        Predicate<User> combinedPredicate = user -> true; // Default predicate that accepts all users

        // Apply the name filter if present
        if (name.isPresent()) {
            combinedPredicate = combinedPredicate.and(hasTheName(name.get()));
        }

        // Apply the age filter if present
        if (age.isPresent()) {
            combinedPredicate = combinedPredicate.and(isOlderThan(age.get()));
        }

        return ResponseEntity.ok(USERS.stream()
                .filter(combinedPredicate)
                .peek(System.out::println)
                .collect(Collectors.toList()));

    }

    @PostMapping("/enteros")
    List<Integer> filterWithOutCeros(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .filter(num -> num != 0)
                .collect(Collectors.toList());
    }

    @PostMapping("/validate")
    List<Integer> validate(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .map(num -> Math.max(0, Math.min(num, 100)))
                .collect(Collectors.toList());
    }

    @PostMapping("/distinct")
    List<Integer> distinc(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/sum")
    Optional<Integer> sum(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .reduce(Integer::sum);

    }

    @PostMapping("/max")
    Optional<Integer> max(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .reduce(Integer::max);

    }

    @PostMapping("/min")
    Optional<Integer> min(@RequestBody IntegersRequest request) {

        return request.getList().stream()
                .reduce(Integer::min);

    }

}
