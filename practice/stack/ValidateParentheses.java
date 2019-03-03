class ValidateParentheses {
    public boolean isValid(String s) {
        if(s.length() % 2 != 0){
            return false;
        }
        
        Stack<Character> leftParens = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(isLeftParen(chars[i])){
                leftParens.push(chars[i]);
            }else{
                if(leftParens.isEmpty()){
                   return false; 
                }
                char left = leftParens.pop();                
                if(!isMatch(left, chars[i])){
                    return false;
                }
            }
        }
        if(leftParens.isEmpty()){
            return true;
        }
        return false;
    }
    
    private boolean isLeftParen(char ch){
        return ch == '(' || ch == '[' || ch == '{';
    }
    
    private boolean isMatch(char left, char right){
        return (left == '(' && right == ')') 
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }
}