import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8Part2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Get all coordinates
        // calc distance between all junctions and add them to a max heap
        // make sure max heap size is <= 1000
        // Do all connections
        // get 3 largest circuits, and mult their sizes together

        Scanner sc;
        ArrayList<long[]> coords = new ArrayList<>();
        ArrayList<Pair<Long, int[]>> pairDistances = new ArrayList<>();
        ArrayList<HashSet<Integer>> circuits = new ArrayList<>();

        try {
            sc = new Scanner(new File("./res/day8.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // get lines
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            coords.add(Arrays.stream(currLine.split(",")).mapToLong(Long::parseLong).toArray());
        }

        // for each unique pair of lines, calc the distance
        for (int i = 0; i < coords.size()-1; i++) {
            long[] iCoords = coords.get(i);
            for (int j = i+1; j < coords.size(); j++) {
                long[] jCoords = coords.get(j);
                long dist = 0;
                for (int k = 0; k < 3; k++) {
                    dist += (long) Math.pow(iCoords[k]-jCoords[k], 2);
                }
                pairDistances.add(new Pair<>(dist, new int[] {i, j}));
            }
        }

        // sort pairDistances in ascending order
        pairDistances.sort(Comparator.comparingLong(o -> o.x));
        long res = 0;
        for (int i = 0; i < pairDistances.size(); i++) {
            Pair<Long, int[]> currPair = pairDistances.get(i);
            ArrayList<Integer> indices = new ArrayList<>();
            for (int j = 0; j < circuits.size()-1; j++) {
                HashSet<Integer> circuit = circuits.get(j);
                if (circuit.contains(currPair.y[0]) || circuit.contains(currPair.y[1])) {
                    indices.add(j);
                }
            }
            if (indices.isEmpty()) {
                HashSet<Integer> newCircuit = new HashSet<>();
                newCircuit.add(currPair.y[0]);
                newCircuit.add(currPair.y[1]);
                circuits.add(newCircuit);
            } else if (indices.size() == 1) {
                circuits.get(indices.getFirst()).add(currPair.y[0]);
                circuits.get(indices.getFirst()).add(currPair.y[1]);
            } else if (indices.size() == 2) {
                circuits.get(indices.getFirst()).addAll(circuits.get(indices.getLast()));
                circuits.remove((int) indices.getLast());
            }

            if (circuits.getFirst().size() == coords.size()) {
                res = coords.get(currPair.y[0])[0] * coords.get(currPair.y[1])[0];
                break;
            }
        }

        System.out.println(res);
        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }

    public static class Pair <X, Y> {
        public final X x;
        public final Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
