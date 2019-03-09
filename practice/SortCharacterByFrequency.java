import java.util.Map.Entry;

class Solution {
    public String frequencySort(String s) {
        if(s.length() == 0){
            return "";
        }
        
        Map<Character, Integer> charFreqMap = new HashMap<>();
        char[] charArray = s.toCharArray();
        for(char ch : charArray){
            charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);
        }
        
        PriorityQueue<Entry<Character, Integer>> queue = new PriorityQueue<>((a, b)->{
            if(a.getValue() == b.getValue()){
                return 0;
            }
            return a.getValue() > b.getValue() ? -1 : 1;
        });
        
        for(Entry<Character, Integer> charFreq : charFreqMap.entrySet()){
            queue.offer(charFreq);
        }
        
        
        StringBuilder res = new StringBuilder();
        while(!queue.isEmpty()){
            Entry<Character, Integer> entry = queue.poll();
            int count = entry.getValue();
            char ch = entry.getKey();
            for(int i = 0; i < count; i++){
                res.append(ch);
            }
        }
        
        return res.toString();                    
    }
}