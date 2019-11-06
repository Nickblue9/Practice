package leetCode;


import com.sun.source.tree.Tree;

public class merge2BinaryTrees {
    public static void main(String [] args){

        Object[] array = {1,3,2,5};
        Object[] array2 = {2,1,3,4,7};
        TreeNode root = null;
        TreeNode root2 = null;
        for (int i = 0; i < array.length; i++) {
            TreeNode newNode = new TreeNode((Integer) array[i]);
            root = BuildTree.TreeInsert(root, newNode);
        }
        for (int i = 0; i < array2.length; i++) {
            TreeNode newNode = new TreeNode((Integer) array2[i]);
            root2 = BuildTree.TreeInsert(root2, newNode);
        }

        BuildTree.print2DUtil(root,0,array.length);
        System.out.println("----------------------------------------------");
        BuildTree.print2DUtil(root2,0,array2.length);
        System.out.println("----------------------------------------------");

        Solution s = new Solution();
        TreeNode solution = s.mergeTrees(root,root2);
        BuildTree.print2DUtil(solution,0,array2.length);
    }
}

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null)return t2;
        else if(t2==null)return t1;
        else{
            t1.val = t1.val+t2.val;
            t1.right = mergeTrees(t1.right,t2.right);
            t1.left = mergeTrees(t1.left,t2.left);
        }
        return t1;
    }
}
