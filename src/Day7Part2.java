import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7Part2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Scanner sc;
        try {
            sc = new Scanner(new File("./res/day7.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Go line by line
        // Find start
        // go to next line and check if beam splits
        // if it does, beam goes to idx+1 and idx-1
        // It gives its number of possible paths to the children beams
        // remove the beam at idx
        // sum up all possible paths
        HashMap<Integer, Long> beams = new HashMap<>();
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.contains("S")) {
                beams.put(currLine.indexOf("S"), 1L);
                continue;
            }

            for (int i = 0; i < currLine.length(); i++) {
                if (currLine.charAt(i) == '.') {
                    continue;
                }

                if (beams.containsKey(i)) {
                    beams.put(i-1, beams.getOrDefault(i-1, 0L) + beams.get(i));
                    beams.put(i+1, beams.getOrDefault(i+1, 0L) + beams.get(i));
                    beams.remove(i);
                }
            }
        }
        long sum = 0;
        for (Map.Entry<Integer, Long> entry : beams.entrySet()) {
            sum += entry.getValue();
        }

        System.out.println(sum);
        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }
}
