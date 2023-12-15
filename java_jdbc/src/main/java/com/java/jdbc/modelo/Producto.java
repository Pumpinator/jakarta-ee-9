package com.java.jdbc.modelo;

import java.util.Date;

public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private Date fechaRegistro;

    public Producto() {
    }

    public Producto(Integer id, String nombre, int precio, Date fechaRegistro) {
        this.setId(id);
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setFechaRegistro(fechaRegistro);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return id + "\t" + nombre + "\t" + precio + "\t" + fechaRegistro;
    }
}
