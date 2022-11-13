package project.autocar_ticket;

public class Rese {

    String Name,Cin,Dtae,Vd,Va,Price;
    Integer NBus;

    public Rese(String name, String cin, String date, String vd, String va, Integer NBus, String price) {
        Name = name;
        Cin = cin;
        Dtae = date;
        Vd = vd;
        Va = va;
        this.NBus = NBus;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getDtae() {
        return Dtae;
    }

    public void setDtae(String dtae) {
        Dtae = dtae;
    }

    public String getVd() {
        return Vd;
    }

    public void setVd(String vd) {
        Vd = vd;
    }

    public String getVa() {
        return Va;
    }

    public void setVa(String va) {
        Va = va;
    }

    public Integer getNBus() {
        return NBus;
    }

    public void setNBus(Integer NBus) {
        this.NBus = NBus;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
