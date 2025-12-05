import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part2 {
    public static void main(String[] args) {
        // First set of inputs are the ID ranges
        // Second set are the ingredient IDs
        // Ingredient is fresh if ID is in any of the ranges
        // No longer need second set
        // get number of ingredients that can be considered fresh from id ranges only
        long start = System.currentTimeMillis();

        Scanner sc;
        ArrayList<long[]> ranges = new ArrayList<>();
        ArrayList<long[]> reducedRanges = new ArrayList<>();
        long nUniqueIDs = 0;

        try {
            sc = new Scanner(new File("./res/day5.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Read all ranges
        while (true) {
            String currLine = sc.nextLine();
            if (currLine.isEmpty()) {
                break;
            }
            String[] tokenizedCurrLine = currLine.split("-");
            ranges.add(new long[] {Long.parseLong(tokenizedCurrLine[0]), Long.parseLong(tokenizedCurrLine[1])});
        }

        // sort ranges by start value
        ranges.sort(Comparator.comparingLong(o -> o[0]));
        int i = -1;
        for (long[] range : ranges) {
            if (reducedRanges.isEmpty()) {
                reducedRanges.add(range);
                i++;
                continue;
            }

            // compare vals
            if (range[0] == reducedRanges.get(i)[0]) {
                reducedRanges.get(i)[1] = Math.max(range[1], reducedRanges.get(i)[1]);
            } else {
                if (range[0] <= reducedRanges.get(i)[1]) {
                    reducedRanges.get(i)[1] = Math.max(range[1], reducedRanges.get(i)[1]);
                } else {
                    reducedRanges.add(range);
                    i++;
                }
            }
        }

        // calculate n of unique ids
        for (long[] range : reducedRanges) {
            nUniqueIDs += range[1] - range[0] + 1;
        }
        System.out.println(nUniqueIDs);

        long finish = System.currentTimeMillis();
        System.out.println(finish-start + " ms");
    }
}
