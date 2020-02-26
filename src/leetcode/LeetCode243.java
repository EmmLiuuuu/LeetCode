package leetcode;

public class LeetCode243 {

    /**
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
     * <p>
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * <p>
     * Given word1 = “coding”, word2 = “practice”, return 3.
     * Given word1 = "makes", word2 = "coding", return 1.
     * <p>
     * Note:
     * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     * ————————————————
     * 版权声明：本文为CSDN博主「jmspan」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/jmspan/article/details/51082268
     */

    public int shortestDistance(String[] words, String word1, String word2) {
        int shortest = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                index1 = i;
                if (index2 != -1 && Math.abs(index2 - index1) < shortest) {
                    shortest = Math.abs(index2 - index1);
                }
            } else if (word2.equals(words[i])) {
                index2 = i;
                if (index1 != -1 && Math.abs(index1 - index2) < shortest) {
                    shortest = Math.abs(index2 - index1);
                }
            }
        }

        return shortest;
    }
}
