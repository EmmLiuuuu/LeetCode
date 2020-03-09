package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * <p>
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * str = "aabbcc", k = 3
 * <p>
 * Result: "abcabc"
 * <p>
 * The same letters are at least distance 3 from each other.
 * Example 2:
 * <p>
 * str = "aaabc", k = 3 axxaxxa (count - 1) * (k - 1) + count > length
 * <p>
 * Answer: ""
 * <p>
 * It is not possible to rearrange the string.
 * Example 3:
 * <p>
 * str = "aaadbbcc", k = 2
 * <p>
 * Answer: "abacabcd"
 * <p>
 * Another possible answer is: "abcabcda"
 * <p>
 * The same letters are at least distance 2 from each other.
 */
public class LeetCode358 {

    public static void main(String[] args) {
        System.out.println(new LeetCode358().rearrangeString1("aaadbbcc", 2));
    }

    public String rearrangeString(String str, int k) {
        if (k <= 1) {
            return str;
        }
        Map<Character, Integer> map = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        return helper(map, "", k, str.length());
    }

    public String helper(Map<Character, Integer> map, String str, int k, int n) {
        if (n == 0) {
            return str;
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                if ((entry.getValue() - 1) * (k - 1) > n) {
                    return "";
                }
                int lastIndexOf = str.lastIndexOf(entry.getKey());
                if (lastIndexOf < 0 || str.length() - lastIndexOf >= k) {
                    entry.setValue(entry.getValue() - 1);
                    String result = helper(map, str + entry.getKey(), k, n - 1);
                    if ("".equals(result)) {
                        entry.setValue(entry.getValue() + 1);
                        continue;
                    }
                    return result;
                }
            }
        }
        return "";
    }

    //用优先队列存储每个字符出现的次数，然后从大到小，不断组合min(k, len)个不同的字符，保证间距（len为str的长度，会随着循环不断减小）
    public String rearrangeString1(String str, int k) {
        if (k <= 1) {
            return str;
        }
        if (k > str.length()) {
            return "";
        }
        //用优先队列存储每个字符出现的次数
        PriorityQueue<Pair> queue = new PriorityQueue<>((s, v) -> v.count - s.count);
        Map<Character, Integer> map = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(Pair.of(entry.getKey(), entry.getValue()));
        }
        int len = str.length();
        StringBuilder builder = new StringBuilder();
        LinkedList<Pair> tmp = new LinkedList<>();
        while (!queue.isEmpty()) {
            int cnt = Math.min(k, len);
            for (int i = 0; i < cnt; i++, len--) {
                Pair pair = queue.poll();
                //剩余的不同的字符不足以填满剩下的空间，不符合要求
                if (pair == null) {
                    return "";
                }
                builder.append(pair.c);
                pair.count--;
                if (pair.count > 0) {
                    tmp.addLast(pair);
                }
            }
            //重新排序
            while (!tmp.isEmpty()) {
                queue.add(tmp.removeFirst());
            }
        }
        return builder.toString();
    }

    static class Pair {
        char c;
        int count = 0;

        public Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }

        static Pair of(char c, int count) {
            return new Pair(c, count);
        }
    }
}
