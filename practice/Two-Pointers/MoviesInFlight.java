/**
 * Best movie combo in flight!
 *
 * You have a list of movie durations and the duration of the flight.
 * You need to find the longest movie combo (exactly 2 movies) for the flight!
 *
 * The total duration of the two movies should be less than or equal to
 * (durationOfFlight - 30).
 *
 * If tie, return the pair with the longest movie!
 */

import java.util.*;

public class MoviesInFlight {

    public int[] moviesInFlight(int[] dur, int d) {
        Arrays.sort(dur);
        System.out.println(Arrays.toString(dur));

        int maxLenSoFar = Integer.MIN_VALUE;
        List<List<Integer>> results = new ArrayList<>();
        int left = 0;
        int right = dur.length - 1;

        while (left < right) {
            int sum = dur[left] + dur[right];

            if (sum > d) {
                right--;
            } else {
                if (sum >= maxLenSoFar) {
                    if (sum > maxLenSoFar) {
                        results.clear();
                        maxLenSoFar = sum;
                    }
                    results.add(Arrays.asList(dur[left], dur[right]));
                }
                left++;
            }
        }

        int[] result = new int[2];
        System.out.println(results);
        if (results.size() > 1) {
            int longestMovie = Integer.MIN_VALUE;
            for (List<Integer> res : results) {
                if (res.get(1) > longestMovie) {
                    result[0] = res.get(0);
                    result[1] = res.get(1);
                    longestMovie = res.get(1);
                }
            }
        } else {
            result[0] = results.get(0).get(0);
            result[1] = results.get(0).get(1);
        }
        return result;
    }


    public static void main(String[] args) {
        MoviesInFlight solution = new MoviesInFlight();
        int[] dur = {1, 3, 2, 5, 7, 4, 6, 9, 10};
        System.out.println(Arrays.toString(solution.moviesInFlight(dur, 9)));

    }
}
