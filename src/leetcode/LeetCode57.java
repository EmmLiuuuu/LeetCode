package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class LeetCode57 {

    private Comparator<int[]> comparator = Comparator.comparingInt(k -> k[0]);

    public static void main(String[] args) {
        int[][] temp = new int[2][];
        temp[0] = new int[]{1, 3};
        temp[1] = new int[]{6, 9};
        new LeetCode57().insert(temp, new int[]{6, 10});
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] interval : intervals) {
            queue.addLast(interval);
        }

//        int index = -1;
//        for (int i = 0; i < intervals.length; i++) {
//            if (newInterval[0] < intervals[i][0]) {
//                index = i;
//                break;
//            }
//        }
//        if (index == -1) {
//            queue.addLast(newInterval);
//        } else {
//            queue.add(index, newInterval);
//        }
        int index = Arrays.binarySearch(intervals, newInterval, comparator);
        if (index < 0) {
            queue.add((-index) - 1, newInterval);
        } else {
            queue.add(index, newInterval);
        }

        LinkedList<int[]> stack = new LinkedList<>();
        stack.addLast(queue.removeFirst());
        while (!queue.isEmpty()) {
            int[] interval = queue.removeFirst();
            int[] last = stack.getLast();
            if (interval[0] > last[1]) {
                stack.addLast(interval);
            } else {
                if (last[1] < interval[1]) {
                    last[1] = interval[1];
                }
            }
        }
        int[][] result = new int[stack.size()][];
        Iterator<int[]> iterator = stack.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            result[i] = iterator.next();
        }
        return result;
    }
}
