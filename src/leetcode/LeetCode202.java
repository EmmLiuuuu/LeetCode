package leetcode;

import java.util.LinkedList;

public class LeetCode202 {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        int powSum = 11;
        LinkedList<Integer> list = new LinkedList<>();
        while (powSum > 10) {
            powSum = 0;
            while (n >= 10) {
                list.add(n % 10);
                n /= 10;
            }
            list.add(n);

            while (list.size() > 0) {
                int pop = list.pop();
                powSum += (pop * pop);
            }
            n = powSum;
        }

        return powSum == 1;
    }
}
