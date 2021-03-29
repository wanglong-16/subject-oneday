package com.leetcode.march;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-28 11:31:40
 * @author: wanglong16@meicai.cn
 */
public class Day28 {
    /**
     * 709. 转换成小写字母
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     *
     *
     *
     * 示例 1：
     *
     * 输入: "Hello"
     * 输出: "hello"
     * 示例 2：
     *
     * 输入: "here"
     * 输出: "here"
     * 示例 3：
     *
     * 输入: "LOVELY"
     * 输出: "lovely"
     */
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                chars[i] += 32;
            }
        }
        return new String(chars);
    }

    /**
     * 1002. 查找常用字符
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，
     * 如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答案。
     * 示例 1：
     *
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     *
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     */
//    public List<String> commonChars(String[] A) {
//
//    }

    /**
     * 1304. 和为零的N个唯一整数
     * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 5
     * 输出：[-7,-1,1,3,4]
     * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
     * 示例 2：
     *
     * 输入：n = 3
     * 输出：[-1,0,1]
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：[0]
     */
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        if (n % 2 == 1) {
            ans[0] = 0;
            for (int i = 1; i <= n / 2; i++) {
                ans[i] = i;
                ans[i + n / 2] = -i;
            }
        } else {
            for (int i = 0; i < n / 2; i++) {
                ans[i] = i;
                ans[i + n / 2] = -i;
            }
        }
        return ans;
    }

    public String generateTheString(int n) {
        if (n % 2 == 0) {
            char[] a = new char[n - 1];
            Arrays.fill(a, 'a');
            return new String(a) + "b";
        } else {
            char[] a = new char[n];
            Arrays.fill(a, 'a');
            return new String(a);
        }
    }

    /**
     * 1460. 通过翻转子数组使两个数组相等
     * 给你两个长度相同的整数数组 target 和 arr 。
     *
     * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     *
     * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：target = [1,2,3,4], arr = [2,4,1,3]
     * 输出：true
     * 解释：你可以按照如下步骤使 arr 变成 target：
     * 1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
     * 2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
     * 3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
     * 上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
     * 示例 2：
     *
     * 输入：target = [7], arr = [7]
     * 输出：true
     * 解释：arr 不需要做任何翻转已经与 target 相等。
     * 示例 3：
     *
     * 输入：target = [1,12], arr = [12,1]
     * 输出：true
     * 示例 4：
     *
     * 输入：target = [3,7,9], arr = [3,7,11]
     * 输出：false
     * 解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
     * 示例 5：
     *
     * 输入：target = [1,1,1,1,1], arr = [1,1,1,1,1]
     * 输出：true
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        if (arr.length != target.length) {
            return false;
        }
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1207. 独一无二的出现次数
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     *
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     *
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for (Integer in : arr) {
            map.merge(in, 1, Integer::sum);
        }
        Set<Integer> integerSet = new HashSet<>();
        int curVal;
        for (Map.Entry e : map.entrySet()) {
            curVal = Integer.parseInt(e.getValue().toString());
            if (integerSet.contains(curVal)) {
                return false;
            }
            integerSet.add(curVal);
        }
        return true;
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     *
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 已按 非递减顺序 排序
     *
     *
     * 进阶：
     *
     * 请你设计时间复杂度为 O(n) 的算法解决本问题
     */
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0, right = nums.length - 1, ptr = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                ans[ptr] = nums[left] * nums[left];
                left ++;
            } else {
                ans[ptr] = nums[right] * nums[right];
                right --;
            }
            ptr --;
        }
        return ans;
    }

    /**
     * 1309. 解码字母到整数映射
     * 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
     *
     * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
     * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 106
     * 返回映射之后形成的新字符串。
     *
     * 题目数据保证映射始终唯一。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "10#11#12"
     * 输出："jkab"
     * 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
     * 示例 2：
     *
     * 输入：s = "1326#"
     * 输出："acz"
     * 示例 3：
     *
     * 输入：s = "25#"
     * 输出："y"
     * 示例 4：
     *
     * 输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
     * 输出："abcdefghijklmnopqrstuvwxyz"
     */
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int ptr = s.length() - 1;
        int key;
        while (ptr >= 0) {
            if (s.charAt(ptr) == '#') {
                key = Integer.parseInt(s.substring(ptr - 2, ptr));
                ptr -= 3;
            } else {
                key = Integer.parseInt(s.substring(ptr, ptr + 1));
                ptr --;
            }
            sb.append((char) (key + 96));
        }
        return sb.reverse().toString();
    }

    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     * 示例 2：
     *
     *
     * 输入：head = [0,1,2,0,1,2], k = 4
     * temp =
     * 输出：[2,0,1]
     *
     *
     * 提示：
     *
     * 链表中节点的数目在范围 [0, 500] 内
     * -100 <= Node.val <= 100
     * 0 <= k <= 2 * 109
     */
    public ListNode rotateRight(ListNode head, int k) {

        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len ++;
        }
        temp = head;
        int ptr = 0;
        while (ptr < k) {
            if (temp.next == null) {
                temp = head;
            } else {
                temp = temp.next;
            }
            ptr ++;
        }
        ListNode tempT = temp;
        for (int i = 0; i < len; i++) {
            if (tempT.next == null) {
                tempT = head;
            } else {
                tempT = tempT.next;
            }
        }
        tempT.next = null;
        return temp;
    }
}
