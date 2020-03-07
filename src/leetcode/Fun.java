package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fun {

    public static List<String> fun(int[] nums) {
        if (nums == null || nums.length == 0) {//边界条件
            return null;
        }
        List<String> result = new ArrayList<>(nums.length + 1);//初始化数组大小为nums数组的长度+1，防止进行扩容操作，花费时间
        for (int n : nums) {//遍历
            if (n > 26) {//如果其中数字大于26的话，进行进制转换
                StringBuilder builder = new StringBuilder();
                while (n > 26) {
                    int temp = n % 26;
                    n /= 26;
                    builder.append(temp == 0 ? 'Z' : (char) (temp + 64));// 1-》A  1 + 'A' - 1
                }
                builder.append((char) (n + 64));//别忘了还有n
                result.add(builder.reverse().toString());//将进制转换结果翻转后加入最终结果
            } else {//如果不大于26，直接加进最终结果
                if (n == 0) {
                    result.add("0");//0的处理
                } else if (n != 26){
                    result.add(String.valueOf((char) (n + 64)));
                } else {
                    result.add("Z");
                }
            }
        }
        return result;
    }

    //用来表示转换结果
    static class Node {
        String hn;
        String title;
        Node(String hn, String title) {
            this.hn = hn;
            this.title = title;
        }

        //在输出时要重写toString方法
        @Override
        public String toString() {
            return "{" +
                    "\"hn\"=" + "\"" + hn + '\"' +
                    ", \"title\"=" + "\"" + title + '\"' +
                    '}';
        }
    }

    public static List<Node> fun1(String[] array) {

        if (array == null || array.length == 0) {//边界条件
            return null;
        }
        List<Node> nodes = new ArrayList<>(array.length + 1);//用来保存最后结果

        int[] level = new int[array.length];//记录层级状态
        for (String n : array) {//遍历
            int index = n.indexOf(" ");//找出第一个空格
            String temp = n.substring(0, index);//找出前面的#号
            String value = n.substring(index).trim();//找出后面的值
            Node node = new Node("", value);//先新建一个node

            level[temp.length() - 1] += 1;//在对应层级加1
            int indexOf = indexOf0(level);
            if (temp.length() < indexOf) {//如果出现 ### #这样的情况
                for (int i = temp.length(); i < level.length; i++) {
                    level[i] = 0;//重制层级状态，但不动下标为temp.length()的值
                }
            }

            StringBuilder builder = new StringBuilder();//防止String对象之间直接相加，生成多个额外的对象

            indexOf = indexOf0(level);
            for (int i = 0; i < indexOf - 1; i++) {
                builder.append(level[i]);
                builder.append(".");
            }
            builder.append(level[indexOf - 1]);//进行数字组装

            node.hn = builder.toString();//得出最终层级string结果
            nodes.add(node);//添加进最终结果中
        }
        return nodes;//返回
    }

    private static int indexOf0(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(fun(new int[]{1, 2, 3, 26, 27, 28}));
        for (int i = 0; i < 1100; i++) {
            System.out.println(fun(new int[]{i}));
        }

        System.out.println(parser(new String[]{"# a", "## b", "## c", "### d", "# e", "# f"}));
        System.out.println(parser(new String[]{"# a", "## b", "## c", "### d", "## e", "# f"}));
        System.out.println(parser(new String[]{"# a", "## b", "## c", "### d", "## e", "### f", "# g"}));
    }


    public static List<Node> parser(String[] strings) {
        int[] levels = new int[strings.length];
        List<Node> result = new ArrayList<>();

        String pre = "#";
        for (String str : strings) {
            String[] splits = str.split(" ");
            assert splits.length == 2;
            Node node = new Node("", splits[1]);
            levels[splits[0].length() - 1] += 1;
            if (splits[0].length() == 1) {
                Arrays.fill(levels, 1, pre.length(), 0);
            }
            int i = 0;
            for (; i < splits[0].length() - 1; i++) {
                node.hn = node.hn + levels[i] + ".";
            }
            node.hn = node.hn + levels[i];
            result.add(node);
            pre = splits[0];
        }

        return result;
    }
}
