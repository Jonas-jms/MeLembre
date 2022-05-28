package com.meLembre.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lembrete
{
    private Long id;

    private String titulo;
    
    private String descricao;
    
    private String telefone;
    
    private LocalTime horario;
    
    private String tipo_lembrete;
    
    private LocalDate data;

    private boolean diario;
    
    private int semanal;

    private String semana_personalizado;

    private String ativo;
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo_lembrete() {
        return tipo_lembrete;
    }

    public void setTipo_lembrete(String tipo_lembrete) {
        this.tipo_lembrete = tipo_lembrete;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getSemana_personalizado() {
        return semana_personalizado;
    }

    public void setSemana_personalizado(String semana_personalizado) {
        this.semana_personalizado = semana_personalizado;
    }

    public int getSemanal() {
        return semanal;
    }

    public void setSemanal(int semanal) {
        this.semanal = semanal;
    }

    public boolean isDiario() {
        return diario;
    }

    public void setDiario(boolean diario) {
        this.diario = diario;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}