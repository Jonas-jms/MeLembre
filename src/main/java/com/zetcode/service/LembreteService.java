package com.zetcode.service;

import com.zetcode.model.Lembrete;
import com.zetcode.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;
import com.zetcode.repository.LembreteRepository;
import java.util.List;

public class LembreteService
{
    @Autowired
    private LembreteRepository lembreteRepository;
    
    public Lembrete inserir(Lembrete parametros)
    {
        BeanProvider.autowire(this);
        return lembreteRepository.save(parametros);
    }

    public List<Lembrete> findAll()
    {
        BeanProvider.autowire(this);
        return lembreteRepository.findAll();
    }

    public void deleteLembrete(Lembrete lembrete)
    {
       BeanProvider.autowire(this);
       lembreteRepository.deleteById(lembrete.getId());
    }
}
