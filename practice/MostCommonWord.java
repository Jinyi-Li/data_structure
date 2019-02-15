class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        
        // 注意，banned不需要检查长度，因为banned可以为空啊
        if(paragraph == null || banned == null || paragraph.length() < 1){
            return "";
        }    

        Set<String> bannedSet = new HashSet<>();
        for(String bannedWord : banned){
            bannedSet.add(bannedWord.toLowerCase());
        }
        
        String[] words = paragraph.split("\\W+");
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        
        // For each word, update its frequency if it's a valid word and not banned, ignoring case.
        for(String word : words){
            word = word.toLowerCase();
            if(isAWord(word) && !bannedSet.contains(word)){
                wordFrequencyMap.put(
                word, wordFrequencyMap.getOrDefault(word, 0) + 1);                
            }
        }        
        
        // Find the most common word.
        String result = null;
        int count = -1;
        for(Map.Entry<String, Integer> wordFreqEntry : wordFrequencyMap.entrySet()){

            // 注意，Entry的方法是getValue()和getKey()，不是value()和key()！！
            if(wordFreqEntry.getValue() > count){
                count = wordFreqEntry.getValue();
                result = wordFreqEntry.getKey();
            }
        }        
        return result;
    }
    
    private boolean isAWord(String token){
        char[] chars = token.toCharArray();
        for(char ch : chars){
            if(!Character.isLetter(ch)){
                return false;
            }
        }
        return true;
    }
}