package API;

import java.io.Serializable;
import java.util.List;

public class Raspuns implements Serializable {
    tipRaspuns tip;
    List<Carte> carti;

    public Raspuns(tipRaspuns tip, List<Carte> carti) {
        this.tip = tip;
        this.carti = carti;
    }

    public tipRaspuns getTip() {
        return tip;
    }

    public void setTip(tipRaspuns tip) {
        this.tip = tip;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    @Override
    public String toString() {
        return "Raspuns{" +
                "tip=" + tip +
                ", carti=" + carti +
                '}';
    }
}
