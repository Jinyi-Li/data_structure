class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() < 1 || k < 1){
            return 0;
        }
        
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int numUniqueChar = 0;
        
        // Array for char count with extended ASCII encoding.
        int[] charFreqCount = new int[256];
        Arrays.fill(charFreqCount, 0);
        
        // With left index fixed, move right index to get a substring.
        while(right < s.length()){            
            // Check substring
            if(charFreqCount[s.charAt(right)] == 0){
                numUniqueChar++;                
            }
            charFreqCount[s.charAt(right)]++;
            
            // Update maxLen if possible
            if((numUniqueChar <= k)){
                maxLen = Math.max(maxLen, (right - left + 1));
                
            }else{
                charFreqCount[s.charAt(left)]--;
                // If char at left index is a unique char, update counter.
                if(charFreqCount[s.charAt(left)] == 0){                    
                    numUniqueChar--;
                }
                left++;                                       
            }                                 
            
            // ALWAYS MOVE forward right index.
            right++;
        }
        
        return (right - 1) - left + 1;
    }
}