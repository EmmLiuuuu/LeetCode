package leetcode;

import java.util.LinkedList;

public class LeetCode273 {

    private final String[] STRINGS = new String[]{"Hundred", "Thousand", "Million", "Billion"};

    public static void main(String[] args) {
        LeetCode273 leetCode273 = new LeetCode273();
        System.out.println(leetCode273.numberToWords(51));
        System.out.println(leetCode273.numberToWords(100));
        System.out.println(leetCode273.numberToWords(123));
        System.out.println(leetCode273.numberToWords(1022));
        System.out.println(leetCode273.numberToWords(12345));
        System.out.println(leetCode273.numberToWords(123456));
        System.out.println(leetCode273.numberToWords(1234567));
        System.out.println(leetCode273.numberToWords(12345678));
        System.out.println(leetCode273.numberToWords(123456789));
        System.out.println(leetCode273.numberToWords(1234567890));
        System.out.println(leetCode273.numberToWords(1234567891));
        System.out.println(leetCode273.numberToWords(10000));
        System.out.println(leetCode273.numberToWords(100000));
        System.out.println(leetCode273.numberToWords(1000000));
        System.out.println(leetCode273.numberToWords(10000000));
        System.out.println(leetCode273.numberToWords(100000000));
        System.out.println(leetCode273.numberToWords(1200003001));
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        LinkedList<String> stack = new LinkedList<>();
        //数量级指示
        int index = 0;
        //mod -> [0, 999]
        while (num > 0) {
            int mod = num % 1000;
            num /= 1000;
            int singleDigit = mod % 10;
            int tensDigit = mod % 100;
            int hundredsDigit = mod / 100;

            if (index > 0) {
                if (mod > 0) {
                    stack.addLast(STRINGS[index]);
                    stack.addLast(" ");
                }
            }

            if (tensDigit < 20) {
                if (tensDigit > 0) {
                    stack.addLast(oneToNineteen(tensDigit));
                    stack.addLast(" ");
                }
            } else {
                if (singleDigit > 0) {
                    stack.addLast(oneToNineteen(singleDigit));
                    stack.addLast(" ");
                    stack.addLast(twentyToNinety(tensDigit - singleDigit));
                    stack.addLast(" ");
                } else {
                    stack.addLast(twentyToNinety(tensDigit - singleDigit));
                    stack.addLast(" ");
                }
            }
            if (hundredsDigit > 0) {
                stack.addLast(STRINGS[0]);
                stack.addLast(" ");
                stack.addLast(oneToNineteen(hundredsDigit));
                stack.addLast(" ");
            }

            index++;
        }
        //去除最后的空格符
        stack.removeLast();

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.removeLast());
        }

        return builder.toString();
    }

    private String oneToNineteen(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
            default:
                throw new IllegalArgumentException(String.valueOf(num));
        }
    }

    private String twentyToNinety(int num) {
        switch (num) {
            case 20:
                return "Twenty";
            case 30:
                return "Thirty";
            case 40:
                return "Forty";
            case 50:
                return "Fifty";
            case 60:
                return "Sixty";
            case 70:
                return "Seventy";
            case 80:
                return "Eighty";
            case 90:
                return "Ninety";
            default:
                throw new IllegalArgumentException(String.valueOf(num));
        }
    }
}
