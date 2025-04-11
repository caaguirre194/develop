package com.caaguirre.develop.controller;

import com.caaguirre.develop.models.ExcerciseSumRequest;
import com.caaguirre.develop.models.OperationRequest;
import com.caaguirre.develop.models.OperationResponse;
import com.caaguirre.develop.service.OperationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Carlos Andrés Aguirre
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@Tag(name = "operations", description = "Agrupación de operaciones.")
@RequestMapping("api/v1/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/1")
    ResponseEntity<List<Integer>> sumExcercise(@Valid @RequestBody ExcerciseSumRequest request) {

        Map<Integer, Integer> validations = new HashMap<>();
        for (int i = 0; i < request.nums().size(); i++) {
            Integer complementIndex = validations.get(request.nums().get(i));

            if (complementIndex != null) {
                return ResponseEntity.ok(List.of(complementIndex, i));
            }

            validations.put(request.target() - request.nums().get(i), i);

        }

        return ResponseEntity.ok(request.nums());

    }

    @PostMapping("/enteros")
    ResponseEntity<List<Integer>> filterWithOutCeros(@RequestBody OperationRequest request) {

        return ResponseEntity.ok(request.list().stream()
                .filter(num -> num != 0)
                .collect(Collectors.toList()));
    }

    @PostMapping("/validate")
    ResponseEntity<List<Integer>> validate(@RequestBody OperationRequest request) {

        return ResponseEntity.ok(request.list().stream()
                .map(num -> Math.max(0, Math.min(num, 100)))
                .collect(Collectors.toList()));
    }

    @PostMapping("/distinct")
    ResponseEntity<List<Integer>> distinc(@RequestBody OperationRequest request) {

        return ResponseEntity.ok(request.list().stream()
                .distinct()
                .collect(Collectors.toList()));

    }

    @PostMapping("/sum")
    ResponseEntity<Integer> sum(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(operationService.getSum(request.list()));
    }

    @PostMapping("/max")
    ResponseEntity<Integer> max(@RequestBody OperationRequest request) {

        return operationService.getMax(request.list())
                .map(ResponseEntity::ok)  // Return 200 OK if max is present
                .orElseGet(() -> ResponseEntity.noContent().build());  // Return 204 No Content if empty list

    }

    @PostMapping("/min")
    ResponseEntity<Integer> min(@RequestBody OperationRequest request) {

        return operationService.getMin(request.list())
                .map(ResponseEntity::ok)  // Return 200 OK if max is present
                .orElseGet(() -> ResponseEntity.noContent().build());  // Return 204 No Content if empty list

    }

    @GetMapping("/divide")
    ResponseEntity<OperationResponse> divide(@RequestParam Integer a, @RequestParam Integer b) {
        return operationService.getDivision(a, b)
                .map(result -> ResponseEntity.ok(new OperationResponse(result, null, 200)))
                .orElseGet(() -> ResponseEntity.badRequest()
                        .body(new OperationResponse(-1, "Division by zero is not allowed", 400)));
    }

}
