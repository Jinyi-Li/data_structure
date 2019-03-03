class SparseMatrixDotProduct {
    public int[][] multiply(int[][] A, int[][] B) {
        
        int lenX = A.length;
        int lenY = A[0].length;
        int lenZ = B[0].length;
        
        if(B.length != lenY) {
            throw new IllegalArgumentException("Invalid dot product.");
        }
        
        int[][] result = new int[lenX][lenZ];
        
        for(int rowA = 0; rowA < lenX; rowA++) {
            
            // Check A[a][col] == 0?
            if(!allZero(A, rowA, true)) {
                
                // Calculate product!
                for(int colB = 0; colB < lenZ; colB++) {
                    result[rowA][colB] = getProductSum(A, rowA, B, colB);
                }
            }            
        }
        
        return result;
    }
    
    /* If a row or a column in the matrix is filled with all zeros. */
    private boolean allZero(int[][] matrix, int i, boolean runCol) {
        if(runCol) {
            for(int j = 0; j < matrix[i].length; j++) {                
                if(matrix[i][j] != 0) {
                    return false;
                }
            }
        } else {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[j][i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /* Return the sum of cell products. */
    private int getProductSum(int[][] A, int rowA, int[][] B, int colB) {
        int sum = 0;
        for(int i = 0; i < A[rowA].length; i++) {
            sum += A[rowA][i] * B[i][colB];
        }
        return sum;
    }
}
