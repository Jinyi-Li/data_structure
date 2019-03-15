/*
    Given a 2D binary matrix filled with 0's and 1's, find the largest 
    square containing only 1's and return its area.

    Example:

    Input: 

    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0

    Output: 4
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[][] memo = new int[numRows+1][numCols+1];
        
        int max = 0;
        
        for(int i = 1; i <= numRows; i++){
            for(int j = 1; j <= numCols; j++){
                if(matrix[i-1][j-1] == '1'){                    
                    memo[i][j] = 1 + getMin(memo[i-1][j], memo[i-1][j-1], memo[i][j-1]);
                    if(memo[i][j] > max){
                        max = memo[i][j];
                    }
                }
            }
        }
        
        return max * max;
    }

    private int getMin(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
}