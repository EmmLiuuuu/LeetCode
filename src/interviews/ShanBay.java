package interviews;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ShanBay {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(5);
//        [[1,2],[2,3],[3,4],[3,5],[4,5],[5,5],[5,6],[6,7],[7,8]]
//        [[2,100],[3,200],[4,300],[5,500],[5,400],[5,250],[6,370],[6,360],[7,380]]
//        [[30,50],[12,2],[3,4],[12,15]]
//        [[5,4],[6,4],[6,7],[2,3]]
        int[][] a = new int[][]{
                {2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}
//                {30,50},{12,2},{3,4},{12,15}
//                {5,4},{6,4},{6,7},{2,3}
//                {1,1},{2,2}
        };

//        System.out.println(maxEnvelopes(a));
//        System.out.println(fun2(list));
        fun1();
        fun1_2();
    }

    private static List<Integer> fun2(List<Integer> array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == 1) {
                array.remove(i);
            }
        }
        return array;
    }
    static class T {
        int height;
        int weight;

        public T(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[" + weight + ", " + height + "]";
        }
    }




    private static void fun1() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == i) {//边界情况
                    System.out.println(i * j);
                    builder.append(i * j).append("\n");
                    continue;
                }
                if (j > 1 && i * j < 10 && (i * (j + 1)) < 10) {
                    System.out.print(i * j + "  ");
                    builder.append(i * j).append("  ");
                    continue;//中间情况
                }
                if (i <= 4 && j == 1) {
                    System.out.print(i + "  ");
                    builder.append(i).append("  ");
                    continue;//前4行
                }
                System.out.print(i * j + " ");
                builder.append(i * j).append(" ");
            }
        }
        System.out.println(builder.toString().equals("1\n2  4\n3  6  9\n4  8 12 16\n5 10 15 20 25\n6 12 18 24 30 36\n7 14 21 28 35 42 49\n8 16 24 32 40 48 56 64\n9 18 27 36 45 54 63 72 81\n"));
//        System.out.println(builder.toString());
    }

    private static void fun1_2() {
        for (int i = 1; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.printf("%2d ", i * j);
            }
            System.out.println();
        }
    }
}
