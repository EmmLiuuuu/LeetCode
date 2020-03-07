package leetcode;

import java.util.HashMap;

public class LeetCode242 {

    public boolean validate(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            if (map.get(t.charAt(i)) == null || map.put(t.charAt(i), map.get(t.charAt(i)) - 1) - 1 < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int sIndex = s.charAt(i) - 'a';
            chars[sIndex]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int tIndex = t.charAt(i) - 'a';
            if (chars[tIndex] == 0) {
                return false;
            } else {
                chars[tIndex]--;
            }
        }
        return true;
    }
}
