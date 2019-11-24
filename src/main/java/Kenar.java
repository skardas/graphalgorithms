/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author achilless
 */
public class Kenar implements Comparable<Kenar> {

    public String from, to;
    public int agirlik;

    public Kenar(String from, String to, int agirlik) {
        this.from = from;
        this.to = to;
        this.agirlik = agirlik;
    }

    @Override
    public int compareTo(Kenar o) {
        return this.agirlik - o.agirlik;
    }
}
