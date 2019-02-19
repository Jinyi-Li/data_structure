/**
 * Find First Repeated Char in the String!
 */

import java.util.*;

public class FirstRepeatedChar {

    public char find(String s) {
        int minId = Integer.MAX_VALUE;
        Map<Character, Integer> charFirstIdMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!charFirstIdMap.containsKey(c)) {
                charFirstIdMap.put(c, i);
            } else {
                if(charFirstIdMap.get(c) < minId) {
                    minId = charFirstIdMap.get(c);
                }
            }
        }
        if(minId == Integer.MAX_VALUE) {
            return ' ';
        }
        System.out.println(s.charAt(minId));
        return s.charAt(minId);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.find("ajhbbuoaens");


    }
}
