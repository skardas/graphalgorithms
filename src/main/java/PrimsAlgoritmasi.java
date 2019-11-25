/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author achilless
 */
public class PrimsAlgoritmasi {

    public static void main(String[] args) {

        boolean isDirected = false;
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
            if (!isDirected) {
                graph.get(tks[1]).kenarlar.add(new Kenar(tks[1], tks[0], Integer.parseInt(tks[2])));
            }
        }
        System.out.println(minCostOnPrimsAlgorithm(graph, "A"));

    }

    public static int minCostOnPrimsAlgorithm(HashMap<String, Dugum> graph, String baslangicDugum) {

        HashSet<String> ziyaret = new HashSet<>();
        ziyaret.add(baslangicDugum);

        PriorityQueue<Kenar> pQueue = new PriorityQueue<>(100, Comparator.comparingInt(o -> o.agirlik));

        graph.get(baslangicDugum).kenarlar.forEach((k) -> {
            pQueue.add(k);
        });
        int minCost = 0;
        while (!pQueue.isEmpty()) {
            Kenar kenar = pQueue.poll();

            if (ziyaret.contains(kenar.to)) {
                continue;
            }
            ziyaret.add(kenar.to);

            minCost += kenar.agirlik;

            System.out.print(kenar.from + "-" + kenar.to + ", ");
            graph.get(kenar.to).kenarlar.forEach((k) -> {
                pQueue.add(k);
            });

        }
        System.out.println("");

        return minCost;
    }

}
