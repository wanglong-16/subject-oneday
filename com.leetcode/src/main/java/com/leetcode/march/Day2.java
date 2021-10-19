package com.leetcode.march;

import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.BTreeNode;

import java.util.*;

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

    /**
     * 354. 俄罗斯套娃信封问题
     * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 说明:
     * 不允许旋转信封。
     * 示例:
     * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出: 3
     * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     */
    public int maxEnvelopesV1(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes.length == 1) {
            return envelopes.length;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        int ans = 1;
        for (int i = 0; i < envelopes.length; i++) {
            int x = envelopes[i][0], y = envelopes[i][1];
            int temp = 1;
            for (int j = i; j < envelopes.length; j++) {
                if (envelopes[j][0] > x && envelopes[j][1] > y) {
                    temp++;
                    x = envelopes[j][0];
                    y = envelopes[j][1];
                }
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 面试题 17.10. 主要元素
     * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     *
     * 示例 1：
     *
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * 示例 2：
     *
     * 输入：[3,2]
     * 输出：-1
     * 示例 3：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        for (int i = 0; i < nums.length / 2; i++) {
            if (nums[i] == nums[i + nums.length / 2]) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 众所周知，主要元素最多只有 1 个。
     * 摩尔投票法的核心就是比拼消耗。主要分为比拼消耗 pairing 阶段和统计 counting 阶段。
     * 它基于这样一个事实：任意删除数组中两个不同的数，直至数组中不存在相同的数，那么剩下的数就是主要元素。
     * 形象化理解：诸侯争霸游戏，假设你的人口占比超过总人口的一半，那么只要不内斗，每次和其他国家打仗都能一换一，最终赢家肯定是你。即使其他国家联合起来也打不过你，更不用说其他国家之间也会相互攻击。
     * 算法过程：
     * 遍历数组，从第一个元素开始计数，计数器初始为 1。
     * 遇到与当前元素相同的元素时，计数器加 1，否则减 1（比拼消耗）。
     * 若计数器为 0，则重新从当前元素开始计数，重复步骤 2 直至到达数组末尾。
     */

    public int majorityElementMor(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int current = nums[0];
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) {
                current = nums[i];
            }
            if (nums[i] == current) {
                ++ cnt;
            } else {
                -- cnt;
            }
        }
        int count = 0;
        for (int num : nums) {
            if (num == current) {
                count ++;
            }
        }
        return count * 2 > nums.length ? current : -1;
    }


    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public ListNode mergeTwoListsV1(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

}
