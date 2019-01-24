package practice.mathematics;

class MrotateImage {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 1 && matrix[0].length == 1){
            return;
        }
        
        int n = matrix.length;

        int length = n / 2;
        // 注意如果n是奇数的话，要多移一个，不然最中间的会被漏掉
        int width = n / 2 + n % 2; 
        int tmp;
        
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;                
            }
        }
    }
}
