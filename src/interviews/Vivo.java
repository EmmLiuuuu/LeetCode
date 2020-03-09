package interviews;

import java.util.*;

/**
 * 第二输入个是一个整数，返回多个个位数乘积为这个整数组合起来的最小值
 * 比如 输入100 返回455 输入36返回49
 */
public class Vivo {

    static List<String> res = new ArrayList<>();
    static Map<Integer, String> memo = new HashMap<>(8);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        helper(n, true);
        System.out.println(res);
    }

    public static String helper(int n, boolean flag) {
        if (n < 10) {
            return String.valueOf(n);
        }

        String cache = memo.get(n);
        if (cache != null) {
            return cache;
        }

        if (check(n)) {
            String nStr = String.valueOf(n);
            memo.put(n, nStr);
            return nStr;
        }

        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                int div = n / i;
                String s = String.valueOf(i);
                s += helper(div, false);
                if (flag) {
                    memo.put(n, s);
                    res.add(s);
                } else {
                    memo.put(n, s);
                    return s;
                }
            }
        }
        return "";
    }

    public static boolean check(int n) {
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
