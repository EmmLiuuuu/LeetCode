package leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class LeetCode945 {

    public int minIncrementForUnique(int[] A) {

        if (A == null || A.length <= 1) {
            return 0;
        }


//        HashMap<Integer, Integer> map = new HashMap<>();
//
//        Arrays.sort(A);
//
//        for (int a : A) {
//            map.put(a, map.getOrDefault(a, 0) + 1);
//        }
//
//        int result = 0;
//
//        for (int a : A) {
//            int temp = map.get(a);
//            for (int i = a + 1; temp > 1;i++) {
//                if (map.get(i) == null) {
//                    map.put(i, 1);
//                    temp--;
//                    map.put(a, temp);
//                    result += (i - a);
//                }
//            }
//        }
//
//        return result;//low逼解法，cn 6s



//        HashMap<Integer, Integer> map = new HashMap<>();
//
////         Arrays.sort(A);
//
//        for (int a : A) {
//            map.put(a, map.getOrDefault(a, 0) + 1);
//        }
//
//        int result = 0;
//        int taken = 0;
//        for (int a = 0; a < 100000; a++) {
//            if (map.get(a) != null && map.get(a) > 1) {
//                taken += map.get(a) - 1;
//                result -= a * (map.get(a) - 1);
//            } else if (taken > 0 && map.get(a) == null) {
//                taken--;
//                result += a;
//            }
//        }
//
//        return result;

        //年轻人不要被map局限住了
        int[] count = new int[100000];
        for (int x: A) count[x]++;

        int ans = 0, taken = 0;

        for (int x = 0; x < 100000; ++x) {
            if (count[x] >= 2) {
                taken += count[x] - 1;
                ans -= x * (count[x] - 1);
            }
            else if (taken > 0 && count[x] == 0) {
                taken--;
                ans += x;
            }
        }

        return ans;
    }
}
