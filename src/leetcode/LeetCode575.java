package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode575 {

    /*
    最大极端情况，所有糖都不重样，那马冬梅也只能得到一半。
    中间情况，每个糖都有两个，那马冬梅刚好能得到所有种类，数量跟第一种一样。
    最小极端情况，就一种糖，那马冬梅就只能得到一种。

    思想，不管有多少种，先每种一个给妹妹，如果才给了妹妹几个，所有的种类就都给过了，那后面的都是重复的了。
    如果给了妹妹一半的数量，还有不重样的，那马冬梅最多也就只有一半数量的种类。
    所以说到最后，看（数量的一半）和（所有的种类）哪个先达到，也就是取两者中较小的值。
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        //统计糖果种类
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }
}
