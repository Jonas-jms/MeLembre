package com.meLembre.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao_Banco
{
    public static Connection conectorSQLITE()
    {
      Connection conexao = null;
      try
      {
        String url = "jdbc:sqlite:banco/MeLembre_DB.db";
        conexao = DriverManager.getConnection(url);
        return conexao;
      }
      catch(SQLException e)
      { 
        JOptionPane.showMessageDialog(null, e.getMessage());
        return null;
      }
   }
    
   public static Connection getConexao1MySQL()
   {
        Connection conexao = null;  
        try
        {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "sql10.freesqldatabase.com";
            String mydatabase ="sql10495760";       
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            String username = "sql10495760";
            String password = "efjCVEvZPt";
            conexao = DriverManager.getConnection(url, username, password);
            
            return conexao;
         }
         catch (ClassNotFoundException e)
         { 
                System.out.println("O driver expecificado nao foi encontrado.");
                return null;
         }
         catch (SQLException e)
         {
                return getConexao2MySQL();
         }
    }  
   
   private static Connection getConexao2MySQL()
   {
        Connection conexao = null;  
        try
        {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "sql.freedb.tech";
            String mydatabase ="freedb_MeLembre";       
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            String username = "freedb_MeLembreADM";
            String password = "rejgT4HHh$FxH@@";
            conexao = DriverManager.getConnection(url, username, password);
                        
            return conexao;
         }
         catch (ClassNotFoundException e)
         { 
             JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado.");
             return null;
         }
         catch (SQLException e)
         {
             JOptionPane.showMessageDialog(null, "Não foi possivel se conectar a base de dados");
             return null;  
         }
    }  
}