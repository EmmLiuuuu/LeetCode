package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetCode244 {

    /**
     * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be calledrepeatedly many times with different parameters. How would you optimize it?
     *   Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
     *   For example,
     *   Assume that words = [“practice”, “makes”, “perfect”, “coding”, “makes”].
     *   Given word1 = “coding”, word2 = “practice”, return 3.
     *   Given word1 = “makes”, word2 = “coding”, return 1.
     *   Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     * ————————————————
     * 版权声明：本文为CSDN博主「郝春雨」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/haoxiaoxiaoyu/article/details/79208358
     */

    class ShortestWordDistanceII {

        private final HashMap<String, List<Integer>> map;

        public ShortestWordDistanceII(String[] words) {
            map = new HashMap<>(words.length);
            for (int i = 0; i < words.length; i++) {
                map.computeIfAbsent(words[i], key -> new LinkedList<>()).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int i = 0;
            int j = 0;
            int distance = Integer.MAX_VALUE;
            while (i < list1.size() && j < list2.size()) {
                distance = Math.min(distance, Math.abs(list1.get(i) - list2.get(j)));
                if (list1.get(i) < list2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return distance;
        }
    }
}
