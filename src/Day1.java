import java.util.Scanner;

public class Day1 {
    // Every time we stop at 0, we update counter.
    public static void solvePart1() {
        int nZeros = 0;
        int currN = 50;
        Scanner sc = new Scanner(System.in);
        String currRotation;
        char currDirection;
        int nTicks;

        while (true) {
            // Get rotation direction and amount
            currRotation = sc.nextLine();
            currDirection = currRotation.charAt(0);
            nTicks = Integer.parseInt(currRotation.substring(1));
            nTicks = nTicks % 100;
            if (currDirection == 'L') nTicks *= -1;

            // Update current tick and check if its within bounds
            currN += nTicks;
            if (currN < 0) currN += 100;
            else if (currN > 99) currN -= 100;

            // Increment if we stop at 0
            if (currN == 0) nZeros++;
            System.out.println(nZeros);
        }
    }

    // Every time we pass by 0 or stop at 0 we update counter
    public static void solvePart2() {
        int nZeros = 0;
        int currN = 50;
        Scanner sc = new Scanner(System.in);
        String currRotation;
        char currDirection;
        int nTicks;
        int prevN;

        while (true) {
            // Get rotation direction and amount
            currRotation = sc.nextLine();
            currDirection = currRotation.charAt(0);
            nTicks = Integer.parseInt(currRotation.substring(1));

            // Get how many times we pass by 0 if rotation over 100
            nZeros += nTicks / 100;
            nTicks = nTicks % 100;
            if (currDirection == 'L') nTicks *= -1;

            // Update current tick and check if its within bounds,increment if outside bounds
            // Edge case when currN = 0 before adding nTicks, continue but update currN.
            prevN = currN;
            currN += nTicks;
            if (currN < 0) {
                currN += 100;
                if (prevN == 0) {
                    System.out.println(nZeros);
                    continue;
                }
                nZeros++;
            }
            else if (currN > 99) {
                currN -= 100;
                if (prevN == 0) {
                    System.out.println(nZeros);
                    continue;
                }
                nZeros++;
            }
            else if (currN == 0) {
                nZeros++;
            }

            System.out.println(nZeros);
        }
    }
}
