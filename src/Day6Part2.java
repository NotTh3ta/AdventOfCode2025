import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Day6Part2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Store each line in an array
        // right pad the array so they have same length
        // Iterate through the array of chars backwards

        Scanner sc;
        String currLine;
        ArrayList<String> lines = new ArrayList<>();
        long res = 0;

        try {
            sc = new Scanner(new File("./res/day6.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // get lines as char arrays
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            lines.add(currLine);
        }

        // get line with longest length
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }

        // pad string lower than max length
        for (int i = 0; i < lines.size(); i++) {
            StringBuilder sb = new StringBuilder(lines.get(i));
            while (sb.length() < maxLength) {
                sb.append(" ");
            }
            lines.set(i, sb.toString());
        }

        // iterate backwards and store nums in a stack
        // if num contains + or * pop stack and do op
        Stack<String> nums = new Stack<>();
        for (int i = maxLength-1; i >= 0; i--) {
            StringBuilder numString = new StringBuilder();

            for (String line : lines) {
                char currChar = line.charAt(i);
                if (currChar == ' ') {
                    continue;
                }
                numString.append(currChar);
            }
            if (!numString.isEmpty()) {
                nums.push(numString.toString());
            }

            if (nums.isEmpty()) {
                continue;
            }

            if (nums.peek().contains("*")) {
                long product = 1;
                while (!nums.isEmpty()) {
                    String curr = nums.pop();
                    if (curr.contains("*")) {
                        curr = curr.substring(0, curr.length()-1);
                        product *= Long.parseLong(curr);
                    } else {
                        product *= Long.parseLong(curr);
                    }
                }
                res += product;
            } else if (nums.peek().contains("+")) {
                long sum = 0;
                while (!nums.isEmpty()) {
                    String curr = nums.pop();
                    if (curr.contains("+")) {
                        curr = curr.substring(0, curr.length()-1);
                        sum += Long.parseLong(curr);
                    } else {
                        sum += Long.parseLong(curr);
                    }
                }
                res += sum;
            }
        }

        System.out.println(res);
        long finish = System.currentTimeMillis();
        System.out.println((finish - start) + " ms");
    }
}
