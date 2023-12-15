package com.java.jdbc.modelo;

import java.sql.Date;

public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private Date fechaRegistro;

    public Producto() {
    }

    public Producto(int id, String nombre, int precio, Date fechaRegistro) {
        this.setId(id);
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setFechaRegistro(fechaRegistro);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
