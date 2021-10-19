package com.leetcode.october;

import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-07 21:54:24
 * @author: wanglong16@meicai.cn
 */
public class Day6 {


    public int countSegments(String s) {
        String[] strings = s.split(" ");
        return strings.length;
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.countSegments(", , , ,        a, eaefa"));
    }


    int maxSum = 0;
    public int maxPathSum(TreeNode root) {
        maxG(root);
        return maxSum;
    }

    public int maxG(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(maxG(node.left), 0);
        int right = Math.max(maxG(node.right), 0);
        maxSum = Math.max(maxSum, left + right + node.val);
        return Math.max(maxG(node.left) + node.val, maxG(node.right) + node.val);
    }

    List<TreeNode> inOrderList = new ArrayList<>();

    public TreeNode inorderSuccessorV1(TreeNode root, TreeNode p) {
        for (int i = 0; i < inOrderList.size(); i++) {
            if (p == inOrderList.get(i) && i < inOrderList.size() - 1) {
                return inOrderList.get(i + 1);
            }
        }
        return null;
    }

    public void inOrderV1(TreeNode node) {
        if (node != null) {
            inOrderV1(node.left);
            inOrderList.add(node);
            inOrderV1(node.right);
        }
    }

    TreeNode next = null;
    boolean find = false;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inOrder(root, p);
        return next;
    }

    public void inOrder(TreeNode node, TreeNode p) {
        if (node != null) {
            inOrder(node.left, p);
            if (node == p) {
                find = true;
            }
            if (find) {
                next = node;
                find = false;
            }
            inOrder(node.right, p);
        }
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        for (;;) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    Map<Integer, TreeNode> parents = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        Set<Integer> visRoots = new HashSet<>();
        while (p != null) {
            visRoots.add(p.val);
            if (parents.get(p.val) != null) {
                p = parents.get(p.val);
            }
        }
        while (q != null) {
            if (visRoots.contains(q.val)) {
                return q;
            }
            q = parents.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            dfs(node.right);
        }
    }

    public int[] reversePrint(ListNode head) {
        Stack<Integer> nodes = new Stack<>();
        while (head != null) {
            nodes.push(head.val);
            head = head.next;
        }
        int[] ans = new int[nodes.size()];
        int ptr = 0;
        while (!nodes.isEmpty()) {
            ans[ptr] = nodes.pop();
            ++ ptr;
        }
        return ans;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

}
