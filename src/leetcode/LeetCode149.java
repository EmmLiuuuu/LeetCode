package leetcode;

import java.util.HashMap;

public class LeetCode149 {

    /**
     * Definition for a point.
     * class Point {
     *     int x;
     *     int y;
     *     Point() { x = 0; y = 0; }
     *     Point(int a, int b) { x = a; y = b; }
     * }
     */
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }

        public boolean equals(Object o) {
            Point p = (Point) o;
            if (p.x == this.x && p.y == this.y) {
                return true;
            }
            return false;
        }
    }
    public int maxPoints(Point[] points) {

        int count = 0;
        HashMap<Point, Integer> map = new HashMap<Point, Integer>();

        for (int i = 0; i < points.length; i++) {
            int same = 0;
            int cnt = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    same++;
                } else {
                    int xDiff = points[i].x - points[j].x;
                    int yDiff = points[i].y - points[j].y;

                    int g = gcd(xDiff, yDiff);

                    xDiff /= g;
                    yDiff /= g;

                    int m = map.getOrDefault(new Point(xDiff, yDiff), 0);
                    map.put(new Point(xDiff, yDiff), m + 1);
                    cnt = Math.max(cnt, m + 1);
                }
            }
            count = Math.max(count, cnt + same + 1);
        }

        return count;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
