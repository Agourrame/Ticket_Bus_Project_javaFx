package Modules;

public class History extends Persone{
    String date;
    String start,end,time;

    public History(int id, String nom, String cin, String date, String start, String end,String time) {
        super(id, nom, cin);
        this.date = date;
        this.start = start;
        this.end = end;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
