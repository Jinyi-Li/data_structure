/*
    Add minimum number of characters to make String s a palindrome.
*/
class Solution {
    public String shortestPalindrome(String s) {
        if(s.length() < 2){
            return s;
        }
        
        int right = s.length();
        while(right >= 1){
            if(isPalindrome(s, 0, right - 1)){
                break;
            }
            right--;
        }
            
        StringBuilder res = new StringBuilder();
        res.append(s);
        for(int i = right; i < s.length(); i++){
            res.insert(0, s.charAt(i));
        }
        return res.toString();
    }
    
    private boolean isPalindrome(String s, int left, int right){
        if(right - left < 1){
            return true;
        }
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