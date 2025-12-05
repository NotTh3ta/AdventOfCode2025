import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5Part1 {
    public static void main(String[] args) {
        // First set of inputs are the ID ranges
        // Second set are the ingredient IDs
        // Ingredient is fresh if ID is in any of the ranges
        // get n of ingredients that are fresh

        Scanner sc;
        ArrayList<long[]> ranges = new ArrayList<>();
        boolean hasReadAllRanges = false;
        int count = 0;

        try {
            sc = new Scanner(new File("./res/day5.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            // Read all ranges
            while (!hasReadAllRanges) {
                String currLine = sc.nextLine();
                if (currLine.isEmpty()) {
                    hasReadAllRanges = true;
                    break;
                }
                String[] tokenizedCurrLine = currLine.split("-");
                ranges.add(new long[] {Long.parseLong(tokenizedCurrLine[0]), Long.parseLong(tokenizedCurrLine[1])});
            }

            // Go through each id and check if they are within any range
            long currID = Long.parseLong(sc.nextLine());
            for (long[] range : ranges) {
                if (range[0] <= currID && currID <= range[1]) {
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}
