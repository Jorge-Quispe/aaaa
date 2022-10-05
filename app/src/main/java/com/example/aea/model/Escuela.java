package com.example.aea.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Escuela {
    @SerializedName("id")
    @Expose
    private int idescuela;

    @SerializedName("nombres")
    @Expose
    private String nombre;

    public Escuela(){

    }

    public Escuela(int idescuela, String nombre) {
        this.idescuela = idescuela;
        this.nombre = nombre;
    }

    public int getIdescuela() {
        return idescuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdescuela(int idescuela) {
        this.idescuela = idescuela;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
