package practice.binarysearch;

public class MmyPow {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        if(x == 0 && n == 0){
            System.err.println("NaN");
            return 0;
        }else if(x == 0){
            return 0;
        }
        
        boolean isNegative = n < 0 ? true : false;
        if(isNegative){
            n = - (n + 1); // avoid int overflow
            x = 1 / x;
        }
        
        double ans = 1;
        double tmp = x;
        
        // x^20 
        // = (x^2)^10 
        // = (x^4)^5 
        // = (x^4)(x^4)^4 
        // = (x^4)(x^8)^2
        // = (x^4)(x^16)^1
        // = (x^4)(x^16)(x^16)^0
        // = (x^4)(x^16)(1)

        while(n != 0){
            if(n % 2 == 1){
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }
        
        // 补上一次，因为为了避免overflow一开始减掉了一次。
        if(isNegative){
            ans *= x;
        }

        return ans;
    }
}