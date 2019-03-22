/*
    We are given an array asteroids of integers representing asteroids in a row.

    For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

    Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

    Example 1:
    Input: 
    asteroids = [5, 10, -5]
    Output: [5, 10]
    Explanation: 
    The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

    Example 2:
    Input: 
    asteroids = [8, -8]
    Output: []
    Explanation: 
    The 8 and -8 collide exploding each other.
*/
class Solution {
    public int[] asteroidCollision(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }

        Stack<Integer> leftRocks = new Stack<>();
        leftRocks.push(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            while (!leftRocks.isEmpty() && leftRocks.peek() > 0 && leftRocks.peek() < -1 * nums[i]) {
                leftRocks.pop();
            }
            
            if (leftRocks.isEmpty()) {
                leftRocks.push(nums[i]);
                continue;
            }

            int prev = leftRocks.peek();
            if (prev < 0 || (prev > 0 && nums[i] > 0)) {
                leftRocks.push(nums[i]);
            } else if (prev == -1 * nums[i]) {
                leftRocks.pop();
            } else if (prev < -1 * nums[i]) {
                leftRocks.pop();
                leftRocks.push(nums[i]);
            } else {
                continue;
            }
        }

        int[] result = new int[leftRocks.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = leftRocks.pop();
        }
        return result;
    }
}