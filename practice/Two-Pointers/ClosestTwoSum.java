/**
 * Closest two sum.
 *
 * Give two arrays, find one number from each whose sum is less than or equal
 * to the given limit. Find such two numbers with the biggest sum.
 */

import java.util.*;

public class Solution {

    public List<List<Integer>> closestTwoSum(int[] array1, int[] array2, int limit) {
        Arrays.sort(array1);
        Arrays.sort(array2);

        List<List<Integer>> results = new ArrayList<>();
        int pointerA = array1.length - 1;
        int pointerB = array2.length - 1;
        int closestSumSoFar = Integer.MIN_VALUE;

        while (pointerA >= 0 && array1[pointerA] > limit) {
            pointerA--;
        }
        while (pointerB >= 0 && array2[pointerB] > limit) {
            pointerB--;
        }
        int ptrABound = pointerA;

        while (pointerA >= 0 && pointerB >= 0) {
            while (pointerA >= 0 && array1[pointerA] + array2[pointerB] > limit) {
                pointerA--;
            }

            if(pointerA >= 0) {
                int sum = array1[pointerA] + array2[pointerB];
                if (sum >= closestSumSoFar) {
                    if (sum > closestSumSoFar) {
                        results.clear();
                    }

                    List<Integer> newResult = Arrays.asList(array1[pointerA],
                            array2[pointerB]);
                    results.add(newResult);
                    closestSumSoFar = sum;
                }
            }

            pointerA = ptrABound;
            pointerB--;
        }

        System.out.println(results);

        return results;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrayA = {2, 7, 5, 6, 9, 3, 4};
        int[] arrayB = {5, 3, 2, 7, 8, 9, 6, 4};
        solution.closestTwoSum(arrayA, arrayB, 10);

    }
}
