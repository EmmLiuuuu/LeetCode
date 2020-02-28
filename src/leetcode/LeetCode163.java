package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
————————————————
版权声明：本文为CSDN博主「xudli」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/xudli/article/details/42290511
 */
public class LeetCode163 {

    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> list = new ArrayList<>(A.length);
        if (A[0] > lower) {
            if (lower + 1 == A[0]) {
                list.add(Integer.toString(lower));
            } else {
                list.add(lower + "->" + (A[0] - 1));
            }
            lower = A[0] + 1;
        }
        for (int i = 0; i < A.length && A[i] <= upper; i++) {
            if (A[i] < lower) {
                continue;
            }
            while (i < A.length - 1 && A[i] + 1 == A[i + 1]) {
                i++;
            }

            int start = ++i;
            while (i < A.length - 1 && A[i] + 1 != A[i + 1] && A[i] <= upper) {
                i++;
            }
            if (start == i) {
                list.add(String.valueOf(A[i]));
            } else {
                list.add(A[start] + "->" + A[i]);
            }
            lower = A[i] + 1;
        }

        return list;
    }
}
