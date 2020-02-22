package leetcode;

import java.util.Arrays;

public class ChineseToInteger {

    public static void main(String[] args) {
        System.out.println(toInteger("三千九百零三万三千五百七十三"));
        System.out.println(toInteger("三千九百八十三万"));
        System.out.println(toInteger("一千万"));
        System.out.println(toInteger("一千零一万"));
        System.out.println(toInteger("一万零一"));
        System.out.println(toInteger("九千八百"));
        System.out.println(toInteger("九千零三十"));
        System.out.println(toInteger("九千零三"));
        System.out.println(toInteger("九千"));
        System.out.println(toInteger("八百"));
        System.out.println(toInteger("八十三"));
        System.out.println(toInteger("一"));
        System.out.println(toInteger("零"));
    }

    //三千九百零三万三千五百
    //39033500
    public static int toInteger(String s) {

        int[] bit = new int[4];

        String[] temp = s.split("万");
        boolean flag = temp.length > 1;
        int sum = 0;

        for (String string : temp) {
            String oneToNine = mapOneToNine(string);
            if (oneToNine.length() >= 5) {
                throw new IllegalArgumentException("输入错误: " + s);
            }
            mapBit(bit, string);
            int index = 0;
            int level = 1000;
            for (Character oTn : oneToNine.toCharArray()) {
                for (int i1 : bit) {
                    if (i1 == 0) {
                        index++;
                        level /= 10;
                    } else if (i1 == 1) {
                        break;
                    }
                }
                if (index < bit.length && bit[index] == 1) {
                    sum += ((oTn - '0') * level);
                } else {
                    sum += (oTn - '0');
                }
                index++;
                level /= 10;
            }

            if (flag) {
                sum *= 10000;
                flag = false;
            }

            Arrays.fill(bit, 0);
        }
        return sum;
    }

    private static String mapOneToNine(String s) {
        StringBuilder builder = new StringBuilder();
        for (Character c : s.toCharArray()) {
            switch (c) {
                case '九':
                    builder.append(9);
                    break;
                case '八':
                    builder.append(8);
                    break;
                case '七':
                    builder.append(7);
                    break;
                case '六':
                    builder.append(6);
                    break;
                case '五':
                    builder.append(5);
                    break;
                case '四':
                    builder.append(4);
                    break;
                case '三':
                    builder.append(3);
                    break;
                case '二':
                    builder.append(2);
                    break;
                case '一':
                    builder.append(1);
                    break;
                case '零':
                    builder.append(0);
            }
        }
        return builder.toString();
    }

    private static void mapBit(int[] bit, String s) {
        for (Character c : s.toCharArray()) {
            switch (c) {
                case '千':
                    bit[0] = 1;
                    break;
                case '百':
                    bit[1] = 1;
                    break;
                case '十':
                    bit[2] = 1;
            }
        }
    }
}
