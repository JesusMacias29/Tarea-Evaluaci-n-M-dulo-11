package com.tunnel;

public class UserData {
    private String nombre;
    private String apellidos;
    private String curso;

    public UserData(String nombre, String apellidos, String curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getCurso() { return curso; }

    public String getNombreCompleto() {
        return (nombre + " " + apellidos).trim();
    }
}
