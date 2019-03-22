package practice.binarysearch;

import java.util.Arrays;

public class EaddBinary {

    private boolean carry;
    private char[] res;
    private int ptrr;

    /**
     * @param a: a number
     * @param b: a number
     * @return: the results
     */
    public String addBinary(String a, String b) {
        if(a == null || b == null || (a.length() == 0 && b.length() == 0)){
            return "";
        }

        char[] chara = a.toCharArray();
        char[] charb = b.toCharArray();
        res = new char[Math.max(a.length(), b.length())];

        int ptra = chara.length - 1;
        int ptrb = charb.length - 1;
        ptrr = res.length - 1;
        while(ptra >= 0 && ptrb >= 0){
            update(chara[ptra--], charb[ptrb--]);
        }
        while(ptra >= 0){
            update(chara[ptra--], '0');
        }
        while(ptrb >= 0){
            update(charb[ptrb--], '0');
        }

        if(!carry){
            return String.valueOf(res);
        }
        return '1' + String.valueOf(res);
    }

    private void update(char x, char y){
        boolean bitAdd = add(x, y);
        if(bitAdd){ // 1 and carry one bit
            res[ptrr--] = '1';
        }else{
            res[ptrr--] = '0';
        }
        System.out.println(Arrays.toString(res));
    }

    private boolean add(char x, char y){
        // 1+1+1=(1)1
        // 1+0+1=(1)0 0+1+1=(1)0
        // 0+0+0=0 0+0+1=1 1+0+0=1
        if((!carry) && x == '0' && y == '0'){
            // remain uncarrying
            return false;
        }else if((!carry) && (x == '0' || y == '0')){
            // remain uncarrying
            return true;
        }else if(carry && x == '0' && y == '0'){
            carry = false;
            return true;
        }else if((!carry) && x == '1' && y == '1'){
            carry = true;
            return false;
        }else if(carry && x == '1' && y == '1'){
            // remain carrying state
            return true;
        }else{
            // remain carrying state
            return false;
        }
    }

    public static void main(String[] args){
        EaddBinary sol = new EaddBinary();
        sol.addBinary("1","111");
    }
}