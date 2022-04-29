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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import io.github.bonigarcia.wdm.WebDriverManager;

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
    
    public void agenda_novo(Lembrete lembrete)
    { verificaLembreteHoje(lembrete); }
    
    public void getProgramacaoAtiva()
    {        
        BeanProvider.autowire(this);
        List<Lembrete> lembretes =  lembreteRepository.findByAtivo("Ativo");
                
        for(Lembrete lembrete: lembretes)
        verificaLembreteHoje(lembrete);
        
    }
    
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
            selenium_call_whatsapp(lembrete);  
            else if((lembrete.getSemanal()==Dia_Semana_Mes.semanal()) && lembrete.getHorario().equals(horas_minutos))
            selenium_call_whatsapp(lembrete);  
            else if(((lembrete.getSemana_personalizado()!=null) && (lembrete.getSemana_personalizado().contains(Dia_Semana_Mes.semanal_personalizado())) && lembrete.getHorario().equals(horas_minutos)))
            selenium_call_whatsapp(lembrete);  
            else if((lembrete.getData()!=null) && (lembrete.getData().equals(Dia_Semana_Mes.unico())) && lembrete.getHorario().equals(horas_minutos))
            selenium_call_whatsapp(lembrete);  
            else if((lembrete.getMensal()==Dia_Semana_Mes.mensal()) && lembrete.getHorario().equals(horas_minutos))
            selenium_call_whatsapp(lembrete);  
        }
    }
    
    private void agendador(LocalTime horario)
    {
       Timer timer = new Timer();
            
       Date date = Date.from(horario.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
            
       MyTimeTask lembrete_call = new MyTimeTask();
       
       timer.schedule(lembrete_call, date);
    }
    
    private void selenium_call_whatsapp(Lembrete lembrete)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
       /* driver.get("https://web.selenium_call_whatsapp.com");
        
        while(driver.findElement(By.id("side"))==null)
        {
            try
            { Thread.sleep(1); }
            catch(InterruptedException ex)
            {
                
            }
        }    */
                
        driver.get("https://web.whatsapp.com/send?phone="+lembrete.getTelefone()+"&text="+lembrete.getDescricao());

        while(driver.findElements(By.id("side")).size()<1 && driver.findElements(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]")).size()<1)
        {
            try
            { Thread.sleep(1); }
            catch(InterruptedException ex)
            {
                
            }
        }
        
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]")).sendKeys(Keys.ENTER);        
        
        try
        { Thread.sleep(10); }
        catch(InterruptedException ex)
        {
                
        }
    }
    
    private class MyTimeTask extends TimerTask
    {
        public void run()
        {
            preparaLembrete();
        }
    }
}
