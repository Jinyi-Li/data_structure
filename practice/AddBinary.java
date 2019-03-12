class Solution {
    private boolean carried;
    
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int indexa = a.length() - 1;
        int indexb = b.length() - 1;
        
        while(indexa >= 0 && indexb >= 0){
            char chara = a.charAt(indexa);
            char charb = b.charAt(indexb);
            res.insert(0, getSum(chara, charb));
            indexa--;
            indexb--;
        }
        while(indexa >= 0){
            char chara = a.charAt(indexa);
            res.insert(0, getSum(chara, '0'));
            indexa--;
        }
        while(indexb >= 0){
            char charb = b.charAt(indexb);
            res.insert(0, getSum(charb, '0'));
            indexb--;
        }
        if(carried){
            res.insert(0, '1');
        }
        return res.toString();
    }
    
    private char getSum(char x, char y){
        if(carried){
            if(x == '1' && y == '1'){
                return '1';
            }else if(x == '0' && y == '0'){
                carried = false;
                return '1';
            }else{                
                return '0';
            }
        }else{
            if(x == '1' && y == '1'){
                carried = true;
                return '0';
            }else if(x == '0' && y == '0'){
                return '0';
            }else{
                return '1';
            }
        }
    }
}