package com.template;

public class BikeDTO {

    // Atributos da bicicleta (representam a tabela no banco)
    private int id;
    private String marca;
    private String modelo;
    private String tipo;
    private double preco;

    // ===== GETTERS E SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; // define o ID da bicicleta
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca; // define a marca
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo; // define o modelo
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo; // define o tipo (ex: MTB, Speed)
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco; // define o preço
    }
}
