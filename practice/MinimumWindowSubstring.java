import java.util.*;

class Solution {

    public String minWindowSubstring(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> charRequiredMap = new HashMap<>();
        int requiredChars = 0;
        for(char ch : t.toCharArray()){
            charRequiredMap.put(ch, charRequiredMap.getOrDefault(ch, 0) + 1);
            requiredChars++;
        }

        int minLen = Integer.MAX_VALUE;
        String result = "";

        int numChars = 0;
        int start = 0;
        int end = start;
        while(end < s.length()){
            char lastChar = s.charAt(end);
            // need it in the final string!
            if(charRequiredMap.getOrDefault(lastChar, 0) > 0){
                numChars++;
            }
            charRequiredMap.put(lastChar, charRequiredMap.getOrDefault(lastChar, 0) - 1);

            // have enough number of chars!
            while(numChars >= requiredChars){
                // if shorter than minLen, update result
                if(minLen > (end - start + 1)){
                    minLen = end - start + 1;
                    result = s.substring(start, end + 1);
                }

                // narrow the window by deleting the first char
                char firstChar = s.charAt(start);
                charRequiredMap.put(firstChar, charRequiredMap.get(firstChar) + 1);
                start++;

                // if the first char is a required char, i decrease numChars to find another one
                if(charRequiredMap.get(firstChar) > 0){
                    numChars--;
                }
            }
            end++;
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.minWindowSubstring("cabwefgewcwaefgcf", "cae"));
    }
}