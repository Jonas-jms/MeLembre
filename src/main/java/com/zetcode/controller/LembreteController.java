package com.zetcode.controller;

import com.zetcode.util.TSUtil;
import com.zetcode.model.Lembrete;
import com.zetcode.service.LembreteService;
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
                    model = new LembreteService().inserir(parametros);
                }
            }
        }
        catch (Exception e)
        { JOptionPane.showMessageDialog(null, e); }

        return model;
    }
}