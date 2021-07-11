package API;

import java.io.Serializable;

public class Cerere implements Serializable {

    //private String API.tipCerere;
    private tipCerere cerere;
    private Carte carte;

    public tipCerere getTip() {
        return cerere;
    }

    public void setTip(tipCerere cerere) {
        this.cerere = cerere;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Cerere(tipCerere cerere, Carte carte) {
        this.carte=carte;
        this.cerere = cerere;
    }

    @Override
    public String toString() {
        return "Cerere{" +
                "cerere=" + cerere +
                ", carte=" + carte +
                '}';
    }
}

