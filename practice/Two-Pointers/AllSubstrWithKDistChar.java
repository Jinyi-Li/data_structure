/**
 * Sliding windows.
 */

import java.util.*;

public class Solution {

    public List<String> getSubarraysWithNoDup(String data, int k) {
        if (data == null || data.length() < k) {
            return null;
        }

        List<String> results = new ArrayList<>();
        char[] chars = data.toCharArray();
        int left = 0;
        int right = left;

        // counter for each char in extended ASCII encoding.
        int[] charFreqCount = new int[256];

        int uniqueChar = 0;

        while (left <= right && right < chars.length) {
            char lastChar = chars[right];
            if (charFreqCount[lastChar] == 0) {
                uniqueChar++;
            }
            charFreqCount[lastChar]++;

            if (uniqueChar == k) {
                results.add(data.substring(left, right + 1));                
            } else if (uniqueChar > k) {
                char firstChar = chars[left];
                charFreqCount[firstChar]--;

                if (charFreqCount[firstChar] == 0) { // if a unique char
                    uniqueChar--;
                }
                left++;
            } 

            // Always MOVE rightPointer forward!
            right++;
        }
        return results;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String data = "eceba";
        solution.getSubarraysWithNoDup(data, 2);
    }
}
