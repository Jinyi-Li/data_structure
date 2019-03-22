/*
    At most remove 1 character.
*/
class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        boolean hasSkipped = false;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return isValidPalinSubstring(s, left + 1, right)
                    || isValidPalinSubstring(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
    
    private boolean isValidPalinSubstring(String s, int left, int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}