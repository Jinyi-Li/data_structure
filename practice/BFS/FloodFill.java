/*
    Input: 
    image = [[1,1,1],[1,1,0],[1,0,1]]
    sr = 1, sc = 1, newColor = 2    
    Output: [[2,2,2],[2,2,0],[2,0,1]]
    
    Explanation: 
    From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
    by a path of the same color as the starting pixel are colored with the new color.
    Note the bottom corner is not colored 2, because it is not 4-directionally connected
    to the starting pixel.

    Use a BFS search to color all connected & same-color floors
*/
class Solution {
    private final int[] X_MOVES = {0,0,1,-1};
    private final int[] Y_MOVES = {1,-1,0,0};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length <= sr || image[0].length <= sc || sr < 0 || sc < 0){
            return image;
        }
        
        int color = image[sr][sc];
        
        // avoid infinite looping
        if(color == newColor){
            return image;
        }
        Queue<int[]> tovisit = new LinkedList<>();
        tovisit.offer(new int[]{sr, sc});
        
        while(!tovisit.isEmpty()){
            int[] currentPosition = tovisit.poll();
            image[currentPosition[0]][currentPosition[1]] = newColor;
            
            for(int i = 0; i < X_MOVES.length; i++){
                int newRow = currentPosition[0] + X_MOVES[i];
                int newCol = currentPosition[1] + Y_MOVES[i];
                if(inBound(image, newRow, newCol) && image[newRow][newCol] == color){
                    tovisit.offer(new int[]{newRow, newCol});
                }
            }
        }
        
        return image;
    }
    
    private boolean inBound(int[][] data, int x, int y){
        return (x >= 0 && y >= 0 && x < data.length && y < data[0].length);
    }   
}