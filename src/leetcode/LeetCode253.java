package leetcode;

import java.util.Arrays;
import java.util.Comparator;

//https://blog.csdn.net/jmspan/article/details/51093343
public class LeetCode253 {
    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return 2.
     */

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length < 2) {
            return intervals.length;
        }
        Arrays.sort(intervals, Comparator.comparingInt(k -> k.start));
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                count++;
            }
        }
        return count;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
