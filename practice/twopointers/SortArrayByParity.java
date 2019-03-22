/*
    Given a list of number, sort it such that all even numbers are on the left
    and all odd number are following behind.

    For example:
    [1,2,3,4] -> [2,4,1,3] (order between numbers with same parity does not matter)

*/
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while(left < right){
            while(left < right && A[left] % 2 == 0){
                left++;
            }
            while(left < right && A[right] % 2 != 0){
                right--;
            }
            if(left < right){
                swap(A, left, right);
            }
        }
        return A;
    }
    
    private void swap(int[] data, int x, int y){
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }
}