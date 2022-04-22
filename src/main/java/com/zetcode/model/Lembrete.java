package com.zetcode.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lembrete")
public class Lembrete {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="titulo")
    private String titulo;
    
    @Column(name="descricao")
    private String descricao;
    
    @Column(name="horario")
    private LocalTime horario;
    
    @Column(name="data")
    private LocalDate data;
       
    @Column(name="diario")
    private boolean diario;
    
    @Column(name="semanal")
    private int semanal;
    
    @Column(name="mensal")
    private int mensal;
        
    @Column(name="semana_personalizado")
    private String semana_personalizado;

    public Lembrete() {
    }

    public Lembrete(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

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

    public int getMensal() {
        return mensal;
    }

    public void setMensal(int mensal) {
        this.mensal = mensal;
    }

    public boolean isDiario() {
        return diario;
    }

    public void setDiario(boolean diario) {
        this.diario = diario;
    }
}