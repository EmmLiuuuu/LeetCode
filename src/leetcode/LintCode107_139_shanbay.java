package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LintCode107_139_shanbay {

    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("a");
//        dict.add("aaa");
        System.out.println(wordBreak("a", dict));
    }

    public static boolean wordBreak(String word, Set<String> dict) {
        int[] list = new int[word.length() / 32 + 1];
        list[0] = 1;
        // list[0] = true;

        for (int i = 1; i < word.length()+1; ++i) {
            for (int j = 0; j < i; ++j) {
                if ((list[j / 32] & (1 << (j % 32))) == (1 << (j % 32)) && dict.contains(word.substring(j, i))) {
                    list[i / 32] |= (1 << i);
                    break;
                }
            }
        }
        int temp = (1 << (word.length() % 32));
        return (list[word.length() / 32] & (1 << (word.length() % 32)))  == (1 << (word.length() % 32));
    }


}
