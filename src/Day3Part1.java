import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Day3Part1 {
    public static void main(String[] args) {
        Scanner sc;
        int sum = 0;
        String currLine;
        char[] digits;

        try {
            sc = new Scanner(new File("./res/day3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            digits = currLine.toCharArray();
            Stack<Integer> stack = new Stack<>();
            int digitsLeft = digits.length;

            for (char digit: digits) {
                int currDigit = digit - '0';

                if (stack.isEmpty()) {
                    stack.push(currDigit);
                    digitsLeft--;
                    continue;
                }

                if (stack.peek() < currDigit) {
                    boolean isPushed = false;

                    // we can pop as many digits as we want only if
                    // we have enough digits remaining in the input.
                    // or if the stack is empty
                    while (stack.peek() < currDigit) {
                        if (2-stack.size() == digitsLeft) {
                            stack.push(currDigit);
                            isPushed = true;
                            break;
                        }

                        stack.pop();

                        if (stack.isEmpty()) {
                            stack.push(currDigit);
                            isPushed = true;
                            break;
                        }
                    }
                    if (!isPushed) stack.push(currDigit);
                } else if (stack.size() < 2) {
                    stack.push(currDigit);
                }

                digitsLeft--;
            }

            for (int i = 0; i < 2; i++) {
                sum += (int) (stack.pop() * Math.pow(10, i));
            }
        }

        System.out.println(sum);
    }
}
