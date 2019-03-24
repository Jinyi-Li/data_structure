/**
 * Merge two sorted arrays!
 *
 * Both array have M elements.
 * Array a has a capacity of M and Array b has a 2M.
 *
 * Merge a into b and return a sorted Array b!
 */

import java.util.*;

public class Solution {

    public void mergeArrays(int[] a, int[] b, int M) {
        if (a == null || b == null || a.length != M || b.length != 2 * M) {
            return;
        }

        int indexA = M - 1;
        int indexB = M - 1;
        int index = 2 * M - 1;
        while (indexA >= 0 && indexB >= 0) {
            if (a[indexA] > b[indexB]) {
                b[index] = a[indexA];
                indexA--;
            } else {
                b[index] = b[indexB];
                indexB--;
            }
            index--;
        }
        while (indexA >= 0) {
            b[index--] = a[indexA--];
        }

        System.out.println(Arrays.toString(b));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {6, 7, 8, 9, 10};
        int[] b = {1, 2, 11, 15, 16, 0, 0, 0, 0, 0};
        solution.mergeArrays(a, b, 5);
    }
}
