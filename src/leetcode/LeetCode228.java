package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

示例 1:

输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
示例 2:

输入: [0,2,3,4,6,8,9]
输出: ["0","2->4","6","8->9"]
解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/summary-ranges
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode228 {

    public List<String> summaryRanges(int[] nums) {
        int i = 0;
        List<String> list = new ArrayList<>(nums.length);
        while (i < nums.length) {
            int start = i;
            while (i < nums.length && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            if (start == i) {
                list.add(String.valueOf(nums[i]));
            } else {
                list.add(nums[start] + "->" + nums[i]);
            }
            i++;
        }
        return list;
    }
}
