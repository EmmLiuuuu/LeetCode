package interviews;

import java.util.Scanner;

public class Netease1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            String []arr = temp.split(" ");
            if (arr.length == 0) {
                System.out.print(temp);
                continue;
            }
            if (arr.length == 1) {
                boolean flag = false;
                for (int i = 0; i < temp.length(); i++) {
                    if (!check(temp.charAt(i))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    for (int j = arr[0].length() - 1; j >= 0; j--) {
                        if (check(arr[0].charAt(j))) {
                            int k;
                            StringBuilder sub = new StringBuilder();
                            for (k = j; k >= 0 && check(arr[0].charAt(k)); k--) {
                                sub.append(arr[0].charAt(k));
                            }
                            j = k + 1;
                            System.out.print(sub.reverse().toString());
                        } else {
                            System.out.print(arr[0].charAt(j));
                        }
                    }
                } else {
                    System.out.print(temp);
                }
                continue;
            }

            for (int i = arr.length - 1; i > 0; i--) {
                for (int j = arr[i].length() - 1; j >= 0; j--) {
                    if (check(arr[i].charAt(j))) {
                        int k;
                        StringBuilder sub = new StringBuilder();
                        for (k = j; k >= 0 && check(arr[i].charAt(k)); k--) {
                            sub.append(arr[i].charAt(k));
                        }
                        j = k + 1;
                        System.out.print(sub.reverse().toString());
                    } else {
                        System.out.print(arr[i].charAt(j));
                    }
                }
                System.out.print(" ");
            }
            System.out.print(arr[0]);
        }
    }

    private static boolean check(char a) {
        return a >= 'a' && a <= 'z' ||
                a >= 'A' && a <= 'Z' ||
                a >= '0' && a <= '9';
    }
}
