package com.zetcode.service;

import com.zetcode.model.City;
import com.zetcode.repository.CityRepository;
import com.zetcode.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class CityService
{
    @Autowired
    private CityRepository cityRepository;
    
    public City inserir(City parametros)
    {
        BeanProvider.autowire(this);
        return cityRepository.save(parametros);
    }
}
