import javax.swing.plaf.IconUIResource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Part1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Scanner sc;
        int numRows = 0;
        String currLine = "";
        int numCols;

        try {
            sc = new Scanner(new File("./res/day6.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // get num of cols and rows
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            numRows++;
        }
        numCols = currLine.split(" +").length;
        String[] ops = currLine.split(" +");
        int[][] nums = new int[numRows-1][numCols];

        // Create new scanner and add data to array
        try {
            sc = new Scanner(new File("./res/day6.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int currRow = 0;
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            if (currLine.contains("*")) continue;
            String[] currLineTokens = currLine.split(" +");

            int currCol = 0;
            for (String token: currLineTokens) {
                if (token.isEmpty()) continue;
                nums[currRow][currCol] = Integer.parseInt(token);
                currCol++;
            }
            currRow++;
        }

        // go through array and do ops
        long res = 0;
        for (int i = 0; i < numCols; i++) {
            if (ops[i].equals("+")) {
                res += nums[0][i] + nums[1][i] + nums[2][i] + nums[3][i];
            } else {
                res += (long) nums[0][i] * nums[1][i] * nums[2][i] * nums[3][i];
            }
        }

        System.out.println(res);

        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }
}
