package algorithm;

public class SearchTree {

    private TreeNode root = null;

    public static void main(String[] args) {
        SearchTree searchTree = new SearchTree();
        searchTree.put(15);
        searchTree.put(10);
        searchTree.put(9);
        searchTree.put(20);
        searchTree.put(18);
        searchTree.put(25);
        searchTree.put(16);
        searchTree.put(19);
        searchTree.delete(15);
    }

    public void put(int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode p = root;
        while (p != null) {
            if (newNode.val > p.val) {
                if (p.right == null) {
                    p.right = newNode;
                    newNode.parent = p;
                    break;
                } else {
                    p = p.right;
                }
            } else if (newNode.val < p.val) {
                if (p.left == null) {
                    p.left = newNode;
                    newNode.parent = p;
                    break;
                } else {
                    p = p.left;
                }
            } else {
                break;
            }
        }
    }

    public TreeNode predecessor(TreeNode node) {
        if (node.left != null) {
            return findMax(node.left);
        } else {
            TreeNode parent = node.parent;
            TreeNode p = node;
            while (parent != null) {
                if (parent.right == p) {
                    return parent;
                }

                p = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public TreeNode findMax(TreeNode node) {
        if (node.right == null) {
            return node;
        }

        return findMax(node.right);
    }

    public TreeNode findMin(TreeNode node) {
        if (node.left == null) {
            return node;
        }

        return findMin(node.left);
    }

    /**
     * 后继节点：比node大的最小值
     * 获取node节点的后继节点，后继节点的左子树一定为空
     *
     * @param node node
     * @return 后继节点
     */
    public TreeNode successor(TreeNode node) {
        if (node.right != null) {
            //如果右子节点存在的话，直接找右子节点最小值
            return findMin(node.right);
        } else {
            //如果右子节点不存在，那么得往上找第一个比node大的父节点
            TreeNode parent = node.parent;
            TreeNode p = node;
            while (parent != null) {
                //node位于父节点的左子树中，直接返回（说明node比父节点小）
                if (parent.left == p) {
                    return parent;
                }
                p = parent;
                parent = parent.parent;
            }
            return null;
        }
    }

    /**
     * 用y替换x
     *
     * @param x 被替换的节点
     * @param y 取代x的节点
     */
    private void trans(TreeNode x, TreeNode y) {
        TreeNode parent = x.parent;
        if (parent == null) {
            root = y;
        } else {
            if (parent.left == x) {
                parent.left = y;
            } else {
                parent.right = y;
            }
        }
        if (y != null) {
            y.parent = parent;
        }
    }

    /**
     * 删除节点
     *
     * @param val 待删除的值
     */
    public void delete(int val) {
        //先找到节点
        TreeNode x = find(val);
        if (x != null) {
            //如果该节点的左节点为空，那么直接用右节点替换
            if (x.left == null) {
                trans(x, x.right);
            } else if (x.right == null) {
                //同理，如果该节点的右节点为空，那么直接用左节点替换
                trans(x, x.left);
            } else {
                //如果双子节点都存在，寻找该节点的后继节点
                TreeNode successor = successor(x);
                if (successor != null) {
                    //如果后继节点不是x的子节点，那么需要将后继节点的右节点安排好
                    if (x.right != successor) {
                        //后继节点的右节点取代后继节点
                        trans(successor, successor.right);
                        //并更新后继节点的右节点
                        successor.right = x.right;
                        //设置parent
                        x.right.parent = successor;
                    }
                    //再让后继节点取代x节点
                    trans(x, successor);
                    successor.left = x.left;
                    x.left.parent = successor;
                }
            }
        }
    }

    public TreeNode find(int val) {
        TreeNode p = root;
        while (p != null) {
            if (val > p.val) {
                p = p.right;
            } else if (val < p.val) {
                p = p.left;
            } else {
                return p;
            }
        }
        return p;
    }

    class TreeNode {
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
