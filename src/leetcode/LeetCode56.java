package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

//https://leetcode.com/problems/merge-intervals/
public class LeetCode56 {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        LinkedList<int[]> result = new LinkedList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.addLast(interval);
            } else {
                result.getLast()[1] = Math.max(interval[1], result.getLast()[1]);
            }
        }

        return result.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.deepToString(
                        merge(new int[][]{
                                new int[]{1, 3},
                                new int[]{2, 6},
                                new int[]{8, 10},
                                new int[]{15, 18}
                        })));
    }
}
