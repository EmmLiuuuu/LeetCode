package leetcode;

import java.util.LinkedList;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Note:
 * The read function may be called multiple times.
 */
public class LeetCode158 {

    LinkedList<Character> queue = new LinkedList<>();

    public int read(char[] buf, int n) {
        int i = 0;
        // 队列不为空时，先读取队列中的暂存字符
        while (!queue.isEmpty() && i < n) {
            buf[i++] = queue.removeFirst();
        }

        for (; i < n; i += 4) {
            char[] tmp = new char[4];
            int len = read4(tmp);

            // 如果读到字符多于我们需要的字符，需要暂存这些多余字符
            if (len > n - i) {
                System.arraycopy(tmp, 0, buf, i, n - i);
                // 把多余的字符存入队列中
                for (int j = n - i; j < len; j++) {
                    queue.addLast(tmp[j]);
                }
            } else {
                // 如果读到的字符少于我们需要的字符，直接拷贝
                System.arraycopy(tmp, 0, buf, i, len);
            }

            if (len < 4) {
                // 同样的，如果读不满4个，说明数据已经读完，返回总所需长度和目前已经读到的长度的较小的
                return Math.min(n, i + len);
            }
        }
        // 如果到这里，说明都是完美读取，直接返回n
        return n;
    }

    private int read4(char[] tmp) {
        return -1;
    }
}
