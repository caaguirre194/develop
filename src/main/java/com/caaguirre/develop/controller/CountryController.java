package com.caaguirre.develop.controller;

import com.caaguirre.develop.entity.Country;
import com.caaguirre.develop.service.CountryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Country>> listCountries(){
        return ResponseEntity.ok(countryService.listCountries());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Country> addCountry(@RequestBody Country country){
        return ResponseEntity.ok(countryService.addCountry(country));
    }

}
