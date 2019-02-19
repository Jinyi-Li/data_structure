/**
 * Flight: Find closest two sum.
 *
 * A flight will have a maximum operating travel distance.
 * Must have forward route(s).
 * Must have return route(s).
 * The sum of two shouldn't exceed the max operating travel distance.
 *
 * For example,
 * maxDistance = 1000;
 * forwardRoutes = [[1, 200], [2, 300], [3,400]];
 * returnRoutes = [[1, 500], [2, 550]];
 *
 * Return [3, 2] because 400+550=950 < 1000 and it's the longest distance the
 * flight can get!
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MaxFlightComboDistance {

    private List<List<Integer>> forwardRoutes;
    private List<List<Integer>> returnRoutes;

    public List<List<Integer>> findLongestFlightRoute(int maxDistance,
                                                      List<List<Integer>> forwardRoutes,
                                                      List<List<Integer>> returnRoutes) {

        if (forwardRoutes == null || forwardRoutes.size() < 1
                || returnRoutes == null || returnRoutes.size() < 1) {
            return new ArrayList<>(0);
        }

        List<List<Integer>> results = new ArrayList<>();
        this.forwardRoutes = forwardRoutes;
        this.returnRoutes = returnRoutes;

        // Sort two lists; get two asce order list of forward/return distances.
        Collections.sort(forwardRoutes, (a, b) -> compare(a, b));
        Collections.sort(returnRoutes, (a, b) -> compare(a, b));

        int maxDistSoFar = 0;

        // Start from two tails - move forward if larger than maxDistance.
        int forwardPtr = forwardRoutes.size() - 1;
        int returnPtr = returnRoutes.size() - 1;

        // Find the maximum possible indexes in two route lists.
        while (forwardPtr >= 0
                && forwardRoutes.get(forwardPtr).get(1) > maxDistance) {
            forwardPtr--;
        }
        while (returnPtr >= 0
                && returnRoutes.get(returnPtr).get(1) > maxDistance) {
            returnPtr--;
        }
        int forwardBound = forwardPtr;

        // Find maximum possible combo of forward and return!
        while (forwardPtr >= 0 && returnPtr >= 0) {
            while (forwardPtr > 0 && getSum(forwardPtr, returnPtr) > maxDistance) {
                forwardPtr--;
            }

            if (forwardPtr >= 0 && getSum(forwardPtr, returnPtr) >= maxDistSoFar) {
                if (getSum(forwardPtr, returnPtr) > maxDistSoFar) {
                    results.clear();
                }
                List<Integer> result = new ArrayList<>();
                result.add(forwardRoutes.get(forwardPtr).get(0));
                result.add(returnRoutes.get(returnPtr).get(0));
                results.add(result);
                maxDistSoFar = getSum(forwardPtr, returnPtr);
            }

            returnPtr--;
            forwardPtr = forwardBound;
        }

        return results;
    }

    private int getSum(int forwardPtr, int returnPtr) {
        return forwardRoutes.get(forwardPtr).get(1)
                + returnRoutes.get(returnPtr).get(1);
    }

    private int compare(List<Integer> a, List<Integer> b) {
        if (a.get(1).intValue() == b.get(1).intValue()) {
            return 0;
        }
        return a.get(1) > b.get(1) ? 1 : -1;
    }

    public static void main(String[] args) {
        MaxFlightComboDistance solution = new MaxFlightComboDistance();
        List<List<Integer>> forwardRoutes = new ArrayList<>();
        forwardRoutes.add(Arrays.asList(1, 2));
        forwardRoutes.add(Arrays.asList(3, 6));
        forwardRoutes.add(Arrays.asList(2, 4));
        forwardRoutes.add(Arrays.asList(4, 10));

        List<List<Integer>> returnRoutes = new ArrayList<>();
        returnRoutes.add(Arrays.asList(1, 2));
        returnRoutes.add(Arrays.asList(4, 5));
        returnRoutes.add(Arrays.asList(2, 3));
        returnRoutes.add(Arrays.asList(3, 4));

        System.out.println(solution.findLongestFlightRoute(7, forwardRoutes,
                returnRoutes));

    }
}
