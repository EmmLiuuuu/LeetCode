package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode3 {

    //暴力
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    //优化暴力
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int i = 0;
        int j = 0;
        Set<Character> set = new HashSet<>();
        //对所有的i开头找最长不重复子串
        while (i < s.length() && j < s.length()) {
            //找到的没有重复字符的最长子字符串将会以索引 i 开头
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                //记录最长
                result = Math.max(result, j - i);
            } else {
                //去掉开头，换一个
                set.remove(s.charAt(i++));
            }
        }
        return result;
    }

    //继续优化
    //我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口
    public int lengthOfLongestSubstring2(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                //当发现窗口中存在重复字符时，直接跳过这个窗口，到达下一个起点，而不是i一个个往前移
                i = Math.max(i, map.get(c) + 1);
            }
            //如果不存在重复的字符，那么j是不断变大的，result会越来越大，记录最大的不重复子串
            result = Math.max(result, j - i + 1);
            //记录当前字符的最新下标
            map.put(c, j);
        }
        return result;
    }
}
