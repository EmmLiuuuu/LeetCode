package leetcode;

public class LeetCode55 {

    public boolean canJump(int[] nums) {
        int[] indexFlag = new int[nums.length];
        indexFlag[nums.length - 1] = 1;
        return canJump1(nums, indexFlag, 0);
    }

    //回溯法
    public boolean canJump(int[] nums, int position) {
        if (position == nums.length - 1) {
            return true;
        }

        for (int i = Math.min(position + nums[position], nums.length - 1); i > position; i--) {
            if (canJump(nums, i)) {
                return true;
            }
        }

        return false;
    }

    //自顶向下 记忆表剪枝 记录当前下标是否可达，如果不可达，下一次递归时跳过该坐标
    public boolean canJump1(int[] nums, int[] indexFlag, int position) {
        if (indexFlag[position] != 0) {
            return indexFlag[position] == 1;
        }

        for (int i = Math.min(position + nums[position], nums.length - 1); i > position; i--) {
            if (canJump1(nums, indexFlag, i)) {
                indexFlag[position] = 1;
                return true;
            }
        }

        indexFlag[position] = -1;
        return false;
    }

    //自底向上 如果 j 可以到达 i ，那么j为可达下标，再往前找可达 j 的可达下标
    public boolean canJump1(int[] nums) {
        int[] indexFlag = new int[nums.length];
        indexFlag[nums.length - 1] = 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= Math.min(nums.length - 1, i + nums[i]); j++) {
                if (indexFlag[j] == 1) {
                    indexFlag[i] = 1;
                    break;
                }
            }
        }

        return indexFlag[0] == 1;
    }

    //聪明方法 自底向上的优化，如果 i 加上可跳跃值 大于等于 "当前终点" ，那么可以认为 i 可达终点的，那么将 i 设置为 "当前终点" 再找下一个可达 i 的下标（下一个终点），
    //如果走到了数组头，那么说明数组起点存在一条路径可以到达数组终点
    public boolean canJump2(int[] nums) {
        //初始化终点为数组末尾
        int leftIndex = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            //不断找 "当前终点"
            if (nums[i] + i >= leftIndex) {
                leftIndex = i;
            }
        }

        //判断 "当前终点" 是否到达了数组起点
        return leftIndex == 0;
    }

    //顺着来版本 初始化数组起点为 "当前终点" ，从数组起点开始，找到下一个当前位置能到达的最远下标，将其作为新的 "当前终点" ，不断得找能跳超越 "当前终点" 的下标，
    //当找到能跳越数组终点的下标时，说明数组起点存在一条路径可以到达数组终点
    public boolean canJump3(int[] nums) {
        int rightIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            //遍历指针超越 "当前终点" 说明 "当前终点" 左侧的所有下标都无法跳跃 "当前终点" ，无法到达数组终点
            if (i > rightIndex) {
                return false;
            }

            if (nums[i] + i > rightIndex) {
                //找到超越 "当前终点" 的下标，并更新 "当前终点" 位置
                rightIndex = nums[i] + i;
            }
        }

        //判断 "当前终点" 是否超越了数组终点
        return rightIndex >= nums.length - 1;
    }

}
