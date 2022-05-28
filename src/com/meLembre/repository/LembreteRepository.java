package com.meLembre.repository;

import com.meLembre.model.Lembrete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.meLembre.util.Conexao_Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LembreteRepository
{
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public List<Lembrete> findAll() 
    {
        List<Lembrete> lembretes = new ArrayList<>();
        Lembrete lembrete;
        
        try
        {
            conexao = Conexao_Banco.conectorSQLITE();
            pst = conexao.prepareStatement("SELECT * FROM LEMBRETE");
            rs = pst.executeQuery();

            while(rs.next())
            {
               lembrete = new Lembrete();
               lembrete.setId(rs.getLong(1));
               lembrete.setAtivo(rs.getString(2));
               
               if(rs.getDate(3)!=null)
               lembrete.setData(rs.getDate(3).toLocalDate());
            
               lembrete.setDescricao(rs.getString(4));
               lembrete.setDiario(rs.getBoolean(5));
               lembrete.setHorario(rs.getTime(6).toLocalTime());
               lembrete.setSemana_personalizado(rs.getString(7));
               lembrete.setSemanal(rs.getInt(8));
               lembrete.setTelefone(rs.getString(9));
               lembrete.setTipo_lembrete(rs.getString(10));
               lembrete.setTitulo(rs.getString(11));
               
               lembretes.add(lembrete);
            }
        }
        catch (SQLException e)
        { JOptionPane.showMessageDialog(null, e); }   
        finally
        {
            try
            { 
              rs.close();
              pst.close();
              conexao.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
        return lembretes;
    }

    public void deleteById(Long id)
    {
        try
        {
            conexao = Conexao_Banco.conectorSQLITE();
            pst=conexao.prepareStatement("DELETE FROM LEMBRETE WHERE ID=?");
            pst.setLong(1, id);
            pst.executeUpdate();
        }
        catch(SQLException e)
        { JOptionPane.showMessageDialog(null,e); }
        finally
        {
            try
            { 
              pst.close();
              conexao.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
    }

    public Long save(Lembrete lembrete)
    {
        Long id = null;
        String sql="INSERT INTO LEMBRETE(ativo, data,descricao, diario, horario, semana_personalizado, semanal, telefone, tipo_lembrete, titulo) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try
        {
            conexao = Conexao_Banco.conectorSQLITE();
            pst=conexao.prepareStatement(sql);
            pst.setString(1, lembrete.getAtivo());
            
            if(lembrete.getData()!=null)
            pst.setDate(2, java.sql.Date.valueOf(lembrete.getData()));
            else 
            pst.setDate(2, null);
            
            pst.setString(3, lembrete.getDescricao());
            pst.setBoolean(4, lembrete.isDiario());
            pst.setTime(5, java.sql.Time.valueOf(lembrete.getHorario()));
            pst.setString(6, lembrete.getSemana_personalizado());
            pst.setInt(7, lembrete.getSemanal());
            pst.setString(8, lembrete.getTelefone());
            pst.setString(9, lembrete.getTipo_lembrete());
            pst.setString(10, lembrete.getTitulo());
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next())
            id = rs.getLong(1);
        }
        catch(SQLException e)
        { JOptionPane.showMessageDialog(null,e); }
        finally
        {
            try
            { 
              pst.close();
              rs.close();
              conexao.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
        
        return id;
    }

    public boolean update(Lembrete lembrete)
    {
        boolean sucesso=false;
        try
        {
            conexao = Conexao_Banco.conectorSQLITE();
            pst=conexao.prepareStatement("UPDATE LEMBRETE set ativo=?, data=?, descricao=?, diario=?, horario=?, semana_personalizado=?, semanal=?, telefone=?, tipo_lembrete=?, titulo=? where id=?");
            pst.setString(1, lembrete.getAtivo());
            
            if(lembrete.getData()!=null)
            pst.setDate(2, java.sql.Date.valueOf(lembrete.getData()));
            else 
            pst.setDate(2, null);
            
            pst.setString(3, lembrete.getDescricao());
            pst.setBoolean(4, lembrete.isDiario());
            pst.setTime(5, java.sql.Time.valueOf(lembrete.getHorario()));
            pst.setString(6, lembrete.getSemana_personalizado());
            pst.setInt(7, lembrete.getSemanal());
            pst.setString(8, lembrete.getTelefone());
            pst.setString(9, lembrete.getTipo_lembrete());
            pst.setString(10, lembrete.getTitulo());
            pst.setLong(11, lembrete.getId());
            pst.executeUpdate();
            
            sucesso=true;
        }
        catch(SQLException e)
        { JOptionPane.showMessageDialog(null,e); }
        finally
        {
            try
            { 
              pst.close();
              conexao.close();
            }
            catch (SQLException e)
            { JOptionPane.showMessageDialog(null,e); }
        } 
        return sucesso;
    }
}