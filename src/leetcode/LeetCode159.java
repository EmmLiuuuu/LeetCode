package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 与340题一样，340题是通用解法
 */
public class LeetCode159 {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 2) {
            return 0;
        }

        int maxLength = 0;
        int count = 0;
        int i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>(s.length());
        while (i < s.length() && j < s.length()) {
            char jChar = s.charAt(j);
            if (map.getOrDefault(jChar, 0) == 0) {
                count++;
            }
            map.put(jChar, map.getOrDefault(jChar, 0) + 1);
            j++;

            while (count > 2) {
                char iChar = s.charAt(i);
                if (map.getOrDefault(iChar, 0) == 1) {
                    count--;
                }
                if (map.containsKey(iChar)) {
                    map.put(iChar, map.get(iChar) - 1);
                }
                i++;
            }
            if (count == 2) {
                maxLength = Math.max(maxLength, j - i);
            }
        }
        return maxLength;
    }
}
