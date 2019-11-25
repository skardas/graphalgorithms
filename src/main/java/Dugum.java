/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author achilless
 */
public class Dugum {

    String id;
    List<Kenar> kenarlar = new ArrayList<>();

    public Dugum(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
    

}
