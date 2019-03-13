public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here
        
        if(digits.length() == 0){
            return new ArrayList<>();
        }
        
        Map<Character, char[]> numToCharMap = new HashMap(){{
            put('2', new char[]{'a', 'b', 'c'});
            put('3', new char[]{'d', 'e', 'f'});
            put('4', new char[]{'g', 'h', 'i'});
            put('5', new char[]{'j', 'k', 'l'});
            put('6', new char[]{'m', 'n', 'o'});
            put('7', new char[]{'p', 'q', 'r', 's'});
            put('8', new char[]{'t', 'u', 'v'});
            put('9', new char[]{'w', 'x', 'y', 'z'});
        }};
        
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        helper(digits, 0, path, res, numToCharMap);
        
        return res;
    }
    
    private void helper(String digits, int digitIndex, StringBuilder path, 
                            List<String> res, Map<Character, char[]> numToCharMap){
        if(digitIndex == digits.length()){
            res.add(path.toString());
            return;
        }
        
        char[] chars = numToCharMap.get(digits.charAt(digitIndex));
        for(int i = 0; i < chars.length; i++){
            path.append(chars[i]);
            helper(digits, digitIndex + 1, path, res, numToCharMap);
            path.deleteCharAt(path.length() - 1);
        }
    }
}