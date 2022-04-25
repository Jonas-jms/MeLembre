package com.zetcode.service;

import com.zetcode.model.Lembrete;
import com.zetcode.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;
import com.zetcode.repository.LembreteRepository;
import com.zetcode.util.Dia_Semana_Mes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class LembreteService
{
    @Autowired
    private LembreteRepository lembreteRepository;
    
    /*@Autowired
    private ImplementacaoLembreteRepository implementacaoLembreteRepository;*/
        
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
    
    public void agenda_novo(Lembrete lembrete)
    { verificaLembreteHoje(lembrete); }
    
    public void getProgramacaoAtiva()
    {        
        BeanProvider.autowire(this);
        List<Lembrete> lembretes =  lembreteRepository.findByAtivo("Ativo");
                
        for(Lembrete lembrete: lembretes)
        verificaLembreteHoje(lembrete);
        
    }
       
   /* public List <Lembrete> getLembretesAtivos()
    {
        BeanProvider.autowire(this);
        return implementacaoLembreteRepository.findAtivoOrdenado();
    }*/
    
    public void verificaLembreteHoje(Lembrete lembrete)
    {                        
        LocalTime horario_bruto = LocalTime.now();     
        LocalTime horas_minutos = LocalTime.of(horario_bruto.getHour(), horario_bruto.getMinute());
        
        if(lembrete.isDiario())
        {
            if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
            agendador(lembrete.getHorario()); 
        }
        else if(lembrete.getSemanal()==Dia_Semana_Mes.semanal())
        {
            if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
            agendador(lembrete.getHorario()); 
        }
        else if((lembrete.getSemana_personalizado()!=null) && (lembrete.getSemana_personalizado().contains(Dia_Semana_Mes.semanal_personalizado())))
        {
            if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
            agendador(lembrete.getHorario()); 
        }
        else if((lembrete.getData()!=null && (lembrete.getData().equals(Dia_Semana_Mes.unico()))))
        {
            if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
            agendador(lembrete.getHorario()); 
        }
        else if(lembrete.getMensal()==Dia_Semana_Mes.mensal())
        {
            if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
            agendador(lembrete.getHorario()); 
        }
    }
    
    public void preparaLembrete()
    {                        
        LocalTime horario_bruto = LocalTime.now();     
        LocalTime horas_minutos = LocalTime.of(horario_bruto.getHour(), horario_bruto.getMinute());
        
        BeanProvider.autowire(this);
        List <Lembrete> lembretes = lembreteRepository.findByAtivo("Ativo");
        
        for (Lembrete lembrete: lembretes)
        {
            if((lembrete.isDiario()) && lembrete.getHorario().equals(horas_minutos))
            whatsapp(lembrete);  
            else if((lembrete.getSemanal()==Dia_Semana_Mes.semanal()) && lembrete.getHorario().equals(horas_minutos))
            whatsapp(lembrete);  
            else if(((lembrete.getSemana_personalizado()!=null) && (lembrete.getSemana_personalizado().contains(Dia_Semana_Mes.semanal_personalizado())) && lembrete.getHorario().equals(horas_minutos)))
            whatsapp(lembrete);  
            else if((lembrete.getData()!=null) && (lembrete.getData().equals(Dia_Semana_Mes.unico())) && lembrete.getHorario().equals(horas_minutos))
            whatsapp(lembrete);  
            else if((lembrete.getMensal()==Dia_Semana_Mes.mensal()) && lembrete.getHorario().equals(horas_minutos))
            whatsapp(lembrete);  
        }
    }
    
    private void agendador(LocalTime horario)
    {
       Timer timer = new Timer();
            
       Date date = Date.from(horario.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
            
       MyTimeTask lembrete_call = new MyTimeTask();
       
       timer.schedule(lembrete_call, date);
    }

    private void whatsapp(Lembrete lembrete)
    {
        JOptionPane.showMessageDialog(null, "Deu o horario otário");
    }
    
    private class MyTimeTask extends TimerTask
    {
        public void run()
        {
            preparaLembrete();
        }
    }
}
