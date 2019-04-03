package leetcode;

import java.util.Arrays;

public class LeetCode240 {

//    [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]
//            20
//    [[5,6,10,14],[6,10,13,18],[10,13,18,19]]
//            14
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
//                {1,4,7,11,15},
//                {2,5,8,12,19},
//                {3,6,9,16,22},
//                {10,13,14,17,24},
//                {18,21,23,26,30}
//                {-1,3}
                {5,6,10,14},
                {6,10,13,18},
                {10,13,18,19}
        };
        System.out.println(searchMatrix(matrix, 14));;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //sb解法
//        int rowMid = matrix.length / 2;
//        int columnMid = matrix[0].length / 2;
//
//        if (target > matrix[rowMid][columnMid]) {
//            for (int i = rowMid; i < matrix.length; i++) {
////                int te = Arrays.binarySearch(matrix[i], target);
//                if (Arrays.binarySearch(matrix[i], target) >= 0) {
//                    return true;
//                }
//            }
//
//            for (int i = columnMid; i < matrix[rowMid].length; i++) {
//                if (search(matrix, target, 0, matrix.length, i) >= 0) {
//                    return true;
//                }
//            }
//        } else if (target < matrix[rowMid][columnMid]) {
//            for (int i = 0; i <= rowMid; i++) {
//                if (Arrays.binarySearch(matrix[i], target) >= 0) {
//                    return true;
//                }
//            }
//
//            for (int i = 0; i <= columnMid; i++) {
//                if (search(matrix, target, 0, matrix.length, i) >= 0) {
//                    return true;
//                }
//            }
//        } else {
//            return true;
//        }

        //看成一颗平衡二叉树，小的在右上角的左边，大的在右上角的右边
        int row = 0;
        int column = matrix[0].length - 1;

        while (row < matrix.length && column >= 0) {
            if (target == matrix[row][column]) {
                return true;
            } else if (target > matrix[row][column]) {
                ++row;
            } else {
                column--;
            }
        }


        return false;
    }

    public static int search(int[][] matrix, int target, int fromIndex, int toIndex, int column) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = matrix[mid][column];

            if (midVal < target)
                low = mid + 1;
            else if (midVal > target)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

}
