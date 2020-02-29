package leetcode;

public class LeetCode75 {

    public static void main(String[] args) {
        new LeetCode75().sortColors1(new int[]{2, 1, 2, 0, 1, 0});
    }

    public void sortColors(int[] nums) {
        int[] colors = new int[3];
        for (int num : nums) {
            if (num == 2) {
                colors[2] += 1;
            } else if (num == 1) {
                colors[1] += 1;
            } else {
                colors[0] += 1;
            }
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < colors[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    public void sortColors1(int[] nums) {
        int j = nums.length - 1;
        int i = 0;
        int cur = 0;

        while (cur <= j) {
            //因为curr左边的值已经扫描过了，所以curr要++继续扫描下一位，而与p2交换的值，curr未扫描，要停下来扫描一下，所以curr不用++
            if (nums[cur] == 2) {
                swap(nums, j--, cur);
            } else if (nums[cur] == 0) {
                swap(nums, i++, cur++);
            } else {
                cur++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
