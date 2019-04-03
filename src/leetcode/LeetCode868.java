package leetcode;

public class LeetCode868 {

    public static void main(String[] args) {
        System.out.println(binaryGap(22));
    }

    public static int binaryGap(int N) {
        int result = 0;
        int oneCount = 0;
        int index = 0;
        int temp = 0;
        while (N >>> index > 0) {
            if (((N >>> index) & 1) == 1) {
                oneCount += 1;
            }
            if (oneCount == 1) {
                temp += 1;
            } else if (oneCount == 2) {
                result = Math.max(temp, result);
                temp = 0;
                oneCount = 0;
                continue;
            }
            index++;
        }
        return result;
    }
}
