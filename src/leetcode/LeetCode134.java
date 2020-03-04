package leetcode;

public class LeetCode134 {

    /*
        gas  = [1,2,3,4,5]
        cost = [3,4,5,1,2]
     */

    //暴力
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;

        start:
        for (int i = 0; i < length; i++) {
            int j = i;
            int oil = 0;
            do {
                if ((oil += gas[j]) >= cost[j]) {
                    oil -= cost[j];
                } else {
                    continue start;
                }
                j = (j + 1) % length;
            } while (j % length != i);

            return i;
        }

        return -1;
    }

    //反证法证明算法的正确性
    //https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode/
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        int currentOil = 0;
        int totalOil = 0;

        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalOil += gas[i] - cost[i];
            currentOil += gas[i] - cost[i];

            if (currentOil < 0) {
                currentOil = 0;
                start += 1;
            }
        }

        return totalOil >= 0 ? start : -1;
    }

    public static int canCompleteCircuit3(int[] gas, int[] cost) {
        flag:
        for (int i = 0; i < gas.length; i++) {
            int oil = 0;
            int index = i;
            do {
                oil += gas[index];
                oil -= cost[index];
                if (oil < 0) {
                    continue flag;
                }
                index += 1;
                index %= (gas.length);
            } while (index != i);
            return index;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit3(new int[]{3, 3, 4}, new int[]{3, 4, 4}));
    }
}
