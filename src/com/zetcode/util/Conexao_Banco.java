package com.zetcode.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao_Banco
{
    public static Connection conector()
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
}