package Modules;

public class Rese extends Persone {
    int busid;

    public Rese(int id, String nom, String cin, int busid) {
        super(id, nom, cin);
        this.busid = busid;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }
}
