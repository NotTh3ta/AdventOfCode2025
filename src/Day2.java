import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Day2 {
    public static void main(String[] args) {
        Day2.solvePart1();
    }

    public static void solvePart1() {
        Scanner sc;
        String allIDs;
        String[] tokenizedIDRanges;
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
        for (String range: tokenizedIDRanges) {
            // get left and right numbers of the range
            long left = Long.parseLong(range.split("-")[0]);
            long right = Long.parseLong(range.split("-")[1]);
            for (long i = left; i <= right; i++) {

            }
        }
    }
}
