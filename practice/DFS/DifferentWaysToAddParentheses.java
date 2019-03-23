import java.lang.IllegalArgumentException;

/*
    Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

    Input: "2*3-4*5"
    Output: [-34, -14, -10, -10, 10]
    Explanation: 
    (2*(3-(4*5))) = -34 
    ((2*3)-(4*5)) = -14 
    ((2*(3-4))*5) = -10 
    (2*((3-4)*5)) = -10 
    (((2*3)-4)*5) = 10
*/
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        // it will contain all possible combination results
        List<Integer> result = new ArrayList<>();

        // this loop will break 2*3-4*5 to (2)*(3-4*5), (2*3)-(4*5), and (2*3-4)*(5).
        for(int i = 0; i < input.length(); i++){
            // if this is a digit, skip it. It will be included in "part1".
            if(Character.isDigit(input.charAt(i))){
                continue;
            }
            
            // charAt(i) is an operator
            char operator = input.charAt(i);
            String part1 = input.substring(0, i);
            String part2 = input.substring(i + 1);
            
            // get all possible combinations in each part
            List<Integer> part1Result = diffWaysToCompute(part1);
            List<Integer> part2Result = diffWaysToCompute(part2);
            
            // then combine the two sets to get all possible results in the current string
            for(Integer num1 : part1Result){
                for(Integer num2 : part2Result){
                    result.add(compute(num1, num2, operator));
                }
            }
        }
        
        // if all chars in the input string is digit, parse the whole input string to an int
        if(result.size() == 0){
            result.add(Integer.parseInt(input));
        }
        return result;
    }
    
    private int compute(int num1, int num2, char operator){
        switch(operator){
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            default: throw new IllegalArgumentException();
        }
    }
}