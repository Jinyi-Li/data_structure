/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class IntervalOfTwoSortedList {
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        // Handle edge cases of null/empty arrays.
        if(A == null || A.length == 0 || B == null || B.length == 0){
            return new Interval[0];        
        }
        
        // Use two pointers to track progress of searching.
        int ptra = 0;
        int ptrb = 0;
        List<Interval> result = new ArrayList<>();
        
        // While there're at least one pair of intervals unchecked.
        while(ptra < A.length && ptrb < B.length){
            
            // Skip the pairs without overlaps.
            if(A[ptra].start > B[ptrb].end){
                ptrb++;
            }else if(A[ptra].end < B[ptrb].start){
                ptra++;
            }else{
                Interval newInterval = new Interval(
                    Math.max(A[ptra].start, B[ptrb].start),
                    Math.min(A[ptra].end, B[ptrb].end)
                );
                result.add(newInterval);
                
                // Avoid to skip two adjacent overlapped intervals! 
                if(ptra < A.length - 1 && A[ptra + 1].start <= B[ptrb].end){
                    ptra++;
                }else if(ptrb < B.length - 1 && B[ptrb + 1].start <= A[ptra].end){
                    ptrb++;
                }else{
                    ptra++;
                    ptrb++;    
                }                
            }
        }
        
        // Convert list to arrya.        
        return result.toArray(new Interval[0]);
    }
}