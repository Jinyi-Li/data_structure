/*
Given a string, find the length of the longest substring without repeating characters.
*/
class MlongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;            
        }
        if(s.length() == 1){
            return 1;
        }
        
        // A map of {char->mostRecentOccuringIndex }.
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        char[] chars = s.toCharArray();
        
        // Check each char from beginning to end.
        for(int end = 0; end < chars.length; end++){
            // If has same char within [start, end] range, not qualified.
            if(charIndexMap.containsKey(chars[end])){
                int newStart = charIndexMap.get(chars[end]);
                start = Math.max(start, newStart + 1);                
            }
            // Always update index: if new char, put; if already there, update.
            charIndexMap.put(chars[end], end);
            // Only if longer substring, update.
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;        
    }
}