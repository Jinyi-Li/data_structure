import java.util.*;

/*
    Example 1:
    Input: num = "123", target = 6
    Output: ["1+2+3", "1*2*3"] 
    
    Example 2:
    Input: num = "232", target = 8
    Output: ["2*3+2", "2+3*2"]
    
    Example 3:
    Input: num = "105", target = 5
    Output: ["1*0+5","10-5"]
    
    Example 4:
    Input: num = "00", target = 0
    Output: ["0+0", "0-0", "0*0"]
    
    Example 5:
    Input: num = "3456237490", target = 9191
    Output: []
*/
class Solution {
    private String number;
    private int target;

    public List<String> addOperators(String number, int target){
        this.number = number;
        this.target = target;
        List<String> ans = new ArrayList<>();

        dfs(0, "", 0, 0, ans);

        return ans;
    }

    private void dfs(int start, String str, long sum, long lastF, List<String> ans){
        if(start == number.length()){
            if(sum == target){
                ans.add(str);
            }
            return;
        }

        for(int i = start; i < number.length(); i++){
            long x = Long.parseLong(number.substring(start, i + 1));

            if(start == 0){
                dfs(i + 1, "" + x, x, x, ans);
            }else{
                dfs(i + 1, str + "*" + x, sum - lastF + lastF * x, lastF * x, ans);
                dfs(i + 1, str + "+" + x, sum + x, x, ans);
                dfs(i + 1, str + "-" + x, sum - x, -x, ans);
            }

            if(x == 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addOperators("105", 5));
    }

}