class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }     
        
        int left = 0;
        int right = left;
        int maxLen = 0;
        char[] chars = s.toCharArray();
        
        // Use a map of {char -> index} to store the first index
        // that each char appears in the current substring.
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        while(right < chars.length) {
            char lastChar = chars[right];
            
            // If find duplicate char, this substring needs abortion.
            if(charIndexMap.containsKey(lastChar)) {
                // start a new substring by eliminating the first "lastChar".
                left = Math.max(left, charIndexMap.get(lastChar) + 1);
            }
            charIndexMap.put(chars[right], right);
            maxLen = Math.max(maxLen, right - left + 1);            
            right++;
        }
        return maxLen;
    }
}