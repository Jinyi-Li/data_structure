/**
 * Sliding windows.
 */

import java.util.*;

public class MinChairs {

    public int minChair(List<List<Integer>> times) {
        if (times == null || times.size() == 0) {
            return 0;
        }

        // Sort (start, end) pairs by ascending order on "start".
        Collections.sort(times, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                if (a.get(0) == b.get(0)) {
                    return 0;
                }
                return a.get(0) > b.get(0) ? 1 : -1;
            }
        });

        PriorityQueue<Integer> endTimeHeap =
                new PriorityQueue<>(times.size(), (a, b) -> {
                    if (a == b) {
                        return 0;
                    }
                    return a > b ? 1 : -1;
                });

        // Schedule each meeting one by one.

        // add first elem to avoid empty heap.
        endTimeHeap.add(times.get(0).get(1));
        for (int i = 1; i < times.size(); i++) {
            int startTime = times.get(i).get(0);
            int endTime = times.get(i).get(1);
            if (startTime >= endTimeHeap.peek()) {
                // No need to double count this chair.
                endTimeHeap.poll();
            }
            endTimeHeap.add(endTime);
        }
        System.out.println(endTimeHeap.size());
        return endTimeHeap.size();
    }


    public static void main(String[] args) {
        MinChairs solution = new MinChairs();

        List<List<Integer>> data = Arrays.asList(
                Arrays.asList(1, 5),
                Arrays.asList(2, 5),
                Arrays.asList(6, 7),
                Arrays.asList(5, 6),
                Arrays.asList(3, 8));
        solution.minChair(data);
    }
}
