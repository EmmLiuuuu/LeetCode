package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetCode245 {

    /**
     * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
     * <p>
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
     * <p>
     * word1 and word2 may be the same and they represent two individual words in the list.
     * <p>
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * <p>
     * Given word1 = “makes”, word2 = “coding”, return 1.
     * Given word1 = "makes", word2 = "makes", return 3.
     * <p>
     * Note:
     * You may assume word1 and word2 are both in the list.
     */

    public int shortestDistance(String[] words, String word1, String word2) {
        HashMap<String, List<Integer>> map = new HashMap<>(words.length);

        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], key -> new LinkedList<>()).add(i);
        }

        int i = 0;
        int j = 0;
        int distance = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        while (i < list1.size() && j < list2.size()) {
            int temp1 = list1.get(i);
            int temp2 = list2.get(j);
            if (temp1 == temp2) {
                j++;
            } else {
                distance = Math.min(distance, Math.abs(temp1 - temp2));
                if (temp1 < temp2) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return distance;
    }
}
