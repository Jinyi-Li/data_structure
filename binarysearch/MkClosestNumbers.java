package practice.binarysearch;

public class MkClosestNumbers {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        if(A == null || A.length == 0){
            return new int[0];
        }
        
        if(A.length == 1){
            return A;
        }

        int left = 0;
        int right = A.length - 1;
        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(A[mid] < target){
                left = mid;
            }else{
                right = mid;
            }
        }
        
        int numOfResult = 0;
        int[] result = A.length < k ? new int[A.length] : new int[k];
        
        // collect elems from both sides
        while(left >= 0 && right < A.length && numOfResult < k){
            if(Math.abs(A[left] - target) <= Math.abs(A[right] - target)){
                result[numOfResult] = A[left];
                left--;
            }else{
                result[numOfResult] = A[right];
                right++;
            }
            numOfResult++;
        }
        // until hit one side, collect remaining ones on the other side
        while(left >= 0 && numOfResult < k){
            result[numOfResult++] = A[left--];
        }
        while(right < A.length && numOfResult < k){
            result[numOfResult++] = A[right++];
        }
        
        return result;
    }
}