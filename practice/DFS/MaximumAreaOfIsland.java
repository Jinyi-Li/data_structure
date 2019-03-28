/*
    Given a map of 0 and 1. 1 represents land and 0 water. Return the maximum area of island in the map.
*/
class Solution {
    
    private final int[] X_MOVES = {0,0,1,-1};
    private final int[] Y_MOVES = {1,-1,0,0};
    
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){  
                if(grid[i][j] == 0){
                    continue;
                }
                maxArea = Math.max(maxArea, findArea(grid, i, j, visited));
            }
        }
        return maxArea;
    }
    
    private int findArea(int[][] grid, int i, int j, boolean[][] visited){        
        visited[i][j] = true;
        int area = 1;
        
        for(int k = 0; k < X_MOVES.length; k++){
            int newI = i + X_MOVES[k];
            int newJ = j + Y_MOVES[k];
            if(inBound(grid, newI, newJ) && !visited[newI][newJ] && grid[newI][newJ] == 1){
                visited[newI][newJ] = true;
                area = area + findArea(grid, newI, newJ, visited);
            }
        }
        return area;
    }
    
    private boolean inBound(int[][] grid, int i, int j){
        return (i >= 0 && i < grid.length && j >=0 && j < grid[0].length);
    }
}