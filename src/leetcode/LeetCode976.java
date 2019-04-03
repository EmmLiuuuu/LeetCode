package leetcode;

import java.util.Arrays;

public class LeetCode976 {


    public int largestPerimeter(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        Arrays.sort(A);

        //sb做法
        // for (int i = A.length - 1; i >= 2; i--) {
        //     for (int j = i - 1; j >= 1; j--) {
        //         for (int k = j - 1; k >= 0; k--) {
        //             if (A[i] < A[j] + A[k]) {
        //                 return A[i] + A[j] + A[k];
        //             }
        //         }
        //     }
        // }

        for (int i = A.length - 3; i >= 0; i--) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }


        return 0;
    }
}
