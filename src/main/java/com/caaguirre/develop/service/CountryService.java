package com.caaguirre.develop.service;

import com.caaguirre.develop.entity.Country;

import java.util.List;


public interface CountryService {

    List<Country> listCountries();

    Country addCountry(Country country);

}
