public class MisPalindrome {
    /**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        if(s == null){
            return false;
        }

        // WARN: 询问面试官该返回什么
        if(s.length() == 0){
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        // ERROR: 没check左右指针的范围。 ArrayIndexOutOfBound Exception
        while(left <= right && right >= 0 && left < s.length()){
            char le = s.charAt(left);
            char ri = s.charAt(right);
            
            if(!isValidChar(le)){
                left++;
                continue;
            }
            if(!isValidChar(ri)){
                // ERROR: right pointer应该是递减而不是递增。
                //right++;
                right--;
                continue;
            }

            // ERROR: 忘记处理case insensitive了。
            // if(le != ri){
            if(Character.toLowerCase(le) != Character.toLowerCase(ri)){
                return false;
            }

            // ERROR: 忘记更新指针位置了。
            left++;
            right--;
        }
        return true;
    }
    
    // 封装，很好
    private boolean isValidChar(char ch){
        if(Character.isDigit(ch) || Character.isLetter(ch)){
            return true;
        }
        return false;
    }
}