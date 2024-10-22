package com.bosch.happdine.dtos;

import org.springframework.web.multipart.MultipartFile;

public class AvisoDTO {
    private String recado;
    private String timestampp;
    private Integer categoria; // Altere de Categoria para Integer
    private MultipartFile  imagem;// Usando MultipartFile para upload

    // Getters e Setters
    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public String getTimestampp() {
        return timestampp;
    }

    public void setTimestampp(String timestampp) {
        this.timestampp = timestampp;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public MultipartFile  getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile  imagem) {
        this.imagem = imagem;
    }
}