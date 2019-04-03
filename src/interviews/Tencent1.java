package interviews;

import java.io.File;

public class Tencent1 {

    public static void main(String[] args) {
        System.out.println(findMax(new int[] {1,2,7,4,5,6}, 1, 0));
    }

    public static int findMax(int[] nums, int max, int index) {
        if (index >= nums.length) {
            return max;
        }

        return Math.max(findMax(nums, max, index + 1), nums[index]);
    }

    public static void printFile(File file) {
        if (file.isFile()) {
            System.out.println("您给定的是一个文件"); // 判断给定目录是否是一个合法的目录，如果不是，输出提示
        } else {
            File[] fileLists = file.listFiles(); // 如果是目录，获取该目录下的内容集合

            for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
                System.out.println(fileLists[i].getName());    //输出元素名称
                if (fileLists[i].isDirectory()) {    //判断元素是不是一个目录
                    printFile(fileLists[i]);    //如果是目录，继续调用本方法来输出其子目录
                }
            }
        }
    }
}
