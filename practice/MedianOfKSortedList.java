/*
    Median of K Sorted Arrays

    There are k sorted arrays nums. Find the median of the given k sorted arrays.

    Example
    Given nums = [[1],[2],[3]], return 2.00.

    Notice
    The length of the given arrays may not equal to each other.
    The elements of the given arrays are all positive number.
    Return 0 if there are no elements in the array.

    思路：先确定一共有多少数字 => 从而确定中位数在哪，是n/2或(n/2, n/2 + 1).
    然后由于所有数字的范围已知(0, Integer.MAX_VALUE], 可以用这个范围作为binary search的boundary。
    对nums里的k个list：用binary search找它们中大于mid的个数，sum += subtotal。然后看sum是否大于k。

    binary_search(nums, start=0, end=Integer.MAX_VALUE, value=sum)
        binary_search(list, start=0, end=list.length-1, value=实际value)

*/
import java.util.*;

public class Solution {

    public double getMedian(int[][] nums) {
        int totalNumberOfElems = getTotalNumberOfElements(nums);
        if (totalNumberOfElems % 2 == 0) {
            return (findK(nums, totalNumberOfElems / 2) / 2.0 
                + findK(nums, totalNumberOfElems / 2 + 1)) / 2.0; // avoid overflow
        }
        return findK(nums, totalNumberOfElems / 2 + 1);
    }

    private int getTotalNumberOfElements(int[][] nums) {
        int count = 0;
        for (int[] list : nums) {
            count += list.length;
        }
        return count;
    }

    private int findK(int[][] nums, int k) {
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (start < end - 1) {
            int mid = start + (end - start) / 2;
            int gteCount = getGteCount(nums, mid);
            if (gteCount == k) {
                return mid;
            } else if (gteCount > k) {
                // if too many numbers, shrink the interval by finding a greater number
                start = mid;
            } else {
                end = mid;
            }
        }
        return -1;
    }

    private int getGteCount(int[][] nums, int upperBound) {
        // how many numbers >= the number "mid"
        int total = 0;
        for (int[] list : nums) {
            total += getGteCountInList(list, upperBound);
        }
        return total;
    }

    private int getGteCountInList(int[] list, int target) {
        int left = 0;
        int right = list.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (list[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (list[left] >= target) {
            return list.length - left;
        }
        if (list[right] >= target) {
            return list.length - right;
        }
        return 0;
    }

    public static void main(String args[]) {
        int[][] nums = {
                {0, 1, 3, 5, 7, 9},
                {0, 2, 4, 6, 7, 8, 10}
        };
        // 0 0 1 2 3 4 5 6 7 7 8 9 10 => 13

        Solution solution = new Solution();
        System.out.println(solution.getMedian(nums));
    }
}
