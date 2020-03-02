package leetcode;

public class LeetCode34 {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int left = binarySearch(nums, target, true);
        if (left == nums.length || nums[left] != target) {
            return result;
        }
        result[0] = left;
        result[1] = binarySearch(nums, target, false) - 1;
        return result;
    }

    public int binarySearch(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] > target || left && nums[mid] == target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int[] searchRange1(int[] nums, int target) {
        int right = higher_bound(nums, target);
        int left = lower_bound(nums, target);
        return new int[]{left, right};
    }

    private int higher_bound(int[] a, int v) {
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (r + l) >> 1;
            if (a[mid] < v) {
                l = mid + 1;
            } else if (a[mid] > v) {
                r = mid - 1;
            } else {
                //找到后继续往右边找
                res = mid;
                l = mid + 1;
            }
        }
        return res;
    }

    private int lower_bound(int[] a, int v) {
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (r + l) >> 1;
            if (a[mid] > v) {
                r = mid - 1;
            } else if (a[mid] < v) {
                l = mid + 1;
            } else {
                //找到后继续往左边走
                res = mid;
                r = mid - 1;
            }
        }
        return res;
    }
}
