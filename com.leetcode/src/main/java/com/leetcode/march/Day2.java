package com.leetcode.march;

import com.leetcode.util.tree.BTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-03 09:22:59
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    /**
     * 946. 验证栈序列
     * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     * 提示：
     * 0 <= pushed.length == popped.length <= 1000
     * 0 <= pushed[i], popped[i] < 1000
     * pushed 是 popped 的排列。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int pu = 0, po = 0;
        Stack<Integer> stack = new Stack<>();
        while (pu < pushed.length || !stack.empty()) {
            if (!stack.empty() && stack.peek() == popped[po]) {
                //if possible pop it
                stack.pop();
                po ++;
                continue;
            }
            if (pu == pushed.length && stack.peek() != popped[po]) {
                return false;
            }
            stack.push(pushed[pu]);
            pu ++;
        }
        return stack.empty();
    }

    /**
     * 1544. 整理字符串
     * 给你一个由大小写英文字母组成的字符串 s 。
     * 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:
     * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。
     * 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。
     * 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
     * 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。
     * 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。
     * 示例 1：
     * 输入：s = "leEeetcode"
     * 输出："leetcode"
     * 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
     * 示例 2：
     * 输入：s = "abBAcC"
     * 输出：""
     * 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如：
     * "abBAcC" --> "aAcC" --> "cC" --> ""
     * "abBAcC" --> "abBA" --> "aA" --> ""
     * 示例 3：
     * 输入：s = "s"
     * 输出："s"
     */
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        int l = 0;
        while (l < s.length()) {
            if (!stack.empty() && (stack.peek() == s.charAt(l) - 32 || stack.peek() == s.charAt(l) + 32)) {
                stack.pop();
            } else {
                stack.push(s.charAt(l));
            }
            l ++;
        }
        if (stack.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }
    }

    /**
     * 1190. 反转每对括号间的子串
     * 给出一个字符串 s（仅含有小写英文字母和括号）。
     * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
     * 注意，您的结果中 不应 包含任何括号。
     * 示例 1：
     * 输入：s = "(abcd)"
     * 输出："dcba"
     * 示例 2：
     *
     * 输入：s = "(u(love)i)"
     * （uevoli）
     * 输出："iloveu"
     * 示例 3：
     * 输入：s = "(ed(et(oc))el)"
     * 输出："leetcode"
     * 示例 4：
     *
     * 输入：s = "a(bcdefghijkl(mno)p)q"
     * 输出："apmnolkjihgfedcbq"
     */
    public String reverseParenthesesV1(String s) {
        int left, right;
        while (s.lastIndexOf("(") != -1) {
            left = s.lastIndexOf("(");
            right = s.indexOf(")", left);
            StringBuilder sb = new StringBuilder(s.substring(left + 1, right)).reverse();
            s = s.substring(0, left) + sb + s.substring(right + 1);
        }
        return s;
    }

    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            }
            if (arr[i] == ')') {
                reverse(arr, stack.pop() + 1, i - 1);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ')' && arr[i] != '(') {
                sb.append(arr[i]);
            }
        }
        return sb.toString().replace("{|}", "");
    }

    public void reverse(char[] arr, int left, int right) {
        while (right > left) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }

    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public List<Integer> preorderTraversal(BTreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(BTreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public void inorder(BTreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    

}
