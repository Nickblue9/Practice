package leetCode;


import java.util.Scanner;

class BuildTree {

    public static TreeNode TreeInsert(TreeNode root, TreeNode newTreeNode) {
        TreeNode y = null;
        TreeNode x = root;
        while (x != null) {
            y = x;
            if (newTreeNode.val < x.val) {
                x = x.left;
            } else x = x.right;
        }
        if (y == null) {
            root = newTreeNode;
        } else if (newTreeNode.val < y.val) {
            y.left = newTreeNode;
        } else y.right = newTreeNode;
        return root;
    }

    public static void InOrderTreeWalk(TreeNode root) {
        if (root != null) {
            InOrderTreeWalk(root.left);
            System.out.print(root.val + " ");
            InOrderTreeWalk(root.right);
        }
    }

    public static void PreOrderTreeWalk(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            PreOrderTreeWalk(root.left);
            PreOrderTreeWalk(root.right);
        }
    }

    public static void PostOrderTreeWalk(TreeNode root) {
        if (root != null) {
            PostOrderTreeWalk(root.left);
            PostOrderTreeWalk(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static TreeNode addTreeNode(TreeNode root, int x) {
        if (root == null) return new TreeNode(x);
        if (x < root.val) {
            root.left = addTreeNode(root.left, x);
        } else {
            root.right = addTreeNode(root.right, x);
        }
        return root;
    }

    /* https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/ */
    static void print2DUtil(TreeNode root, int space, int COUNT) {
        // Base case
        if (root == null)
            return;
        // Increase distance between levels
        space += COUNT;
        // Process right child first
        print2DUtil(root.right, space, COUNT);
        // Print current TreeNode after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.val + "\n");
        // Process left child
        print2DUtil(root.left, space, COUNT);
    }

    public static TreeNode removeTreeNode(TreeNode root, int x) {
        if (root == null) {
            return null;
        } else if (x > root.val) {
            root.right = removeTreeNode(root.right, x);
            return root;
        } else if (x < root.val) {
            root.left = removeTreeNode(root.left, x);
            return root;
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                // find largest TreeNode in left subtree
                TreeNode current = root.left;
                while (current.right != null) {
                    current = current.right;
                }
                // remove largest TreeNode in the left subtree
                root.left = removeTreeNode(root.left, current.val);
                // set the largest TreeNode in the left subtree (now freestanding to be the new root)
                current.left = root.left;
                current.right = root.right;
                return current;
            }
        }
    }

    public static void main(String args[]) {
        int[] array = {14, 23, 5, 67, 1, 54, 57, 43, 121, 8, 12};
        TreeNode root = null;
        for (int i = 0; i < array.length; i++) {
            TreeNode newTreeNode = new TreeNode(array[i]);
            root = TreeInsert(root, newTreeNode);
        }

        System.out.println("In-order-walk: ");
        InOrderTreeWalk(root);
        System.out.println("");

        System.out.println("Pre-order-walk: ");
        PreOrderTreeWalk(root);
        System.out.println("");

        System.out.println("Post-order-walk: ");
        PostOrderTreeWalk(root);
        System.out.println("");

        System.out.println("Tree visualization: \n");
        print2DUtil(root, 0, array.length);

        System.out.println("Tree visualization after insert new value " + 13);
        print2DUtil(addTreeNode(root, 13), 0, array.length + 1);

        System.out.println("Tree visualization after remove new value " + 67);
        print2DUtil(removeTreeNode(root, 67), 0, array.length);

    }

}