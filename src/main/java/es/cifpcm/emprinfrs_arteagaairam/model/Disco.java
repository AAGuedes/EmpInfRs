/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.emprinfrs_arteagaairam.model;

/**
 *
 * @author Airam
 */
public class Disco {
    
    private int idDisco;
    private String titulo;
    private Float agno;
    private String interprete;

    public Disco() {
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getAgno() {
        return agno;
    }

    public void setAgno(Float agno) {
        this.agno = agno;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

}
