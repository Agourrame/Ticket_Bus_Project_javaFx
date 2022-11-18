package Modules;

public class Persone {
    int id;
    String nom;
    String cin;

    public Persone(int id, String nom, String cin) {
        this.id = id;
        this.nom = nom;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
