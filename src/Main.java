import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        int []a = new int[] {0,0,1,1,1,2,2,3,3,4,4,5,5};
//        int []b = new int[] {1,2,3,0,0,0};
//        int []c = new int[] {2,5,6};
//        merge(b,3,c,3);
//        singleNumber(new int[] {2,2,3,2});
//        System.out.println(removeDuplicates(a));

//        System.out.println(findKthLargest(new int[] {3,2,1,5,6,4}, 2));

//        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));

        String str = "str";
        String str1 = new String("str");
        System.out.println(str == str1);

        List<?> list = new ArrayList<>();
//        Stack<String> strings = new Stack<>();

//        Thread
    }

    public static int removeDuplicates(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[sum] != nums[i]) {
                nums[++sum] = nums[i];
//                sum++;
            }
        }
        return sum;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            int index = 0;
            for (int i = 0; i < n; i++) {
                nums1[index++] = nums2[i];
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            int index = 0;
            for (int j = 0; j < m; j++) {
                if (nums2[i] > nums1[j]) {
//                    continue;
                    index++;
                } else {
//                    int index = j;
                    index = j;
                    for (int k = m - 1; k >= j && k < nums1.length; k--) {
                        nums1[k + 1] = nums1[k];
                    }
                    break;
                }
            }
            nums1[index] = nums2[i];
            m++;
        }
    }

    public static int singleNumber(int[] nums) {
//         int i = 0, j = 1;
//         while(j < nums.length && i < nums.length) {
//             if (nums[i] == nums[j]) {
//                 if (i == j) {
//                     j++;
//                     continue;
//                 }
//                 i++;
//                 j = 0;

//             } else {
//                 j++;
//             }
//         }
//         return nums[i];    n方解法

        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            b = (b ^ nums[i]) & ~a;
            a = (a ^ nums[i]) & ~b;
        }
        return b;
    }

    public static int findKthLargest(int[] nums, int k) {
        return nums[part(nums, 0, nums.length - 1, k)];
    }

    public static int part(int[] nums, int left, int right, int k) {

        int i = left;
        int j = right;
        if (left > right) {
            return -1;
        }

        while (i != j) {
            while (i < j && nums[j] < nums[left]) j--;
            while (i < j && nums[i] >= nums[left]) i++;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }


        int temp = nums[left];
        nums[left] = nums[i];
        nums[i] = temp;

        if (i == k - 1) {
            return i;
        } else if (i > k - 1) {
            return part(nums, left, i - 1, k);
        } else {
            return part(nums, i + 1, right, k);
        }
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            int sum = nums[0] + nums[1];
            return nums[0] > nums[1] ? (nums[0] > sum ? nums[0] : sum) : (nums[1] > sum ? nums[1] : sum);
        }
        int max = nums[0];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            if (temp > max) {
                max = temp;
            }
            if (temp < 0) {
                temp = 0;
            }
        }
        return max;
    }
}
