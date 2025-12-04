import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4Part2 {
    public static void main(String[] args) {
        Scanner sc;
        ArrayList<Character> charList = new ArrayList<>();
        int count = 0;
        int lineSize = 0;

        try {
            sc = new Scanner(new File("./res/day4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Make a 1d array
        while (sc.hasNextLine()) {
            String currLine =  sc.nextLine();
            if (lineSize == 0) lineSize = currLine.length();
            for (char c : currLine.toCharArray()) {
                charList.add(c);
            }
        }

        while (true) {
            int rollsRemoved = 0;

            // Go through each item
            for (int i = 0; i < charList.size(); i++) {
                // Only check if current item is toilet paper
                if (charList.get(i) != '@') continue;

                // Get indices to check
                ArrayList<Integer> indicesToCheck = new ArrayList<>();
                for (int j = -1; j < 2; j++) {
                    indicesToCheck.add(i + j*lineSize-1);
                    indicesToCheck.add(i + j*lineSize);
                    indicesToCheck.add(i + j*lineSize+1);
                }

                // Check neighbors, if it has at least 4 neighbors, go next item
                int neighborCount = 0;
                for (int idx: indicesToCheck) {

                    // Validate index
                    if (idx < 0 || idx >= charList.size() || idx == i) {
                        continue;
                    }

                    // Case where item is at left edge of the diagram
                    if (i % lineSize == 0) {
                        if ((idx+1) % lineSize == 0) continue;
                    }

                    // Case where item is at right edge of the diagram
                    if ((i+1) % lineSize == 0) {
                        if (idx % lineSize == 0) continue;
                    }

                    // Check neighbor
                    if (charList.get(idx) == '@') neighborCount++;
                    if (neighborCount == 4) break;
                }
                // If less than 4 neighbors, add to count and replace with '.'
                if (neighborCount < 4) {
                    rollsRemoved++;
                    charList.set(i, '.');
                }
            }

            if (rollsRemoved == 0) break;
            else count += rollsRemoved;
        }

        System.out.println(count);
    }
}
