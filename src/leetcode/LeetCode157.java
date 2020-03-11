package leetcode;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function will only be called once for each test case.
 */
public class LeetCode157 {

    public int read(char[] buf, int n) {
        for (int i = 0; i < n; i += 4) {
            char[] tmp = new char[4];
            int len = read4(tmp);
            System.arraycopy(tmp, 0, buf, i, Math.min(n - i, len));
            if (len < 4) {
                return Math.min(len + i, n);
            }
        }
        return n;
    }

    private int read4(char[] tmp) {
        return -1;
    }
}
