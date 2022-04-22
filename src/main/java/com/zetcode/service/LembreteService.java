package com.zetcode.service;

import com.zetcode.model.Lembrete;
import com.zetcode.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;
import com.zetcode.repository.LembreteRepository;

public class LembreteService
{
    @Autowired
    private LembreteRepository lembreteRepository;
    
    public Lembrete inserir(Lembrete parametros)
    {
        BeanProvider.autowire(this);
        return lembreteRepository.save(parametros);
    }
}
