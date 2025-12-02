import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day2 {
    public static void main(String[] args) {
        Day2.solvePart2();
    }

    public static void solvePart1() {
        Scanner sc;
        String allIDs;
        String[] tokenizedIDRanges;
        long sum = 0;

        // Get inputs
        try {
            sc = new Scanner(new File("./res/day2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Split the ranges
        allIDs = sc.nextLine();
        tokenizedIDRanges = allIDs.split(",");

        // Check ranges
        // 101 is a valid id
        // variable sliding window
        for (String range: tokenizedIDRanges) {
            // get left and right numbers of the range
            long left = Long.parseLong(range.split("-")[0]);
            long right = Long.parseLong(range.split("-")[1]);

            for (long i = left; i <= right; i++) {
                String number = String.valueOf(i);
                char[] numberArray = number.toCharArray();

                // Only need to deal with numbers that have an even number of digits
                if (numberArray.length % 2 != 0) continue;

                int start = 0, end = numberArray.length/2 - 1;
                boolean hasRepetition = true;
                while (end < numberArray.length-1) {
                    if (numberArray[start] == numberArray[end + 1]) {
                        start++;
                        end++;
                    } else {
                        hasRepetition = false;
                        break;
                    }
                }

                if (hasRepetition) sum += Long.parseLong(number);
            }
        }

        System.out.println(sum);
    }

    public static void solvePart2() {
        Scanner sc;
        String allIDs;
        String[] tokenizedIDRanges;
        long sum = 0;

        // Get inputs
        try {
            sc = new Scanner(new File("./res/day2.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Split the ranges
        allIDs = sc.nextLine();
        tokenizedIDRanges = allIDs.split(",");

        // Check ranges
        // 101 is a valid id
        // variable sliding window
        for (String range: tokenizedIDRanges) {
            // get left and right numbers of the range
            long left = Long.parseLong(range.split("-")[0]);
            long right = Long.parseLong(range.split("-")[1]);

            for (long i = left; i <= right; i++) {
                String number = String.valueOf(i);
                char[] numberArray = number.toCharArray();
                int nTimesRepeating = 2;

                while (nTimesRepeating <= numberArray.length) {
                    if (numberArray.length % nTimesRepeating != 0) {
                        nTimesRepeating++;
                        continue;
                    }

                    // check if there are nTimesRepeating numbers
                    boolean hasRepetition = true;
                    int start = 0;
                    int end = numberArray.length / nTimesRepeating - 1;
                    while (end < numberArray.length-1) {
                        if (numberArray[start] == numberArray[end + 1]) {
                            start++;
                            end++;
                        } else {
                            hasRepetition = false;
                            break;
                        }
                    }
                    nTimesRepeating++;
                    // If it has at least 2 repetitions, go to the next number.
                    if (hasRepetition) {
                        sum += Long.parseLong(number);
                        break;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
