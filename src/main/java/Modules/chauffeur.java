package Modules;

public class chauffeur {
    int id;
    String nom;
    String cin;

    public chauffeur(int id, String nom, String cin) {
        this.id = id;
        this.nom = nom;
        this.cin = cin;
    }

    public chauffeur(String nom, String cin) {
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
