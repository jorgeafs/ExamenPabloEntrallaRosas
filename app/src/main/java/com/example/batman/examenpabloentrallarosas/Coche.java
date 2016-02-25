package com.example.batman.examenpabloentrallarosas;

/**
 * Created by batman on 4/02/16.
 */
public class Coche {
    private String marca;
    private String modelo;
    private String potencia;
    private String precio;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Coche(String marca, String modelo, String potencia, String precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.precio = precio;
    }
}
