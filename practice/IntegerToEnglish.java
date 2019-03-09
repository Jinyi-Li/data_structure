import java.util.*;

class Solution {
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        // break the whole number into sections
        int thousand = 1000;
        List<Integer> sections = new ArrayList<>(); // num=123 456 789
        while (num / thousand > 0) {
            sections.add(num - num / thousand * thousand);
            num = num / thousand;
        }
        if (num > 0) {
            sections.add(num);
        }
        Collections.reverse(sections);

        // convert each section
        String[] converted = convert(sections);

        // connect all sections into one complete string
        String res = connect(converted);

        return res;
    }

    private String[] convert(List<Integer> sections) {
        String[] res = new String[sections.size()];
        for (int i = 0; i < sections.size(); i++) {
            int num = sections.get(i);
            StringBuilder section = new StringBuilder();

            int hundred = num / 100;
            if (hundred != 0) {
                section.append(convert1Digit(hundred));
                section.append(" Hundred");
            }

            int tens = num - num / 100 * 100;
            if (hundred != 0 && tens != 0) {
                section.append(" ");
            }
            section.append(convert2Digits(tens));
            res[i] = section.toString();
        }
        return res;
    }

    private String connect(String[] converted) {
        String[] connectWords = new String[]{
                "Thousand", "Million", "Billion", "Trillion"
        };

        StringBuilder res = new StringBuilder();
        int len = converted.length - 1;
        for (int i = 0; i < len; i++) {
            if (!converted[i].equals("")) {
                res.append(" ");
                res.append(converted[i]);
                res.append(" ");
                res.append(connectWords[len - i - 1]);
            }
        }

        if (res.length() > 0) {
            res.deleteCharAt(0);
        }

        String lastSec = converted[converted.length - 1];
        // check if i need to add a space
        if (len > 0 && !lastSec.equals("")) {
            res.append(" ");
        }

        res.append(lastSec);
        return res.toString();
    }

    private String convert1Digit(int digit) {
        switch (digit) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            default:
                return "";
        }
    }

    private String convert2Digits(int digits) {
        if (0 <= digits && digits <= 9) {
            return convert1Digit(digits);
        }

        if (10 <= digits && digits <= 19) {
            switch (digits) {
                case 10:
                    return "Ten";
                case 11:
                    return "Eleven";
                case 12:
                    return "Twelve";
                case 13:
                    return "Thirteen";
                case 14:
                    return "Fourteen";
                case 15:
                    return "Fifteen";
                case 16:
                    return "Sixteen";
                case 17:
                    return "Seventeen";
                case 18:
                    return "Eighteen";
                case 19:
                    return "Nineteen";
                default:
                    throw new IllegalArgumentException();
            }
        }

        int twenties = digits / 10;
        String twentiesInString = convertTwenties(twenties);
        int ones = digits - digits / 10 * 10;
        String onesInString = convert1Digit(ones);

        if (onesInString.equals("")) {
            return twentiesInString;
        }
        return twentiesInString + " " + onesInString;
    }

    private String convertTwenties(int digit) {
        switch (digit) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
            default:
                throw new IllegalArgumentException();
        }
    }
}


// 2,000,000,000,000