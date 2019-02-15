class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        if(s.length() == 1){
            return s;
        }
        
        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++){
            // start from same index - get palin with odd length
            int oddPalinLen = expand(s, i, i);
            // start from adjacent index - get palin with even length
            int evenPalinLen = expand(s, i, i+1);
            // use the longer length as current length
            int len = Math.max(oddPalinLen, evenPalinLen);
            
            // if found longer palindrome, update two bounds
            if(len > right - left){
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }            
        }
        return s.substring(left, right + 1);
    }
    
    private int expand(String s, int leftBound, int rightBound){
        int left = leftBound; 
        int right = rightBound;
        while(left >= 0 && right < s.length()
             && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;        
    }
}