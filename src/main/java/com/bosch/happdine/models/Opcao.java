package com.bosch.happdine.models;

public enum Opcao {
    RESTAURANTE("opcao"), WEBSITE("opcao");
    private String opcao;

    Opcao(String opcao){this.opcao = opcao;}
    public String getOpcao(){return opcao;}
}
