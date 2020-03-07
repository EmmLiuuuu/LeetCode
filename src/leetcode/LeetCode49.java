package leetcode;

import java.util.*;

public class LeetCode49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        int[] charCounts = new int[26];
        for (String str : strs) {
            Arrays.fill(charCounts, 0);
            for (int i = 0; i < str.length(); i++) {
                charCounts[str.charAt(i) - 'a']++;
            }
            StringBuilder builder = new StringBuilder("#");
            for (int i = 0; i < charCounts.length; i++) {
                int charCount = charCounts[i];
                if (charCount != 0) {
                    builder.append(i).append('-').append(charCount).append('#');
                }
            }
            map.computeIfAbsent(builder.toString(), key -> new ArrayList<>(8)).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
