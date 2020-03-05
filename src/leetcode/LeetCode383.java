package leetcode;

import java.util.HashMap;

public class LeetCode383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>(magazine.length());
        for (int i = 0; i < magazine.length(); i++) {
            char key = magazine.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            Integer count = map.get(key);
            if (count == null || count == 0) {
                return false;
            }
            map.put(key, count - 1);
        }
        return true;
    }

    public boolean canConstruct1(String ransomNote, String magazine) {
        int[] array = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            array[magazine.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            if (array[key - 'a'] == 0) {
                return false;
            }
            array[key - 'a'] -= 1;
        }
        return true;
    }
}
