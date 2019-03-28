/*
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

Examples: 

"42" -> 42  
"  -42"->-42
"42 wird awor"->42
"word 42"->0
"2147483648"->2147483647
"-2147483649"->-2147483648
*/
class MmyAtoi {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        // Check if match the pattern.
        String pattern = "(^ *)([+-]?)([0-9]+)(.*)";
        if(!str.matches(pattern)){
            return 0;
        }

        // Get the digital part.
        boolean isNegative = false;
        str = str.replaceFirst("^ +", "");
        if(str.charAt(0) == '-'){
            isNegative = true;
        }
        str = str.replaceAll(pattern, "$3");
        
        // Remove leading zeros.
        int firstNonZero = 0;
        while(firstNonZero < str.length() && str.charAt(firstNonZero) == '0'){
            firstNonZero++;
        }
        if(firstNonZero >= str.length()){
            return 0;
        }
        str = str.substring(firstNonZero);                
        // if overflow, return MAX_VALUE or MIN_VALUE
        if(str.length() > 10){
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        // Get the integer value while handling overflow.
        long number = Long.parseLong(str);
        // check overflow again!!!
        if(!isNegative && number > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if(isNegative && number > -1L * Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return isNegative ? (-1 * (int)number) : (int)number;
    }
}