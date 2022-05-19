package com.zetcode.controller;

import com.zetcode.util.TSUtil;
import com.zetcode.model.Lembrete;
import com.zetcode.service.LembreteService;
import com.zetcode.util.BeanProvider;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;

public class LembreteController
{
    @Autowired
    private LembreteService lembreteService;
    
    public LembreteController()
    { BeanProvider.autowire(this); }
    
    public boolean save(Lembrete parametros)
    {
        boolean sucesso = false;
        try
        {
                if(TSUtil.isEmpty(parametros.getDescricao()))
                JOptionPane.showMessageDialog(null, "O campo Descrição do lembrete não pode estar em branco");
                else
                {
                    if(parametros.getHorario()==null)
                    JOptionPane.showMessageDialog(null, "O campo Horário do lembrete deve ser selecionado");
                    else
                    {
                            if(parametros.getTelefone().contains("+55"))
                            {
                                if(parametros.getTelefone().contains(" "))
                                parametros.getTelefone().replace(" ", "");
                                if(parametros.getTelefone().contains("-"))
                                parametros.getTelefone().replace("-", "");
                                if(parametros.getTelefone().contains("("))
                                parametros.getTelefone().replace("(", "");
                                if(parametros.getTelefone().contains(")"))
                                parametros.getTelefone().replace(")", "");

                                if(parametros.getTelefone().length()!=13 && parametros.getTelefone().length()!=14)
                                JOptionPane.showMessageDialog(null, "Favor informar um número válido (verificar se DDD foi incluso)");
                                else
                                sucesso = lembreteService.save(parametros);
                            }
                            else
                            {
                               if(parametros.getTelefone().isEmpty())
                               JOptionPane.showMessageDialog(null, "O nome do contato não pode estar em branco!");
                               else
                               sucesso = lembreteService.save(parametros);
                            }
                    }
                }
        }  
        catch (Exception e)
        { JOptionPane.showMessageDialog(null, e); }

        return sucesso;
    }
    
    public List<Lembrete> findAll()
    {
        return lembreteService.findAll();
    } 
    
    public void deleteLembrete(Lembrete lembrete)
    {
        lembreteService.deleteLembrete(lembrete);
    }
    
    public void getProgramacaoAtiva()
    {
        lembreteService.getProgramacaoAtiva();
    }
}