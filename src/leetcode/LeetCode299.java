package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LeetCode299 {

    //最傻逼的解法
    public static String getHint(String secret, String guess) {
        Set<Integer> set = new HashSet<>();
        HashMap<Character, Integer> map = new HashMap<>(8);
        int a = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++a;
                set.add(i);
            } else {
                map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
            }
        }

        int b = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (map.containsKey(guess.charAt(i))) {
                if (set.contains(i)) {
                    continue;
                }
                int count = map.get(guess.charAt(i));
                if (count > 0) {
                    b += 1;
                    map.put(guess.charAt(i), count - 1);
                }
            }
        }

        return a + "A" + b + "B";
    }

    //双桶计数
    public static String getHint1(String secret, String guess) {
        char zero = '0';
        int[] countArray = new int[10];
        int[] indexArray = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                indexArray[secret.charAt(i) - zero] += 1;
            } else {
                countArray[secret.charAt(i) - zero] += 1;
            }
        }

        int a = 0;
        int b = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (indexArray[guess.charAt(i) - zero] > 0) {
                ++a;
                indexArray[guess.charAt(i) - zero] -= 1;
            } else if (countArray[guess.charAt(i) - zero] > 0) {
                ++b;
                countArray[guess.charAt(i) - zero] -= 1;
            }
        }

        return a + "A" + b + "B";
    }

    public static String getHint2(String secret, String guess) {
        int[] countArray = new int[10];
        int a = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                //记录原字符串各个数字出现次数
                countArray[secret.charAt(i) - '0'] += 1;
                //记录猜测字符串各个数字出现次数
                countArray[guess.charAt(i) - '0'] -= 1;
            }
        }

        int b = 0;
        //最终得出来的结果为：未猜测中的数字个数+猜测中且位置一致的数字出现个数
        for (int i = 0; i < 10; i++) {
            if (countArray[i] > 0) {
                b += countArray[i];
            }
        }

        //length - 上者得出的结果 - 猜测中且位置一致的数字出现个数 = 猜测中的数字个数
        return a + "A" + (secret.length() - b - a) + "B";
    }

    public static String getHint3(String secret, String guess) {
        int[] countArray = new int[10];
        int a = 0;
        int b = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                //当countArray[secret.charAt(i) - '0'] += 1后当前位置的值仍小于等于0，则说明此数字曾经出现过，b+1
                if ((countArray[secret.charAt(i) - '0'] += 1) <= 0) {
                    ++b;
                }
                //同理，当countArray[guess.charAt(i) - '0'] -= 1后当前位置的值仍大于等于0，则说明此数字曾经出现过，b+1
                if ((countArray[guess.charAt(i) - '0'] -= 1) >= 0) {
                    ++b;
                }
            }
        }

        return a + "A" + b + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint1("1123", "0111"));
    }
}
