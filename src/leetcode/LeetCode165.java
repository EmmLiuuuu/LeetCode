package leetcode;

public class LeetCode165 {

    public static void main(String[] args) {
        System.out.println(compareVersion("1", "1"));
    }

    public static int compareVersion(String version1, String version2) {
        //感觉像是easy题
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < s1.length && j < s2.length) {
            if (Integer.parseInt(s1[i]) > Integer.parseInt(s2[j])) {
                return 1;
            } else if (Integer.parseInt(s1[i]) < Integer.parseInt(s2[j])) {
                return -1;
            }
            ++i;
            ++j;
        }

        if (s1.length > s2.length) {
            for (int k = i; k < s1.length; k++) {
                if (Integer.parseInt(s1[k]) > 0) {
                    return 1;
                }
            }
        } else if (s1.length < s2.length) {
            for (int k = j; k < s2.length; k++) {
                if (Integer.parseInt(s2[k]) > 0) {
                    return -1;
                }
            }
        } else {
            return 0;
        }

        return 0;
    }
}
