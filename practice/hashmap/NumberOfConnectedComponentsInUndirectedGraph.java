class Solution {
    public int countComponents(int n, int[][] edges) {
        
        Map<Integer, List<Integer>> neighborMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            neighborMap.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){            
            neighborMap.get(edge[0]).add(edge[1]);        
            neighborMap.get(edge[1]).add(edge[0]);
        }
        
        int numComponents = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> toVisit = new LinkedList<>();
        int start;
        
        while((start = getNextStart(visited)) != -1){
            numComponents = numComponents + 1;
            toVisit.offer(start);
            
            while(!toVisit.isEmpty()){
                Integer node = toVisit.poll();
                visited[node] = true;
                
                if(neighborMap.get(node).size() > 0){
                    for(Integer neighbor : neighborMap.get(node)){
                        if(!visited[neighbor]){
                            toVisit.add(neighbor);    
                        }
                    }
                }
            }
        }
        
        return numComponents;
    }
    
    private int getNextStart(boolean[] visited){
        int idx = 0;
        while(idx < visited.length && visited[idx]){
            idx++;
        }
        return idx < visited.length ? idx : -1;
    }
}