package com.meLembre.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AuthorizationService
{
    private final Connection conexao = Conexao_Banco.getConexao1MySQL();
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public int findUser(String username) 
    {
        int flagAtivo = -1;
        
        try
        {
            pst=conexao.prepareStatement("SELECT flagAtivo FROM Users WHERE usuario = ?");
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            if(rs.next())
            flagAtivo = rs.getInt(1);
        }
        catch (SQLException e)
        { JOptionPane.showMessageDialog(null, e); }   
        finally
        {
            try
            { 
              rs.close();
              pst.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
        return flagAtivo;
    }

    public boolean createUser(String username)
    {
        boolean sucesso = true;
        String sql="INSERT INTO Users(usuario, flagAtivo) VALUES (?,1)";
        try
        {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, username);
            pst.executeUpdate();
        }
        catch(SQLException e)
        { 
            sucesso = false;
            JOptionPane.showMessageDialog(null,e);
        }
        finally
        {
            try
            { 
              pst.close();
              rs.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
        
        return sucesso;
    }
    
    public void closeConnection()
    {
        try
        { conexao.close(); }
        catch (SQLException ex)
        { JOptionPane.showMessageDialog(null, ex); }
    }
}
