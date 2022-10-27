package com.example.apped.Modelos;

public class Usuario {
    int Id;
    String Nombre, ApellidoP, ApellidoM, Email, Password;

    public Usuario(){}

    public Usuario( String nombre, String apellidoP, String apellidoM, String email, String password) {
        Nombre = nombre;
        ApellidoP = apellidoP;
        ApellidoM = apellidoM;
        Email = email;
        Password = password;
    }



    public boolean isNull(){
        if(Nombre.equals("")&&ApellidoP.equals("")&&ApellidoM.equals("")&&Email.equals("")&&Password.equals("")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoP='" + ApellidoP + '\'' +
                ", ApellidoM='" + ApellidoM + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String apellidoP) {
        ApellidoP = apellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String apellidoM) {
        ApellidoM = apellidoM;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

