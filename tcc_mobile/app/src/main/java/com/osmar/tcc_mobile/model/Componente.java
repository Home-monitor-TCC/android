package com.osmar.tcc_mobile.model;

import java.io.Serializable;

public class Componente implements Serializable {
    private  String nome ,descrição,tipo;
    private  Integer pino;

    private Integer imgEstadoResource;

    private Boolean estado;

    public Componente(String nome, String descrição, String tipo, Integer pino, Integer imgEstadoResource, Boolean estado) {
        this.nome = nome;
        this.descrição = descrição;
        this.tipo = tipo;
        this.pino = pino;
        this.imgEstadoResource = imgEstadoResource;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.nome = tipo;
    }

    public Integer getImgEstadoResource() {
        return imgEstadoResource;
    }

    public void setImgEstadoResource(Integer imgEstadoResource) {
        this.imgEstadoResource = imgEstadoResource;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Integer getPino() {
        return pino;
    }

    public void setPino(Integer pino) {
        this.pino = pino;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
