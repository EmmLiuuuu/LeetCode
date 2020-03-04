package algorithm;

public class VerifyPostOrder {

    //后续遍历特性，先输出左右子树，再输出根节点，最后一个一定是根节点
    public boolean verifyPostOrder(int[] postOrder) {
        if (postOrder.length <= 2) {
            return true;
        }

        return check(postOrder, 0, postOrder.length - 1);
    }

    boolean check(int[] postOrder, int L, int R) {
        //当树为空或为自身时，必定为true
        if (L >= R) {
            return true;
        }
        int root = postOrder[R];
        int mid = L;
        // 找到左子树部分
        for (; mid < R; mid++) {
            if (postOrder[mid] > root) {
                break;
            }
        }
        // 找到右子树部分, 并进行检查
        int j = mid;
        for (; j < R; j++) {
            if (postOrder[j] < root) {
                //如果右子树中还存在比根节点小的，那么说明不为后续遍历
                return false;
            }
        }
        boolean left = true, right = true;
        // 检查左子树部分是否为二叉树
        if (mid > L) {
            //此时，mid的位置右子树的叶子节点，需要回退一位
            left = check(postOrder, L, mid - 1);
        }
        // 检查右子树部分是否为二叉树
        if (mid < R) {
            //记得去除根节点
            right = check(postOrder, mid, R - 1);
        }
        return left && right;
    }
}
