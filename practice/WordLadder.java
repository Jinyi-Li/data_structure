/*
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    Output: 5

    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.
*/
class Solution {    
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int length = 0;
        
        Set<String> dict = new HashSet<>(wordList);
        dict.add(beginWord);
        dict.add(endWord);
        
        // use bfs find all shortest paths
        Map<String, Integer> distance = new HashMap<>();
        Map<String, List<String>> neighbors = new HashMap<>();
        
        bfs(start, end, neighbors, distance, dict);
        
        // use dfs to backtrack to the start
        dfs(start, end, neighbors, distance, dict, new ArrayList<>(), Integer.MAX_VALUE);
    }
    
    private void bfs(String start, Map<String, List<String>> neighbors, Map<String, Integer> distance, Set<String> dict){
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        
        for(String s : dict){
            neighbors.put(s, new ArrayList<>());
        }
                
        while(!queue.isEmpty()){            
            String curr = queue.poll();            
            List<String> nexts = expand(curr, dict);
            for(String next : nexts){
                neighbors.get(next).add(curr);
                if(distance.containsKey(next)){
                    distance.put(next, distance.get(curr).intValue() + 1);
                    queue.offer(next);
                }                
            }            
        }        
    }
    
    private void dfs(String start, String curr, Map<String, List<String>> neighbors, 
                     Map<String, Integer> distance, List<String> path, int shortest){
        path.add(curr);
        if(curr.equals(start)){
            if(path.size() < shortest){
                shortest = path.size();
            }                        
        }else{
            for(String next : neighbors.get(curr)){
                if(distance.containsKey(next) && distance.get(next) == distance.get(curr) - 1){
                    dfs(start, next, neighbors, distance, path, shortest);
                }
            }
        }                        
        path.remove(path.size() - 1);
    }
}