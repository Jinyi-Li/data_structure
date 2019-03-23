/*
    Given an array of string, group all anagrams and return.

    Input: ["abc", "acb", "bca", "zyz", "yzz", "qqq"]
    Output: [
                ["abc", "acb", "bca"],
                ["zyz", "yzz"],
                [qqq]
            ]
    Solution runtime: O(nklogk)
    Followup: can you do it in O(nk)?
    - Use bucket sort (count sort).
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();        
        Map<MyString, List<String>> anagrams = new HashMap<>();
        for(String str : strs){            
            MyString theString = new MyString(str);
            if(!anagrams.containsKey(theString)){                
                List<String> wordList = new LinkedList<>();
                wordList.add(str);
                anagrams.put(theString, wordList);
            }else{
                anagrams.get(theString).add(str);
            }
        }
        for(List<String> wordList : anagrams.values()){
            res.add(wordList);
        }
        return res;
    }
        
    private static class MyString {
        String val;
        char[] chars;
        
        private MyString(String value){
            val = value;
            chars = value.toCharArray();
            Arrays.sort(chars);
        }
        
        @Override
        public boolean equals(Object o){
            if(o == this){
                return true;
            }
            if(!(o instanceof MyString)){
                return false;
            }
            MyString obj = (MyString) o;
            char[] thisChars = val.toCharArray();
            char[] objChars = obj.val.toCharArray();
            Arrays.sort(thisChars);
            Arrays.sort(objChars);
            return Arrays.equals(thisChars, objChars);
        }
        
        @Override
        public int hashCode(){
            // return the hashCode sum of each char
            // so anagrams will have same hashcode value
            return Arrays.hashCode(chars);
        }
    }
}