package leetcode;

public class LeetCode33 {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high){
            int mid = (low + high) >>> 1;
            int midValue = nums[mid];
            if(midValue == target){
                return mid;
            }else if(midValue < target){
                //未翻转的话，正常情况下，需要向右查找
                //如果最左边都大于中间值且右边值都小于target，则需要向左查找
                //4,5,6,7,0,1,2 -> 5
                if(nums[low] > midValue && nums[high] < target){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }else{
                //未翻转的话，正常情况下，需要向左查找
                //如果最右边都小于中间值且最左边都大于target
                //4,5,6,7,0,1,2 -> 1
                if(nums[high] < midValue && nums[low] > target){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
