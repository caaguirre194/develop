package com.caaguirre.develop.repository;

import com.caaguirre.develop.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<Country, Integer> { }
