import java.util.Arrays;

class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        // remove all spaces
        s = s.trim();

        // if not starting with [0-9] or [+-.] sign, not ending with [0-9] or dot sign
        if (!s.matches(".*[0-9.]")) {
            System.out.println(1);
            return false;
        }
        if (!s.matches("[+-.0-9].*")) {
            System.out.println(2);
            return false;
        }


        boolean isScienfic;
        String[] scientificParts = s.split("e");

        System.out.println("scientificParts.length = " + scientificParts.length);

        if (scientificParts.length == 1) {
            isScienfic = false;
        } else if (scientificParts.length == 2 && scientificParts[0].length() > 0) {
            isScienfic = true;
        } else {
            return false;
        }

        if (isScienfic) {
            String base = scientificParts[0];
            String exp = scientificParts[1];

            if (!isPlainNumber(base)) {
                return false;
            }

            String formatForExp = "^[+-]?[0-9]+$";
            if (!exp.matches(formatForExp)) {
                return false;
            }
        } else {
            return isPlainNumber(s);
        }
        return true;
    }

    private boolean isPlainNumber(String str) {
        if (str.contains(".")) {
            String format1 = "^[+-]?[0-9]*\\.[0-9]+$";
            String format2 = "^[+-]?[0-9]+\\.[0-9]*$";
            return (str.matches(format1) || str.matches(format2));
        } else {
            String format = "^[+-]?[0-9]+$";
            return str.matches(format);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isNumber("11"));
    }
}