package leetcode;

public class LeetCode169 {

    public int majorityElement(int[] nums) {
        //low逼解法
//         int n_2 = nums.length / 2;
//         HashMap<Integer, Integer> map = new HashMap<>(nums.length);
//         for (int i = 0; i < nums.length; i++) {
//             int temp = map.getOrDefault(nums[i], 0) + 1;
//             map.put(nums[i], temp);
//             if (temp > n_2) {
//                 return nums[i];
//             }
//         }

//         for(Integer i : map.keySet()) {
//             if (map.get(i) > n_2) {
//                 return map.get(i);
//             }
//         }
//         return 0;

        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (candidate.equals(num)) ? 1 : -1;
        }

        //无论如何，总是数量多的数被返回

        return candidate;
    }
}
