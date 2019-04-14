package interviews;

import java.util.*;

public class JD {

    static class TreeNode {
        public int val;
        public TreeNode parent;
        public List<TreeNode> treeNodes = new LinkedList<>();
        public TreeNode(int x, TreeNode parent) { val = x; this.parent = parent; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeNode)) return false;
            TreeNode node = (TreeNode) o;
            return val == node.val &&
                    parent.equals(node.parent) &&
                    treeNodes.equals(node.treeNodes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, parent, treeNodes);
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HashMap<String, TreeNode> map = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();
        int n = in.nextInt();

        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            TreeNode parent = map.get(String.valueOf(y));

            if (parent == null) {
                parent = new TreeNode(y, null);
                map.put(String.valueOf(y), parent);
            }

            TreeNode child = map.get(String.valueOf(x));
            if (child == null) {
                child = new TreeNode(x, parent);
                map.put(String.valueOf(x), child);
            }
            parent.treeNodes.add(child);
        }

        TreeNode root = map.get("1");
        for (TreeNode child : root.treeNodes) {
            count.put(String.valueOf(child.val), size(child));
        }

        int result = 0;
        n--;
        while (n > 0) {
            int rootChildren = 0;
            for (TreeNode child : root.treeNodes) {
                int childCount = count.get(String.valueOf(child.val));
                rootChildren = childCount > 0 ? rootChildren + 1 : rootChildren;
                if (childCount - 1 == 0) {
                    root.treeNodes.remove(child);
                }
            }
            n -= rootChildren;
            result++;
        }
        System.out.println(result);
        in.close();
    }

    private static int size(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        while (queue.size() > 0) {

            int parents = queue.size();

            while (parents > 0) {
                TreeNode child = queue.pop();
                count += child.treeNodes.size();
                queue.addAll(child.treeNodes);
                parents--;
            }
        }
        return count;
    }
}
