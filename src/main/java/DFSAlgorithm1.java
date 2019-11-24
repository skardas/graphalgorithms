/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author achilless
 */
public class DFSAlgorithm1 {
    
    public static void main(String[] args) {
        HashMap<String, Dugum> graph = new HashMap<>();
        String gString[] = new String[]{
            "A;B;8",
            "A;H;8",
            "A;D;8",
            "B;C;8",
            "B;E;8",
            "D;F;8"
         };

        for (String gString1 : gString) {
            String[] tks = gString1.split(";");
            if (!graph.containsKey(tks[0])) {
                graph.put(tks[0], new Dugum(tks[0]));
            }
            if (!graph.containsKey(tks[1])) {
                graph.put(tks[1], new Dugum(tks[1]));
            }
            graph.get(tks[0]).komsular.add(new Kenar(tks[0], tks[1], Integer.parseInt(tks[2])));
            graph.get(tks[1]).komsular.add(new Kenar(tks[1], tks[0], Integer.parseInt(tks[2])));
        }
        dfs(graph, "A");
    }
    static void dfs(HashMap<String, Dugum> graph, String baslangicDugum)
    {
        Stack<Dugum> s = new Stack<>();
        
        HashSet<String> ziyaret = new HashSet<>();
        Dugum root = graph.get(baslangicDugum);
        s.add(root);
        ziyaret.add(root.id);
        System.out.print(root.id + ", ");

        do{
            root = s.peek();
            boolean isPushed = false;          
            for (int i = 0; i < root.komsular.size(); i++) {
                Kenar k = root.komsular.get(i);
                if(!ziyaret.contains(k.to)){
                    s.push(graph.get(k.to));
                    ziyaret.add(k.to);
                    isPushed = true;
                    System.out.print(k.to + ", ");
                    //System.out.println(s);
                    break;
                }
                
            }
            if(!isPushed)
            {
                root = s.pop();
            }
        }while(!s.isEmpty());
        
        System.out.println("");
        
    }
}
