/*
    Given an array A of integers, return true if and only if it is a valid mountain array.

    Recall that A is a mountain array if and only if:

    A.length >= 3
    There exists some i with 0 < i < A.length - 1 such that:
    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

    解题思路：考虑negative case太麻烦，其实可以用假设法解题。假设给定数据就是一个有效的山地数组，
    一旦遇到违反条件的地方就返回false即可。
*/
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int i = 0;

        // Walking upwards
        while((i+1 < A.length) && (A[i] < A[i+1])){
            i++;
        }

        // Peak shouldn't be the first or the last element.
        if(i == 0 || i == A.length - 1){
            return false;
        }
        
        // Walking downwards
        while((i+1 < A.length) && (A[i] > A[i+1])){
            i++;
        }

        // If valid, i should be the last position.
        return i == A.length - 1;
    }
}