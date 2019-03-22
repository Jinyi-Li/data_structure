/*
    Given a pattern and a string str, find if str follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

    Example 1:

    Input: pattern = "abab", str = "redblueredblue"
    Output: true
    Example 2:

    Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
    Output: true
    Example 3:

    Input: pattern = "aabb", str = "xyzabcxzyabc"
    Output: false
*/
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        
        Map<Character, String> patternWordMap = new HashMap<>();
        Set<String> usedWords = new HashSet<>();
        
        //  r edblue r edblue
        //  a b     a    b
        
        return match(pattern, str, patternWordMap, usedWords);
    }
    
    private boolean match(String pattern, String str, 
        Map<Character, String> patternWordMap, Set<String> usedWords){
        
        if(pattern.length() == 0){
            if(str.length() == 0){
                return true;
            }else{
                return false;
            }
        }
        
        char firstPattern = pattern.charAt(0);
        // check if word matches pattern-word mapping
        if(patternWordMap.containsKey(firstPattern)){
            
            String mappedWord = patternWordMap.get(firstPattern);
            int len = mappedWord.length();
            
            // no, it doesn't match
            if(str.length() < len || !str.substring(0, len).equals(mappedWord)){
                return false;
            }
            // yes, it matches. continue checking rest of str.    
            return match(pattern.substring(1), str.substring(len), patternWordMap, usedWords);
        }
        
        // no such mapping info yet. try different mappings.
        for(int i = 0; i < str.length(); i++){
            String newWord = str.substring(0, i + 1);
            
            // this word is already mapped to a pattern, don't use it.
            if(usedWords.contains(newWord)){
                continue;
            }
            
            // add a new mapping relation.
            patternWordMap.put(firstPattern, newWord);
            usedWords.add(newWord);
            
            // if this works till the end, return true.
            if(match(pattern.substring(1), str.substring(newWord.length()), 
                patternWordMap, usedWords)){
                
                return true;
            }
            
            // otherwise, try another mapping.
            patternWordMap.remove(firstPattern);
            usedWords.remove(newWord);
        }
        
        // after tried all possible mapping, still not working, return false.
        return false;
    }
    
}