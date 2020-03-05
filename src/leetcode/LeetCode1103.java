package leetcode;

public class LeetCode1103 {

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int candy = 1;
        while (candies != 0) {
            res[candy % num_people] = Math.min(candy, candies);
            if (candy < candies) {
                candies -= candy;
            }
        }
        return res;
    }

    //数学公式计算
    public int[] distributeCandies1(int candies, int num_people) {
        // how many people received complete gifts
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / num_people, cols = p % num_people;

        int[] d = new int[num_people];
        for (int i = 0; i < num_people; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * num_people;
            // cols in the last row
            if (i < cols) d[i] += i + 1 + rows * num_people;
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }
}
