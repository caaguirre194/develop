package com.caaguirre.develop.common;

import com.caaguirre.develop.models.Property;
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

    public static Predicate<User> hasTheIdentification(int id) {
        return user -> user.getIdentification() == id;
    }

    public static Predicate<Property> hasTheOwner(Integer owner) {
        return property -> property.getIdentificationOwner() == owner;
    }

    private static final Random random = new Random();

    private static final List<String> NAMES = List.of("Pedro", "Miguel", "Maria", "Jose", "Juan", "Ana", "Carlos", "Laura", "Luis", "Carmen");
    private static final List<String> LASTNAMES = List.of("Ramirez", "Salas", "Bautista", "Fernandez", "Gonzalez", "Lopez", "Martinez", "Perez", "Sanchez", "Garcia");
    private static final List<String> STREET_NAMES = List.of(
            "Main St", "First Ave", "Oak St", "Maple Ave", "Elm St", "Pine St", "Cedar Blvd", "Washington St", "Lake St", "Hill St"
    );

    private static final List<String> CITIES = List.of(
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"
    );

    private static final List<String> STATES = List.of(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
            "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    );

    public static final List<User> USERS = Stream.generate(Constant::generateRandomUser)
            .limit(5000)
            .collect(Collectors.toList());

    public static final List<Property> PROPERTIES = Stream.generate(Constant::generateRandomProperty)
            .limit(10000)
            .collect(Collectors.toList());

    private static User generateRandomUser() {
        String name = NAMES.get(random.nextInt(NAMES.size()));
        String lastname = LASTNAMES.get(random.nextInt(LASTNAMES.size()));
        return User.builder()
                .id(UUID.randomUUID())
                .identification(random.nextInt(10001)) // Random age between 0 and 10000
                .name(name)
                .lastname(lastname)
                .age(random.nextInt(100)) // Random age between 0 and 99
                .build();
    }

    private static Property generateRandomProperty() {
        return Property.builder()
                .id(UUID.randomUUID())
                .identificationOwner(random.nextInt(10001))
                .address(generateRandomAddress(random))
                .stratum(random.nextInt(6))
                .build();
    }

    public static String generateRandomAddress(Random random) {
        // Generate a random house number (between 1 and 9999)
        int houseNumber = random.nextInt(9999) + 1;

        // Randomly select a street name, city, and state
        String streetName = STREET_NAMES.get(random.nextInt(STREET_NAMES.size()));
        String city = CITIES.get(random.nextInt(CITIES.size()));
        String state = STATES.get(random.nextInt(STATES.size()));

        // Generate a random ZIP code (5 digits)
        String zipCode = String.format("%05d", random.nextInt(100000));

        // Combine all parts into a full address
        return houseNumber + " " + streetName + ", " + city + ", " + state + " " + zipCode;
    }
}
