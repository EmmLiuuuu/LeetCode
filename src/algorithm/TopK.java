package algorithm;

public class TopK {

    public static int topK(int[] nums, int start, int end, int k) {
        int temp = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[right] >= temp) {
                right--;
            }
            while (left < right && nums[left] <= temp) {
                left++;
            }
            swap(nums, left, right);
        }
        //将棋子插在合适的位置
        swap(nums, start, left);

        //end - k + 1: TopK在已排序后的数组的下标位置
        if (left > end - k + 1) {
            //k - (end - left + 1): 因为要往左边找，要重新计算k在子数组中的Top几
            return topK(nums, start, left - 1, k - (end - left + 1));
        } else if (left < end - k + 1) {
            //往右边走的话，不变
            return topK(nums, left + 1, end, k);
        } else {
            return nums[left];
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
