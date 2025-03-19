package com.caaguirre.develop.service;

import com.caaguirre.develop.models.Property;
import com.caaguirre.develop.models.User;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ExcerciseService {

    Mono<List<Property>> properties(Optional<Integer> owner) throws InterruptedException;

    Mono<List<User>> users(Optional<Integer> id, Optional<Integer> age, Optional<String> name) throws InterruptedException;

}
