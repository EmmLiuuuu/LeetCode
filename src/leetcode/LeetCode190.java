package leetcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeetCode190 {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = format.parse("2019-03-01 12:00:00");
        Date date1 = format.parse("2020-03-01 13:00:00");

        long dateTime = date.getTime();
        long dateTime1 = date1.getTime();

        System.out.println((dateTime1 - dateTime) / (1000 * 60 * 60 * 24));
        System.out.println(reverseBits(43261596));
    }

    public static int reverseBits(int n) {
        int result = 0;
        int mask = Integer.MIN_VALUE;
        int tte = 0;

        for (int i = 31; i >= 0; i--) {
            mask = mask & n;
            if (i >= tte) {
                result |= (mask >>> (i - tte++));
            } else {
                result |= (mask << (tte++ - i));
            }
            mask = Integer.MIN_VALUE >>> tte;
        }

        return result;
    }
}
