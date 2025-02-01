package com.caaguirre.develop.service;

import com.caaguirre.develop.entity.Country;
import com.caaguirre.develop.repository.CountryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> listCountries() {
        List<Country> countries = repository.findAll();
        log.info("listCountries: [{}]", countries);
        return countries;
    }

    @Override
    @Transactional
    public Country addCountry(Country country) {
        try {
            country = repository.save(country);
            log.info("addCountry: [{}]", country);
            return country;
        } catch (Exception e) {
            log.warn("addCountry: Exception=[{}]", e.getMessage());
            throw new RuntimeException("Error occurred while adding country", e);
        }
    }


}
