package BinarySearchTree;


import java.util.Scanner;

class BuildTree {

    public static Node TreeInsert(Node root, Node newNode) {
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (newNode.val < x.val) {
                x = x.left;
            } else x = x.right;
        }
        if (y == null) {
            root = newNode;
        } else if (newNode.val < y.val) {
            y.left = newNode;
        } else y.right = newNode;
        return root;
    }

    public static void InOrderTreeWalk(Node root) {
        if (root != null) {
            InOrderTreeWalk(root.left);
            System.out.print(root.val + " ");
            InOrderTreeWalk(root.right);
        }
    }

    public static void PreOrderTreeWalk(Node root) {
        if (root != null) {
            System.out.print(root.val + " ");
            PreOrderTreeWalk(root.left);
            PreOrderTreeWalk(root.right);
        }
    }

    public static void PostOrderTreeWalk(Node root) {
        if (root != null) {
            PostOrderTreeWalk(root.left);
            PostOrderTreeWalk(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static Node addNode(Node root, int x) {
        if (root == null) return new Node(x);
        if (x < root.val) {
            root.left = addNode(root.left, x);
        } else {
            root.right = addNode(root.right, x);
        }
        return root;
    }

    /* https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/ */
    static void print2DUtil(Node root, int space, int COUNT) {
        // Base case
        if (root == null)
            return;
        // Increase distance between levels
        space += COUNT;
        // Process right child first
        print2DUtil(root.right, space, COUNT);
        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.val + "\n");
        // Process left child
        print2DUtil(root.left, space, COUNT);
    }

    public static Node removeNode(Node root, int x) {
        if (root == null) {
            return null;
        } else if (x > root.val) {
            root.right = removeNode(root.right, x);
            return root;
        } else if (x < root.val) {
            root.left = removeNode(root.left, x);
            return root;
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                // find largest node in left subtree
                Node current = root.left;
                while (current.right != null) {
                    current = current.right;
                }
                // remove largest node in the left subtree
                root.left = removeNode(root.left, current.val);
                // set the largest node in the left subtree (now freestanding to be the new root)
                current.left = root.left;
                current.right = root.right;
                return current;
            }
        }
    }

    public static void main(String args[]) {
        int[] array = {14, 23, 5, 67, 1, 54, 57, 43, 121, 8, 12};
        Node root = null;
        for (int i = 0; i < array.length; i++) {
            Node newNode = new Node(array[i]);
            root = TreeInsert(root, newNode);
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
        print2DUtil(addNode(root, 13), 0, array.length + 1);

        System.out.println("Tree visualization after remove new value " + 67);
        print2DUtil(removeNode(root, 67), 0, array.length);

    }

}