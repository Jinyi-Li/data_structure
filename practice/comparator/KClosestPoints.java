/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class KClosestPoints {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        if(points == null || points.length < 1 || k < 1) {
            return new Point[0];
        }     
        
        List<Point> pointList = Arrays.asList(points);
        Collections.sort(pointList, new Comparator<Point>() {
          @Override
          public int compare(Point a, Point b) {
              double distanceA = getDistanceSqr(a, origin);
              double distanceB = getDistanceSqr(b, origin);
              if(distanceA == distanceB) {
                  if(a.x != b.x) {
                      return a.x > b.x ? 1 : -1;
                  }
                  if(a.y != b.y) {
                      return a.y > b.y ? 1 : -1;
                  }
                  return 0;
              }
              return distanceA > distanceB ? 1 : -1;
          }
        });
        
        Point[] results = new Point[k];
        for(int i = 0; i < k; i++) {
            results[i] = pointList.get(i);
            // results[i] = pointHeap.poll();
        }
        return results;
    }
    
    private double getDistanceSqr(Point a, Point b) {
        return Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2);
    }
}