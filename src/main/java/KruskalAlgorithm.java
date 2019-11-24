import java.util.*;

public class KruskalAlgorithm {
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
            graph.get(tks[0]).komsular.add(new Kenar(tks[0], tks[1], Integer.parseInt(tks[2])));
            if (!isDirected) {
                graph.get(tks[1]).komsular.add(new Kenar(tks[1], tks[0], Integer.parseInt(tks[2])));
            }
        }
        System.out.println(new KruskalAlgorithm(graph).kruskalAlgorithm());

    }

    private HashSet<HashSet<String>> dugumSetleri = new HashSet<>();
    private ArrayList<Kenar> kruskalKenars = new ArrayList<>();
    private HashSet<String> subSetFrom, subSetTo;
    int totalCost = 0;
    public KruskalAlgorithm(HashMap<String, Dugum> graph) {
        for (Dugum next: graph.values())
        {
            for(Kenar kenar: next.komsular){
                HashSet<String> set = new HashSet<>();
                set.add(kenar.from);
                dugumSetleri.add(set);

                set = new HashSet<>();
                set.add(kenar.to);
                dugumSetleri.add(set);
                kruskalKenars.add(kenar);
            }
        }

    }
    public  ArrayList<Kenar> kruskalAlgorithm() {
        ArrayList<Kenar> arananKenarlar = new ArrayList<>();
        kruskalKenars.sort(Comparator.comparing(edge -> edge.agirlik));
        int limit = dugumSetleri.size() - 1;
        for (Kenar kenar : kruskalKenars) {
            if (arananKenarlar.size() == limit) break;

            if (isAcyclic(kenar.from, kenar.to)) {
                dugumSetleri.add(union(subSetFrom, subSetTo));
                dugumSetleri.remove(subSetFrom);
                dugumSetleri.remove(subSetTo);
                arananKenarlar.add(kenar);
                totalCost += kenar.agirlik;
            }

        }
        System.out.printf("Toplam maliyet : %d \n", totalCost);
        return arananKenarlar;
    }

    private HashSet<String> union(HashSet<String> subSet1, HashSet<String> subSet2) {
        HashSet<String> set= new HashSet<>(subSet1);
        set.addAll(subSet2);
        return set;
    }

    private boolean isAcyclic(String from, String to) {
        subSetFrom = new HashSet<>();
        subSetTo = new HashSet<>();
        for (HashSet<String> subSet : dugumSetleri) {
            if (subSet.contains(from)) subSetFrom = subSet;
            if (subSet.contains(to)) subSetTo = subSet;
            if (subSetFrom == subSetTo) return false;
        }
        return true;
    }
}
