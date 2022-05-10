package com.zetcode.service;

import com.zetcode.model.Lembrete;
import com.zetcode.util.BeanProvider;
import org.springframework.beans.factory.annotation.Autowired;
import com.zetcode.repository.LembreteRepository;
import com.zetcode.util.Dia_Semana_Mes;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class LembreteService
{
    @Autowired
    private LembreteRepository lembreteRepository;
    
    @Autowired
    private WebDriver webDriver;

    public List<Lembrete> lembretes;

    public List<Lembrete> horarios_lembretes = new ArrayList<>();
        
    public void getProgramacaoAtiva()
    {        
        BeanProvider.autowire(this);
        lembretes =  lembreteRepository.findAll();

        for(Lembrete lembrete: lembretes)
        verificaLembreteHoje(lembrete);   
    }
    
    private void verificaLembreteHoje(Lembrete lembrete)
    {                        
        LocalTime horario_bruto = LocalTime.now();     
        LocalTime horas_minutos = LocalTime.of(horario_bruto.getHour(), horario_bruto.getMinute());

        if(lembrete.getAtivo().equals("Ativo"))
        {
            if(lembrete.isDiario())
            {
                if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
                agendador(lembrete); 
            }
            else if(lembrete.getSemanal()==Dia_Semana_Mes.semanal())
            {
                if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
                agendador(lembrete); 
            }
            else if((lembrete.getSemana_personalizado()!=null) && (lembrete.getSemana_personalizado().contains(Dia_Semana_Mes.semanal_personalizado())))
            {
                if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
                agendador(lembrete); 
            }
            else if((lembrete.getData()!=null && (lembrete.getData().equals(Dia_Semana_Mes.unico()))))
            {
                if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
                agendador(lembrete); 
            }
            else if(lembrete.getMensal()==Dia_Semana_Mes.mensal())
            {
                if(horas_minutos.compareTo(lembrete.getHorario())<1 || horas_minutos.compareTo(lembrete.getHorario())==0)
                agendador(lembrete);
            }
        }
    }
    
    private void agendador(Lembrete lembrete)
    {   
       boolean found = horarios_lembretes.stream().anyMatch(p -> p.getHorario().equals(lembrete.getHorario()));
        
       if(!found)
       {
            Timer timer = new Timer();
            
            Date date = Date.from(lembrete.getHorario().atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());

            MyTimeTask lembrete_call = new MyTimeTask();

            timer.schedule(lembrete_call, date);
       }
       
       horarios_lembretes.add(lembrete); 
    }
    
    private class MyTimeTask extends TimerTask
    {
        public void run()
        { 
            LocalTime horario_bruto = LocalTime.now();     
            LocalTime horas_minutos = LocalTime.of(horario_bruto.getHour(), horario_bruto.getMinute());

            List<Long> horarios_finalizados = new ArrayList<>();
            
            for(Lembrete lembrete: horarios_lembretes)
            {  
                if(lembrete.getHorario().compareTo(horas_minutos)==0)
                {
                    preparaLembrete(lembrete);
                    horarios_finalizados.add(lembrete.getId());
                }
            }

            for(Long horariosFinalizados: horarios_finalizados)
            horarios_lembretes.removeIf(hl -> (hl.getId()==horariosFinalizados));
        }
    }
    
    private void preparaLembrete(Lembrete lembrete)
    {      
        if ((lembrete.isDiario()))
        selenium_call_whatsapp(lembrete);
        else if ((lembrete.getSemanal() == Dia_Semana_Mes.semanal()))
        selenium_call_whatsapp(lembrete);
        else if (((lembrete.getSemana_personalizado() != null) && (lembrete.getSemana_personalizado().contains(Dia_Semana_Mes.semanal_personalizado()))))
        selenium_call_whatsapp(lembrete);
        else if ((lembrete.getData() != null) && (lembrete.getData().equals(Dia_Semana_Mes.unico())))
        selenium_call_whatsapp(lembrete);
        else if ((lembrete.getMensal() == Dia_Semana_Mes.mensal()))
        selenium_call_whatsapp(lembrete);
    }
       
    private void selenium_call_whatsapp(Lembrete lembrete)
    {              
        StringSelection stringSelection= new StringSelection(lembrete.getTelefone());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        
        if(!lembrete.getTelefone().contains("+55"))
        findContato(lembrete.getTelefone());
        else
        {
            webDriver.get("https://web.whatsapp.com/send?phone="+lembrete.getTelefone()+"&text=");
            while(webDriver.findElements(By.id("side")).size()<1)
            {}
        }

        String mensagem = "*"+lembrete.getTitulo()+"*"+"\n\n"+lembrete.getDescricao();
        
        stringSelection= new StringSelection(mensagem);
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        
        selenium_sendMsg_whatsapp();  
                
        if(lembrete.getTipo_lembrete().equals("Único"))
        {
            lembrete.setAtivo("Pausado");
            
            save(lembrete);
        }

        Calendar segundos_inicio = Calendar.getInstance();
        segundos_inicio.add(Calendar.SECOND,10);

        while(Calendar.getInstance().compareTo(segundos_inicio)!=0)
        {}
        
        stringSelection = new StringSelection("");
        clipboard.setContents(stringSelection, null);
    }
    
    private void findContato(String nomeContato)
    {
        String xPathContato = "//*[@id=\"side\"]/div[1]/div/label/div/div[2]";
        List <WebElement> elemento = webDriver.findElements(By.xpath(xPathContato));
        
        while(elemento.size()<1)
        {
            try
            { elemento = webDriver.findElements(By.xpath(xPathContato)); }
            catch(Exception e)
            { findContato(nomeContato); }
        }
        
        elemento.get(0).sendKeys(Keys.CONTROL+"v");
        elemento.get(0).sendKeys(Keys.ENTER);        
    }
    
    private void selenium_sendMsg_whatsapp()
    {
        List <WebElement> elemento = webDriver.findElements(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]"));
        
        while(elemento.size()<1)
        {
            try
            { elemento = webDriver.findElements(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]")); }
            catch(Exception e)
            { selenium_sendMsg_whatsapp(); }
        }
        
        elemento.get(0).sendKeys(Keys.CONTROL+"v");
        elemento.get(0).sendKeys(Keys.ENTER);        
    }
    
    public List<Lembrete> findAll()
    { return lembretes; }
    
    public void deleteLembrete(Lembrete lembrete)
    {
       BeanProvider.autowire(this);
       lembreteRepository.deleteById(lembrete.getId());
              
       lembretes.remove(lembrete);
       
       if(horarios_lembretes.contains(lembrete))
       horarios_lembretes.remove(lembrete);
    }
    
    public Lembrete save(Lembrete parametros)
    {
        BeanProvider.autowire(this);
        
        Lembrete novo_lembrete = new Lembrete();
        
        boolean found = lembretes.stream().anyMatch(p -> p.getId().equals(parametros.getId()));
        
        if(found)
        novo_lembrete.setId(parametros.getId());
        
        novo_lembrete = lembreteRepository.save(parametros);

        found = false;
        
        if(novo_lembrete!=null)
        {
            int i;
            
            for(i=0;i<lembretes.size();i++)
            {
                if(lembretes.get(i).getId()==novo_lembrete.getId())
                {
                    lembretes.set(i, novo_lembrete);
                    found = true;
                }
            }

            if(!found)
            lembretes.add(novo_lembrete);

            Long id = novo_lembrete.getId();
            
            horarios_lembretes.removeIf(l -> (l.getId()==id));
            
            verificaLembreteHoje(novo_lembrete);
        }            
        return novo_lembrete;
    }
}
