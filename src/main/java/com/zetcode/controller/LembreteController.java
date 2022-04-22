package com.zetcode.controller;

import com.zetcode.util.TSUtil;
import com.zetcode.model.Lembrete;
import com.zetcode.service.LembreteService;
import java.util.List;
import javax.swing.JOptionPane;

public class LembreteController
{
    public Lembrete inserir(Lembrete parametros)
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
                        if(!parametros.getTelefone().contains("71"))
                        JOptionPane.showMessageDialog(null, "Favor informar o DDD do número");
                        else
                        {
                            if(parametros.getTelefone().length()!=10 && parametros.getTelefone().length()!=11)
                            JOptionPane.showMessageDialog(null, "Favor informar um número válido, se atente a quantidade de digitos");
                            else
                            {
                                if(parametros.getMensal()!=0)
                                {
                                    if(parametros.getMensal()<0 || parametros.getMensal()>31)
                                    JOptionPane.showMessageDialog(null, "Favor informar um dia do mês válido!");
                                }
                                else
                                {
                                    parametros.setTelefone("+55"+parametros.getTelefone());
                                    model = new LembreteService().inserir(parametros);
                                }
                            }
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
        return new LembreteService().findAll();
    }    
}