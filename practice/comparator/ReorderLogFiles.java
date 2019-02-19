/**
 * Reorder log files.
 *
 * Words before integers.
 * Words sorted in lexi order.
 * Integers should remain original order.
 *
 * For words, use identifier to break tie, sorted in ASCII order.
 */

import java.util.*;

public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs, int logFileSize) {
        if (logs == null || logs.length < 2) {
            return logs;
        }

        Arrays.sort(logs, new LogComparator());
        System.out.println(Arrays.toString(logs));
        return logs;
    }

    private static class LogComparator implements Comparator<String> {
        @Override
        public int compare(String r1, String r2) {
            String r1Identifier = r1.split(" ")[0];
            String r1Log = r1.substring(r1Identifier.length() + 1);

            String r2Identifier = r2.split(" ")[0];
            String r2Log = r2.substring(r2Identifier.length() + 1);

            if (isDigit(r1Log.charAt(0)) && isDigit(r2Log.charAt(0))) {
                return 0;
            }

            if (isDigit(r1Log.charAt(0))) {
                return 1;
            }
            if (isDigit(r2Log.charAt(0))) {
                return -1;
            }

            int res = r1Log.compareTo(r2Log);
            if (res != 0) {
                return res;
            }
            return r1Identifier.compareTo(r2Identifier);
        }

        private boolean isDigit(char ch) {
            return Character.isDigit(ch);
        }
    }

    private static class Record {
        String identifier;
        String log;

        Record(String identifier, String log) {
            this.identifier = identifier;
            this.log = log;
        }
    }

    public static void main(String[] args) {
        ReorderLogFiles solution = new ReorderLogFiles();
        String[] logs = {
                "a1 9 2 3 1",
                "g1 act car",
                "zo4 4 7",
                "ab1 off key dog",
                "9b act car"
        };
        solution.reorderLogFiles(logs, 5);

    }
}
