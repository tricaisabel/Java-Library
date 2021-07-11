package API;

import java.io.Serializable;

public class Carte implements Serializable {
    private String cota;
    private String titlu;
    private String autori;
    private int an;
    private int exemplare;

    public Carte() {
        super();
    }

    public Carte(String cota, String titlu, String autori, int an,int exemplare) {
        super();
        this.cota = cota;
        this.titlu = titlu;
        this.autori = autori;
        this.an = an;
        this.exemplare=exemplare;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getExemplare() {
        return exemplare;
    }

    public void setExemplare(int exemplare) {
        this.exemplare = exemplare;
    }

    @Override
    public String toString() {
        return String.format("%-11s",cota)+' '+String.format("%-30s",titlu)+" "+String.format("%-25s",autori)+" "+String.format("%-5s",an)+' '+String.format("%-3s",exemplare);
    }

    public String toString2() {
        return String.format("%-11s",cota)+' '+String.format("%-30s",titlu)+" "+String.format("%-25s",autori)+" "+String.format("%-5s",an);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Carte)) {
            return false;
        }
        Carte c = (Carte) obj;
        return c.autori==this.autori && c.cota==this.cota && this.titlu==c.titlu && Integer.compare(this.an,c.an)==0;
    }
}
