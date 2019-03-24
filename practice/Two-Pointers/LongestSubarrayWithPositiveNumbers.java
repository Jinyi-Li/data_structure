import java.util.Arrays;

/*
    Given an array of int, return the longest subarray with consecutive positive numbers.
    
    Input = {1, 2, -3, 2, 3, 4, -6, 1, 2, 3, 4, 5, -8, 5, 6}
    Output = {1,2,3,4,5}
*/
class Solution {
    public int[] getMaxConsecPosiSubstr(int[] data) {
        if (data == null || data.length == 0) {
            return data;
        }

        int maxLength = 0;
        int[] maxLocation = new int[2];
        int start;
        int end = -1;

        while (end < data.length) {
            start = end + 1;
            while (start < data.length && data[start] <= 0) {
                start++;
            }
            end = start;
            while (end < data.length && data[end] > 0) {
                if (maxLength < (end - start + 1)) {
                    maxLength = end - start + 1;
                    maxLocation[0] = start;
                    maxLocation[1] = end;
                }
                end++;
            }
        }
        if (maxLength == 0) {
            return new int[0];
        }
        return Arrays.copyOfRange(data, maxLocation[0], maxLocation[1] + 1);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] data = {1, 2, -3, 2, 3, 4, -6, 1,
                2, 3, 4, 5, -8, 5, 6};
        System.out.println(Arrays.toString(sol.getMaxConsecPosiSubstr(data)));
    }
}