/*
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output: 5

    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.

    先建一个HashMap存储各个词和含有他们可以变成的词的LinkedList， 然后用分层次的BFS逐步推进到目标词。
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }

        int minLength = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            minLength++;

            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                if (current.equals(endWord)) {
                    return minLength;
                } 

                List<String> neighbors = expand(current);
                for(String neighbor : neighbors){
                    if(!visited.contains(neighbor)){
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }                            
            }
        }
        return 0;
    }

    private List<String> expand(String current){
        List<String> res = new ArrayList<>();        
        
        char[] currentChars = current.toCharArray();
        for(int i = 0; i < current.length(); i++){
            for(char newChar = 'a'; newChar <= 'z'; newChar++){
                if(newChar != current.charAt(j)){ 
                    String newStr = current.substring(0, j) + newChar + current.substring(j + 1);
                    res.add(newStr);
                }
            }
        }

        return res;
    }   

}

