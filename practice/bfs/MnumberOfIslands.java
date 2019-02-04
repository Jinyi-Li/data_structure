/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1   

Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/
class MnumberOfIslands {
    private static class Node{
        private int i;
        private int j;
        private Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    
    private int[] iMoves = {-1, +1, 0, 0};
    private int[] jMoves = {0, 0, -1, +1};
    
    public int numIslands(char[][] grid) {
        int numOfIslands = 0;
        if(grid == null || grid.length == 0){
            return numOfIslands;
        }
        
        // Visit each spot to check.
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                // if the spot is an island and has not been visited
                if(grid[i][j] == '1'){
                    checkNeighbors(grid, i, j);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }
    
    private void checkNeighbors(char[][] grid, int i, int j){
        Queue<Node> nodesToVisit = new LinkedList<>();
        nodesToVisit.offer(new Node(i, j));
        
        while (!nodesToVisit.isEmpty()) {
            Node node = nodesToVisit.poll();
            i = node.i;
            j = node.j;
            for (int k = 0; k < 4; k++) {
                int newI = i + iMoves[k];
                int newJ = j + jMoves[k];
                if (newI >= 0 && newI < grid.length
                        && newJ >= 0 && newJ < grid[newI].length) {
                    if (grid[newI][newJ] == '1') {
                        grid[newI][newJ] = '0';
                        nodesToVisit.offer(new Node(newI, newJ));
                    }
                }
            }
        }
    }
}