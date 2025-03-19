package com.caaguirre.develop.service;

import com.caaguirre.develop.models.Property;
import com.caaguirre.develop.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.caaguirre.develop.common.Constant.*;
import static com.caaguirre.develop.common.Constant.USERS;

@Service
@Slf4j
public class ExcerciseServiceImpl implements ExcerciseService {

    @Override
    public Mono<List<Property>> properties(Optional<Integer> owner) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultDate = new Date(System.currentTimeMillis());
        log.info("Properties inicio -> [{}]", sdf.format(resultDate));

        return Mono.fromCallable(() -> {

            // Combine filters into one predicate
            Predicate<Property> combinedPredicate = property -> true; // Default predicate that accepts all users

            // Apply the name filter if present
            if (owner.isPresent()) {
                combinedPredicate = combinedPredicate.and(hasTheOwner(owner.get()));
            }

            // Simulate a blocking operation (e.g., a slow database query or external API call)
            Thread.sleep(3000);

            // Perform the filtering and logging
            return PROPERTIES.stream()
                    .filter(combinedPredicate)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                        log.info("Properties fin -> [{}]", sdf.format(new Date(System.currentTimeMillis())));
                        return list;
                    }));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<User>> users(Optional<Integer> id, Optional<Integer> age, Optional<String> name) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultDate = new Date(System.currentTimeMillis());
        log.info("Users inicio -> [{}]", sdf.format(resultDate));

//        return Mono.delay(Duration.ofSeconds(5)) // Non-blocking delay
//                .thenReturn(USERS.stream()
//                        .filter(combinedPredicate)
//                        .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            log.info("Users fin -> [{}]", sdf.format(new Date(System.currentTimeMillis())));
//                            return list;
//                        })))
//                .subscribeOn(Schedulers.boundedElastic());

        return Mono.fromCallable(() -> {

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

            // Apply the id filter if present
            if (id.isPresent()) {
                combinedPredicate = combinedPredicate.and(hasTheIdentification(id.get()));
            }

            // Simulate a blocking operation (e.g., a slow database query or external API call)
            Thread.sleep(5000);
            return USERS.stream()
                    .filter(combinedPredicate)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                        log.info("Users fin -> [{}]", sdf.format(new Date(System.currentTimeMillis())));
                        return list;
                    }));

        }).subscribeOn(Schedulers.boundedElastic());
    }

}
