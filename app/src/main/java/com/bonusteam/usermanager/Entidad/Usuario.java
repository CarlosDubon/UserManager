package com.bonusteam.usermanager.Entidad;

public class Usuario {
    private String carnet;
    private String materia;
    private String catedratico;
    private String nota;

    public Usuario(String carnet, String materia, String catedratico, String nota) {
        this.carnet = carnet;
        this.materia = materia;
        this.catedratico = catedratico;
        this.nota = nota;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
