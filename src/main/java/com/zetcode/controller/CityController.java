package com.zetcode.controller;

import com.zetcode.util.TSUtil;
import com.zetcode.model.City;
import com.zetcode.service.CityService;
import javax.swing.JOptionPane;

public class CityController
{
    public City inserir(City parametros)
    {
        City model = null;
        try
        {
            boolean parametrosInvalidos = TSUtil.isEmpty(parametros.getName()) || TSUtil.isEmpty(parametros.getPopulation());

            if(parametrosInvalidos)
            JOptionPane.showMessageDialog(null, "Os campos Cidade e População são obrigatórios");
            else
            model = new CityService().inserir(parametros);
        }
        catch (Exception e)
        { JOptionPane.showMessageDialog(null, e); }

        return model;
    }
}