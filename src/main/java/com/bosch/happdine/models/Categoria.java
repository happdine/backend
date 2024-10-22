package com.bosch.happdine.models;

public enum Categoria {
    IMPORTANTE(1),
    ALERTA(2),
    INFORMATIVO(3);

    private final int value;

    Categoria(int value) {
        this.value = value;
    }

    public static Categoria fromValue(int value) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.value == value) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}