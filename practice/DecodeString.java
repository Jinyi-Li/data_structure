/*
    decode a string.

    3[a2[c]2[b]] => accbbaccbbaccbb

    number = how many times to repeat the string
    [...] = a pattern unit
    3[a] = you need to print aaa
    2[a2[b]] = you need to print abbabb
*/
class Solution {
    public String decodeString(String s) {
        
        // 12[ab] = repeat "12" times
        Stack<Integer> countStack = new Stack<>();
        
        // "ab" = repeat this unit as a whole
        Stack<String> resStack = new Stack<>();
        
        int i = 0;    
        String res = "";        
        
        while(i < s.length()){
            char theChar = s.charAt(i);
            
            if(Character.isDigit(theChar)){
                int count = 0;  // the time of repeating
                while(Character.isDigit(s.charAt(i))){
                    count = 10 * count + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(count);
            }else if(theChar == '['){
                resStack.push(res);        // store current generated "res"
                res = "";                  // start a new string
                i++;
            }else if(theChar == ']'){
                StringBuilder tmp = new StringBuilder();
                tmp.append(resStack.pop()); // append the last generated string
                int times = countStack.pop();
                for(int j = 0; j < times; j++){
                    tmp.append(res);        // append new components
                }
                res = tmp.toString();       // store it in "res"
                i++;
            }else{
                res += s.charAt(i);         // just a character
                i++;
            }
        }
                
        return res;
    }
}