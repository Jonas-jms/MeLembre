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
    
    public Lembrete save(Lembrete parametros)
    {
        Lembrete model = null;
        try
        {
            if(TSUtil.isEmpty(parametros.getTitulo()))
            JOptionPane.showMessageDialog(null, "O campo Título do lembrete não pode estar em branco");
            else
            {
                if(TSUtil.isEmpty(parametros.getDescricao()))
                JOptionPane.showMessageDialog(null, "O campo Descrição do lembrete não pode estar em branco");
                else
                {
                    if(parametros.getHorario()==null)
                    JOptionPane.showMessageDialog(null, "O campo Horário do lembrete deve ser selecionado");
                    else
                    {
                            if(parametros.getTelefone().contains(" "))
                            parametros.getTelefone().replace(" ", "");
                            if(parametros.getTelefone().contains("-"))
                            parametros.getTelefone().replace("-", "");
                            if(parametros.getTelefone().contains("("))
                            parametros.getTelefone().replace("(", "");
                            if(parametros.getTelefone().contains(")"))
                            parametros.getTelefone().replace(")", "");

                            if(parametros.getTelefone().length()!=10 && parametros.getTelefone().length()!=11)
                            JOptionPane.showMessageDialog(null, "Favor informar um número válido (verificar se DDD foi incluso)");
                            else
                            {
                               parametros.setTelefone("+55"+parametros.getTelefone());
                               model = lembreteService.save(parametros);
                            }
                    }
                }
            }
        }  
        catch (Exception e)
        { JOptionPane.showMessageDialog(null, e); }

        return model;
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