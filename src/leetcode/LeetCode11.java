package leetcode;

public class LeetCode11 {

    /*
    https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode/
    最初我们考虑由最外围两条线段构成的区域。
    现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
    但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int max = Integer.MIN_VALUE;
        while (left < right) {
            //计算最大的面积
            max = Math.max(Math.min(height[left], height[right]) * (right - left), max);
            //移动较短的边
            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }

        return max;
    }
}
