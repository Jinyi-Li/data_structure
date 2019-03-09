/*
    runtime O(n^2) = n to iterate each start * n to iterate each end
    space O(n) = queue
*/
public boolean wordBreak(String s, List<String> wordDict){
        
    Set<String> dictionary = new HashSet<>(wordDict);
    Queue<Integer> startIndices = new LinkedList<>();
    boolean[] visited = new boolean[s.length()];
    
    startIndices.offer(0);
    
    while(!startIndices.isEmpty()){
        int start = startIndices.poll();
        
        if(!visited[start]){
            
            for(int end = start + 1; end <= s.length(); end++){
                if(dictionary.contains(s.substring(start, end))){
                    if(end == s.length()){
                        return true;
                    }
                    startIndices.offer(end);
                }
            }
            visited[start] = true;
        }
    }
    return false;
}