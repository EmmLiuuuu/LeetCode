package leetcode;

public class LeetCode80 {

    /**
     * 解法1：
     * 我们使用了两个指针，i 是遍历指针，指向当前遍历的元素；j 指向下一个要覆盖元素的位置。
     * 同样，我们用 count 记录当前数字出现的次数。count 的最小计数始终为 1。
     * 我们从索引 1 开始一次处理一个数组元素。
     * 若当前元素与前一个元素相同，即 nums[i]==nums[i-1]，则 count++。若 count > 2，则说明遇到了多余的重复项。在这种情况下，我们只向前移动 i，而 j 不动。
     * 若 count <=2，则我们将 i 所指向的元素移动到 j 位置，并同时增加 i 和 j。
     * 若当前元素与前一个元素不相同，即 nums[i] != nums[i - 1]，说明遇到了新元素，则我们更新 count = 1，并且将该元素移动到 j 位置，并同时增加 i 和 j。
     * 当数组遍历完成，则返回 j。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xiang-i-7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums 要原地替换的数组
     * @return 处理之后的数组长度
     */
    public static int removeDuplicates(int[] nums) {

        int j = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }

        return j;
    }

    /**
     * 解法2：
     * i为当前遍历到的数字
     * current为当前位置，下一个数字要插入的位置是current+1
     * 如果current位置的数字与current-1位置的数字一致，那么当且仅当i位置的数字与他两不相同，才能插入current+1的位置中
     * 如果current位置的数字与current-1位置的数字不一致，那么无论i位置的数字值为什么，都可以插入current+1的位置中
     * @param nums 要原地替换的数组
     * @return 处理之后的数组长度
     */
    public static int removeDuplicates1(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }

        int current = 1;

        for (int i = 2; i < nums.length; i++) {
            if (nums[current - 1] == nums[current]) {
                if (nums[i] != nums[current]) {
                    nums[++current] = nums[i];
                }
            } else {
                nums[++current] = nums[i];
            }
        }

        return current + 1;
    }

    public static void print(int[] nums, int i) {
        for (int j = 0; j < i; j++) {
            System.out.print(nums[j]);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,2,2,3};
        int result = removeDuplicates(nums);
        System.out.println(result);
        print(nums, result);
    }
}
