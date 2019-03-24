import java.util.Arrays;
import java.util.Stack;

/*
    Use two stacks to sort an array of int.    
*/
class Solution {
    public void stackSort(int[] data){
        Stack<Integer> aux = new Stack<>();
        Stack<Integer> res = new Stack<>();

        // first, push all numbers into aux stack
        for(int num : data){
            aux.push(num);
        }

        // pop each number, only push it into res stack when it's smaller than res.peek
        // if not, move numbers from res to aux until I can push this new number to res!
        // By doing so, I can always ensure res is a descending stack!!
        while(!aux.isEmpty()){
            Integer num = aux.pop();
            if(res.isEmpty() || res.peek() >= num){
                res.push(num);
            }else{
                while(!res.isEmpty() && res.peek() < num){
                    aux.push(res.pop());
                }
                res.push(num);
            }
        }

        for(int i = 0; i < data.length; i++){
            data[i] = res.pop();
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] data = {4,6,8,1,3,7,9,0,2,5};
        sol.stackSort(data);
        System.out.println(Arrays.toString(data));
    }
}