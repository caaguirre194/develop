package com.caaguirre.develop.common;

import com.caaguirre.develop.models.User;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constant {

    public static Predicate<User> isOlderThan(int age) {
        return user -> user.getAge() >= age;
    }

    public static Predicate<User> hasTheName(String name) {
        return user -> user.getName().equalsIgnoreCase(name);
    }

    private static final Random random = new Random();

    private static final List<String> NAMES = List.of("Pedro", "Miguel", "Maria", "Jose", "Juan", "Ana", "Carlos", "Laura", "Luis", "Carmen");
    private static final List<String> LASTNAMES = List.of("Ramirez", "Salas", "Bautista", "Fernandez", "Gonzalez", "Lopez", "Martinez", "Perez", "Sanchez", "Garcia");

    public static final List<User> USERS = Stream.generate(Constant::generateRandomUser)
            .limit(5000)
            .collect(Collectors.toList());

    private static User generateRandomUser() {
        String name = NAMES.get(random.nextInt(NAMES.size()));
        String lastname = LASTNAMES.get(random.nextInt(LASTNAMES.size()));
        int age = random.nextInt(100); // Random age between 0 and 99
        return User.builder()
                .id(UUID.randomUUID())
                .name(name)
                .lastname(lastname)
                .age(age)
                .build();
    }
}
