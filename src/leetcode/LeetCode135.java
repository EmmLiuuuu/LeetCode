package leetcode;

import java.util.Arrays;

public class LeetCode135 {

    //自己想的暴力做法
    public static int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int[] candyArray = new int[ratings.length];
        Arrays.fill(candyArray, 1);
        for (int i = 1; i < ratings.length; i++) {
            //从前往后扫描，如果下一个比前一个大，那么直接+1
            if (ratings[i] > ratings[i - 1]) {
                candyArray[i] = candyArray[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                //如果下一个比前一个小，但是糖果数>=前一个的，需要处理，将前一个的糖果+1
                if (candyArray[i - 1] <= candyArray[i]) {
                    for (int j = (i - 1); j >= 0; j--) {
                        //后一个比前一个小，且糖果数与前一个一样，需要+1
                        if (ratings[j + 1] < ratings[j] && candyArray[j] <= candyArray[j + 1]) {
                            candyArray[j] += 1;
                        } else {
                            //当遇到第一个不符合条件的情况时，可以直接跳出
                            break;
                        }
                    }
                }
            } else {
                candyArray[i] = 1;
            }
        }

        int candy = 0;
        for (int i : candyArray) {
            candy += i;
        }

        return candy;
    }

    //https://leetcode-cn.com/problems/candy/solution/fen-fa-tang-guo-by-leetcode/
    public static int candy1(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }

        int[] left2Right = new int[ratings.length];
        int[] right2Left = new int[ratings.length];

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2Right[i] = left2Right[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] > ratings[i]) {
                right2Left[i] = right2Left[i + 1] + 1;
            }
        }

        int candy = 0;
        for (int i = 0; i < ratings.length; i++) {
            candy += Math.max(left2Right[i], right2Left[i]) + 1;
        }

        return candy;
    }

    //化简两个数组的情况
    public static int candy2(int[] ratings) {
        int[] candy = new int[ratings.length];

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        int sum = 0;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
            sum += candy[i];
        }

        return sum + ratings.length + candy[ratings.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 2, 3, 1, 0}));
    }
}
