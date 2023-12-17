package com.java.jdbc.modelo;

import java.util.Date;

public class Producto {
    private Integer id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;
    private Categoria categoria;

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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return id + "\t" + nombre + "\t" + precio + "\t" + fechaRegistro + "\t" + categoria.getNombre();
    }
}
