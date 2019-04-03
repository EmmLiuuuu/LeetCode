package leetcode;

import java.util.Arrays;

public class LeetCode214 {

    public String shortestPalindrome(String s) {
//         int n = s.length();
//         int i = 0;

//         for (int j = n - 1; j >= 0; j--) {
//             if (s.charAt(i) == s.charAt(j)) {
//                 i++;
//             }
//         }

//         if (i == n) {
//             return s;
//         }

//         String pre = s.substring(i, n);
//         String rePre = new StringBuilder(pre).reverse().toString();

//         return rePre + shortestPalindrome(s.substring(0, i)) + s.substring(i);

        int n = s.length();

        String rev = new StringBuilder(s).reverse().toString();

        for(int i = 0; i < n; i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        return "";

    }
}
