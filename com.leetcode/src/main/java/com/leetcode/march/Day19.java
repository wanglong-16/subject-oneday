package com.leetcode.march;

import com.leetcode.util.tree.Node;
import com.leetcode.util.tree.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-20 17:05:23
 * @author: wanglong16@meicai.cn
 */
public class Day19 {

    /**
     * 150. 逆波兰表达式求值
     * 根据 逆波兰表示法，求表达式的值。
     *
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     *
     *
     * 说明：
     *
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     *
     * 示例 1：
     *
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     *
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     *
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：
     * 该算式转化为常见的中缀算术表达式为：
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * 提示：
     *
     * 1 <= tokens.length <= 104
     * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
     */
    public int evalRPN(String[] tokens) {
        List<String> opts = Arrays.asList("+", "-", "*", "/");
        Stack<Integer> integers = new Stack<>();
        for (String str : tokens) {
            if (!opts.contains(str)) {
                integers.push(Integer.valueOf(str));
            } else {
                if ("+".equals(str)) {
                    integers.push(integers.pop() + integers.pop());
                } else if ("-".equals(str)) {
                    integers.push(integers.pop() - integers.pop());
                } else if ("*".equals(str)) {
                    integers.push(integers.pop() * integers.pop());
                } else {
                    integers.push(integers.pop() / integers.pop());
                }
            }
        }
        return integers.pop();
    }

    /**
     * 1490. 克隆 N 叉树
     * 给定一棵 N 叉树的根节点 root ，返回该树的深拷贝（克隆）。
     *
     * N 叉树的每个节点都包含一个值（ int ）和子节点的列表（ List[Node] ）。
     *
     * class Node {
     *     public int val;
     *     public List<Node> children;
     * }
     * N 叉树的输入序列用层序遍历表示，每组子节点用 null 分隔（见示例）。
     *
     * 进阶：你的答案可以适用于克隆图问题吗？
     * 示例 1：
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[1,null,3,2,4,null,5,6]
     * 示例 2：
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     *
     *
     * 提示：
     *
     * 给定的 N 叉树的深度小于或等于 1000。
     * 节点的总个数在 [0, 10^4] 之间
     */
    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = new Node(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            temp.children.add(cloneTree(root.children.get(i)));
        }
        return temp;
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     *
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     *
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，
     * 其他语言返回节点本身）。
     * 注意：
     *
     * 你 不能 对两棵二叉树，以及 target 节点进行更改。
     * 只能 返回对克隆树 cloned 中已有的节点的引用。
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null || original == null || target == null) {
            return null;
        }
        if (original.val == target.val) {
            return cloned;
        }
        TreeNode temp =  getTargetCopy(original.left, cloned.left, target);
        return temp == null ? getTargetCopy(original.right, cloned.right, target) : temp;
    }

    /**
     * 654. 最大二叉树
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     *
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树 。
     * 示例 1：
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     *         - 空数组，无子节点。
     *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     *             - 空数组，无子节点。
     *             - 只有一个元素，所以子节点是一个值为 1 的节点。
     *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     *         - 只有一个元素，所以子节点是一个值为 0 的节点。
     *         - 空数组，无子节点。
     * 示例 2：
     * 输入：nums = [3,2,1]
     * 输出：[3,null,2,null,1]
     * 提示：
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * nums 中的所有整数 互不相同
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int l, int r) {
        if (l == r) {
            return null;
        }
        int maxIndex = findMaxIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, l, maxIndex);
        root.right = construct(nums, maxIndex + 1, r);
        return root;
    }

    public int findMaxIndex(int[] nums, int l, int r) {
        int maxIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }



}
