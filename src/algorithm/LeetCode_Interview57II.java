package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Interview57II {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new LeetCode_Interview57II().findContinuousSequence(1)));
    }

    public int[][] findContinuousSequence(int target) {
        int n = target / 2 + 1;
        int i = 1, j = i;
        int sum = i;
        List<int[]> res = new ArrayList<>(8);
        while (i <= n && j <= n) {
            if (sum < target) {
                sum += ++j;
            } else if (sum > target) {
                sum -= i++;
            } else {
                int[] list = new int[j - i + 1];
                int index = 0;
                for (int k = i; k <= j; k++) {
                    list[index++] = k;
                }
                res.add(list);
                sum -= i++;
            }
            if (i > sum - j) {
                break;
            }
        }
        return res.toArray(new int[0][0]);
    }
}
