import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day7Part1 {
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
        // remove the beam at idx
        // increment counter for each split
        Set<Integer> beams = new HashSet<>();
        int splits = 0;
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.contains("S")) {
                beams.add(currLine.indexOf("S"));
                continue;
            }

            for (int i = 0; i < currLine.length(); i++) {
                if (currLine.charAt(i) == '.') {
                    continue;
                }

                if (beams.contains(i)) {
                    beams.remove(i);
                    beams.add(i-1);
                    beams.add(i+1);
                    splits++;
                }
            }
        }

        System.out.println(splits);

        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }
}
