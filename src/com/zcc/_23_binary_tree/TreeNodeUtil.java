package com.zcc._23_binary_tree;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Zcc
 * created on 22/11/7 15:35
 */
public class TreeNodeUtil {

    //后序遍历
    public static <T> void show(TreeNode<T> tree) {
        if (tree == null) {
            return;
        }
        show(tree.left);
        show(tree.right);
        System.out.print(tree.data + "\t");

    }

    public static void main(String[] args) {
        TreeNode<Integer> rootNode = buildBinaryTree(new Integer[]{3, 6, 7, 5, 2, 9, 8}, new Integer[]{2, 5, 6, 3, 7, 8, 9}, 1);
        show(rootNode);
    }

    /**
     * @param traversal        中序遍历的集合
     * @param anotherTraversal 先序/后序遍历的集合
     * @param traversalType    遍历方式: 1- 先序遍历 2-后序遍历  default: 1-先序遍历
     * @param <T>              DateType
     * @return root node
     */
    public static <T extends Comparable<T>> TreeNode<T> buildBinaryTree(Comparable<T>[] traversal, Comparable<T>[] anotherTraversal, int traversalType) {
        checkBuildParams(traversal, anotherTraversal);
        if (traversalType < 1 || traversalType > 2) {
            traversalType = 1;
        }
        if (traversalType == 1) {
//            return buildBinaryTree(traversal, anotherTraversal);
            return buildBinaryTreeWithOutArrayCopy(traversal, anotherTraversal, 0, traversal.length - 1, 0);
        }
        return buildBinaryTreeAnother(traversal, anotherTraversal);
    }

    /**
     * 中序遍历和先序遍历生成二叉树
     * 数组copy得方式不太好  使用传下标优化一下
     *
     * @param traversal        中序遍历集合
     * @param anotherTraversal 先序遍历集合
     */
    private static <T extends Comparable<T>> TreeNode<T> buildBinaryTree(Comparable<T>[] traversal, Comparable<T>[] anotherTraversal) {
        //return conditional
        if (traversal == null) return null;
        TreeNode<T> rootNode = new TreeNode<T>((T) anotherTraversal[0]);
        int rootIndexInTraversal = 0;
        for (int i = 0; i < traversal.length; i++) {
            if (rootNode.data.equals(traversal[i])) {
                rootIndexInTraversal = i;
                break;
            }
        }
        //rebuild LeftTree
        int leftLength = rootIndexInTraversal;
        Comparable<T>[] leftChildTraversal = null;
        Comparable<T>[] leftChildAnotherTraversal = null;
        if (leftLength != 0) {
            leftChildTraversal = new Comparable[rootIndexInTraversal];
            leftChildAnotherTraversal = new Comparable[rootIndexInTraversal];
            System.arraycopy(traversal, 0, leftChildTraversal, 0, leftChildTraversal.length);
            System.arraycopy(anotherTraversal, 1, leftChildAnotherTraversal, 0, leftChildTraversal.length);
        }
        //rebuild Right
        int rightLength = traversal.length - rootIndexInTraversal - 1;
        Comparable<T>[] rightChildTraversal = null;
        Comparable<T>[] rightChildAnotherTraversal = null;
        if (rightLength != 0) {
            rightChildTraversal = new Comparable[rightLength];
            rightChildAnotherTraversal = new Comparable[traversal.length - rootIndexInTraversal - 1];
            System.arraycopy(traversal, rootIndexInTraversal + 1, rightChildTraversal, 0, rightChildTraversal.length);
            System.arraycopy(anotherTraversal, rootIndexInTraversal + 1, rightChildAnotherTraversal, 0, rightChildTraversal.length);
        }
        TreeNode<T> lTreeNode = buildBinaryTree(leftChildTraversal, leftChildAnotherTraversal);
        TreeNode<T> rTreeNode = buildBinaryTree(rightChildTraversal, rightChildAnotherTraversal);
        rootNode.left = lTreeNode;
        rootNode.right = rTreeNode;
        return rootNode;
    }

    /**
     * 每次递归的作用：找到root节点并返回、找到左右子树的集合，递归传下去
     * @param traversal        中序遍历集合
     * @param anotherTraversal 先序遍历集合
     * @param tFromIndex       中序遍历的fromIndex
     * @param tEndIndex        中序遍历的endIndex
     * @param aFromIndex       先序遍历的fromIndex
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> TreeNode<T> buildBinaryTreeWithOutArrayCopy(Comparable<T>[] traversal, Comparable<T>[] anotherTraversal, int tFromIndex, int tEndIndex, int aFromIndex) {
        //return conditional
        if (tFromIndex < 0) return null;
        TreeNode<T> rootNode = new TreeNode<T>((T) anotherTraversal[aFromIndex]);
        int rootIndexInTraversal = 0;
        for (int i = tFromIndex; i <= tEndIndex; i++) {
            if (rootNode.data.equals(traversal[i])) {
                rootIndexInTraversal = i;
                break;
            }
        }
        //rebuild LeftTree
        int leftLength = rootIndexInTraversal - tFromIndex;
        int leftChildTFromIndex = -1, leftChildTEndIndex = -1, leftChildAFromIndex = -1, leftChildAEndIndex = -1;
        if (leftLength != 0) {
            leftChildTFromIndex = tFromIndex;
            leftChildTEndIndex = rootIndexInTraversal - 1;
            leftChildAFromIndex = aFromIndex + 1;
        }
        //rebuild Right
        int rightLength = tEndIndex - rootIndexInTraversal;
        int rightChildTFromIndex = -1, rightChildTEndIndex = -1, rightChildAFromIndex = -1, rightChildAEndIndex = -1;
        if (rightLength != 0) {
            rightChildTFromIndex = rootIndexInTraversal + 1;
            rightChildTEndIndex = rightChildTFromIndex + rightLength - 1;
            rightChildAFromIndex = aFromIndex + 1 + leftLength;
        }
        rootNode.left = buildBinaryTreeWithOutArrayCopy(traversal, anotherTraversal, leftChildTFromIndex, leftChildTEndIndex, leftChildAFromIndex);
        rootNode.right = buildBinaryTreeWithOutArrayCopy(traversal, anotherTraversal, rightChildTFromIndex, rightChildTEndIndex, rightChildAFromIndex);
        return rootNode;
    }

    /**
     * 中序遍历和后序遍历生成二叉树
     */
    private static <T extends Comparable<T>> TreeNode<T> buildBinaryTreeAnother(Comparable<T>[] traversal, Comparable<T>[] anotherTraversal) {

        return null;
    }

    private static <T> void checkBuildParams(Comparable<T>[] traversal, Comparable<T>[] anotherTraversal) {
        if (traversal == null || anotherTraversal == null || anotherTraversal.length == 0 || traversal.length != anotherTraversal.length) {
            throw new IllegalArgumentException("dataSet Error: traversal Set is empty or traversal size is not the same");
        }
    }


}
