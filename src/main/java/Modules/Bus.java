package Modules;

public class Bus {
    int id;
    String start,end,time;
    int prix;
    int nplace;
    String date;
    String chauffeur;

    public Bus(int id,String start, String end,String time, int prix, int nplace, String date, String chauffeur) {
        this.id=id;
        this.start = start;
        this.end = end;
        this.time=time;
        this.prix = prix;
        this.nplace = nplace;
        this.date = date;
        this.chauffeur = chauffeur;
    }
    public Bus(int id,String start, String end, int prix, int nplace, String date,String time) {
        this.id=id;
        this.start = start;
        this.end = end;
        this.prix = prix;
        this.nplace = nplace;
        this.date = date;
        this.time=time;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNplace() {
        return nplace;
    }

    public void setNplace(int nplace) {
        this.nplace = nplace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
