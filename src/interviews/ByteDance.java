package interviews;

import java.util.*;

public class ByteDance {

    //给一个整数数组， 找到其中包含最多连续数的子集，
    // 比如给：15, 7, 12, 6, 14, 13, 9, 11，
    // 则返回: 5:[11, 12, 13, 14, 15]。考虑下时间复杂度

    public static void main(String[] args) {
        System.out.println(fun(new int[] {15, 7, 12, 6, 14, 13, 9, 11}));
    }

    public static List<Integer> fun(int[] array) {
//        Arrays.sort(array);
//
//        int i = 0, j = 1;
//        int max = 1;
//        int left = -1, right = -1;
//
//
//        while (i < array.length && j < array.length) {
//            if (array[j] == array[j - 1] + 1) {
//                if (max < j - i + 1) {
//                    max = j - i + 1;
//                    left = i;
//                    right = j;
//                }
//                j++;
//            } else {
//                i = j;
//                j = j + 1;
//            }
//        }
//        ArrayList<Integer> list = new ArrayList<>(max + 1);
//        for (int k = left; k <= right; k++) {
//            list.add(array[k]);
//        }
//
//        return list; O(logn + n)

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int n : array) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int[] bitMap = new int[(max / 32) == 0 ? 1 : (max / 32)];

        for (int n : array) {
            bitMap[n / 32] |= 1 << n;
        }

        int left = -1;
        int right = -1;

        int i = min, j = min + 1;
        int maxLength = 1;
        while (i <= max && j <= max) {
            if ((bitMap[i / 32] & (1 << j)) == (1 << j) &&
                    (bitMap[i / 32] & (1 << (j - 1))) == (1 << (j - 1))) {
                if (maxLength < j - i + 1) {
                    maxLength = j - i + 1;
                    left = i;
                    right = j;
                }
                j++;
            } else {
                i = j;
                j++;
            }
        }


        ArrayList<Integer> list = new ArrayList<>(max + 1);


        for (int k = left; k <= right; k++) {
            list.add(k);
        }

        return list;
    }
}
