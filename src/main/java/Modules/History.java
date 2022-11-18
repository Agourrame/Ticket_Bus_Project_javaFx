package Modules;

public class History extends Persone{
    String date;
    String start,end;

    public History(int id, String nom, String cin, String date, String start, String end) {
        super(id, nom, cin);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
