package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode229 {

    public static void main(String[] args) {

    }

    public List<Integer> majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        //摩尔投票，因为是 n/3，所以只会有答案为2个或者是1个的情况
        int candidateA = nums[0];//候选人A
        int candidateB = nums[0];//候选人B
        int countA = 0;
        int countB = 0;
        for (int num : nums) {
            if (candidateA == num) {//给A投了一票
                countA++;
                continue;
            }

            if (candidateB == num) {//给B投了一票
                countB++;
                continue;
            }

            if (countA == 0) {//当A的票数为0时，换一个候选人
                candidateA = num;
                countA = 1;
                continue;
            }

            if (countB == 0) {//当B的票数为0时，换一个候选人
                candidateB = num;
                countB = 1;
                continue;
            }

            //两者都不相等的情况（都不给AB投票）
            countA--;
            countB--;
        }

        countA = 0;
        countB = 0;

        for (int i : nums) {
            if (candidateA == i) {
                countA++;
                continue;
            }
            if (candidateB == i) {
                countB++;
            }
        }

        List<Integer> list = new ArrayList<>(2);

        if (countA > nums.length / 3) {
            list.add(candidateA);
        }

        if (countB > nums.length / 3) {
            list.add(candidateB);
        }

        return list;
    }
}
