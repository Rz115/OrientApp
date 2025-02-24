package com.devector.raul.seeorienta;


public class clase_escuelas {
    private String imagen;
    private String nombre_escuela,id;

    public clase_escuelas() {
    }

    public clase_escuelas(String logo, String name, String s) {
 this.imagen = logo;
        this.nombre_escuela = name;
        this.id=s;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre_escuela() {
        return nombre_escuela;
    }


public String getId(){
    return id;
}}