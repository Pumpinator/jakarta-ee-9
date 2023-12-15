package com.java.jdbc.modelo;

import java.util.Date;

public class Producto {
    private Long id;
    private String nombre;
    private Long precio;
    private Date fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, Integer precio, Date fechaRegistro) {
        this.setId(id);
        this.setNombre(nombre);
        this.setPrecio(precio);
        this.setFechaRegistro(fechaRegistro);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = Long.valueOf(precio);
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
