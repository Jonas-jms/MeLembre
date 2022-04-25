package com.zetcode.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Dia_Semana_Mes
{
    public static int semanal()
    {
       DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
  
       return dayOfWeek.getValue();
    }
    
    public static String semanal_personalizado()
    {
       DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
  
       return String.valueOf(dayOfWeek.getValue());
    }
    
    public static int mensal()
    { return LocalDate.now().getDayOfMonth(); }
    
    public static LocalDate unico()
    { return LocalDate.now(); }
    
}
