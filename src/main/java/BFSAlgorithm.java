/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author achilless
 */
public class BFSAlgorithm {
    
    public static void main(String[] args) {
        HashMap<String, Dugum> graph = new HashMap<>();
        String gString[] = new String[]{
            "A;B;4",
            "A;H;8",
            "B;C;8",
            "B;H;11",
            "C;I;2",
            "C;D;7",
            "C;F;4",
            "D;F;14",
            "D;E;9",
            "E;F;10",
            "F;G;2",
            "G;I;6",
            "G;H;1",
            "H;I;7"
        };

        for (String gString1 : gString) {
            String[] tks = gString1.split(";");
            if (!graph.containsKey(tks[0])) {
                graph.put(tks[0], new Dugum(tks[0]));
            }
            if (!graph.containsKey(tks[1])) {
                graph.put(tks[1], new Dugum(tks[1]));
            }
            graph.get(tks[0]).kenarlar.add(new Kenar(tks[0], tks[1], Integer.parseInt(tks[2])));
            graph.get(tks[1]).kenarlar.add(new Kenar(tks[1], tks[0], Integer.parseInt(tks[2])));
        }
        bfs(graph, "A");
    }
    static void bfs(HashMap<String, Dugum> graph, String baslangicDugum)
    {
        Queue<Dugum> q = new LinkedList<>();
        
        HashSet<String> ziyaret = new HashSet<>();
        ziyaret.add(baslangicDugum);
        Dugum root = graph.get(baslangicDugum);
        q.add(root);
        
        while(!q.isEmpty()){
            root = q.poll();           
            System.out.print(root.id + ", ");
            root.kenarlar.sort(Comparator.comparing(kenar->kenar.to));
            for(Kenar k: root.kenarlar){
                if(!ziyaret.contains(k.to)){
                    ziyaret.add(k.to);
                    q.offer(graph.get(k.to));
                }
            }
        }
        
        System.out.println("");
        
    }
}
