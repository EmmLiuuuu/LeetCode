package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * For example, Given s = “eceba” and k = 2,
 * <p>
 * T is "ece" which its length is 3.
 */
public class LeetCode340 {

    public static void main(String[] args) {
        System.out.println(new LeetCode340().lengthOfLongestSubstringKDistinct1("eceba", 2));
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k <= 1) {
            return k;
        }
        Set<Character> set = new HashSet<>();
        int tmp = k;
        int maxLength = 0;
        int i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if (set.add(s.charAt(j))) {
                tmp--;
            }
            if (tmp == 0) {
                maxLength = Math.max(j++ - i + 1, maxLength);
            } else if (tmp < 0) {
                set.remove(s.charAt(i++));
                for (int m = i; m <= j; m++) {
                    set.add(s.charAt(m));
                }
                tmp = k - set.size();
            } else {
                j++;
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if (k <= 1) {
            return k;
        }
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        int i = 0, j = 0;
        int maxLength = 0;
        //用map维护滑动窗口
        Map<Character, Integer> map = new HashMap<>();
        while (i < s.length() && j < s.length()) {
            char jChar = s.charAt(j);
            //当为0的时候，即为当前子串未出现过的
            if (map.getOrDefault(jChar, 0) == 0) {
                //不重复的字符数+1
                count++;
            }
            //计数
            map.put(jChar, map.getOrDefault(jChar, 0) + 1);
            //往前移一步
            j++;
            //如果子串不重复的字符数大于k，需要i前移
            while (count > k) {
                char iChar = s.charAt(i);
                //如果当前为1，那么去除后的子串中就不会存在这个字符，需要将计数-1
                if (map.getOrDefault(iChar, 0) == 1) {
                    count--;
                }
                if (map.containsKey(iChar)) {
                    //-1
                    map.put(iChar, map.get(iChar) - 1);
                }
                i++;
            }
            if (count == k) {
                //因为无论怎样，j都是先前移一步，无需+1
                maxLength = Math.max(maxLength, j - i);
            }
        }
        return maxLength;
    }
}
