import java.util.Arrays;

public class NextGreaterNumber {
    /**
     * Find the rightmost number (i) that is smaller than its next value!
     * 1234
     * ↑
     * 536974
     * ↑
     * Swap it with the smallest of all numbers greater than it on right.
     * For example, swap 3 with 4, and swap 6 with 7!
     * 537964
     * ↑ ↑
     * Then sort all numbers right to i in ascending order!
     */
    public static void nextGreaterInt(int number) {

        int[] digits = new int[String.valueOf(number).length()];
        int n = number;
        int i = digits.length - 1;
        while (n > 0) {
            digits[i] = n - n / 10 * 10;
            n = n / 10;
            i = i - 1;
        }

        // find the rightmost local minimum P
        int firstMinIndex = -1;
        for (int j = digits.length - 1; j > 0; j--) {
            if (digits[j - 1] < digits[j]) {
                firstMinIndex = j - 1;
                break;
            }
        }
        if (firstMinIndex == -1) {
            System.out.println("No answer");
            return;
        }

        // find the smallest num Q that is at P's right and greater than P
        int index = firstMinIndex + 1;
        int minSoFar = digits[index];
        int toBeSwapped = digits[firstMinIndex];
        while (index < digits.length) {
            if (digits[index] > toBeSwapped && digits[index] < minSoFar) {
                break;
            }
            index++;
        }

        // swap P and Q and sort digits after P
        digits[firstMinIndex] = digits[index];
        digits[index] = toBeSwapped;
        Arrays.sort(digits, firstMinIndex + 1, digits.length);

        // return next greater number
        System.out.println("Next greater number = " + Arrays.toString(digits));
    }

    public static void main(String[] args) {
        nextGreaterInt(536974);
    }
}
