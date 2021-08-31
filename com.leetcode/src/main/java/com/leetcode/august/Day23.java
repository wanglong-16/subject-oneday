package com.leetcode.august;

import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-28 16:48:28
 * @author: wanglong16@meicai.cn
 */
public class Day23 {

    public boolean verifyPostorder(int[] postorder) {
        return match(postorder, 0, postorder.length - 1);
    }

    public boolean match(int[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return true;
        }
        int l = startPos, p = 0;
        while (array[l] < array[endPos]) {
            l ++;//找到第一个大于的位置
            p = l;
        }
        while (l < endPos && array[l] > array[endPos]) {
            l ++;
        }
        return l == endPos && match(array, startPos, p - 1) && match(array, p, endPos - 1);
    }

    List<Integer> list = new ArrayList<>();
    public int minDiffInBST(TreeNode root) {
        zhongxu(root);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                min = Math.min(min, Math.abs(list.get(i) - list.get(j)));
            }
        }
        return min;
    }

    public void zhongxu(TreeNode treeNode) {
        if (treeNode != null) {
            zhongxu(treeNode.left);
            list.add(treeNode.val);
            zhongxu(treeNode.right);
        }
    }

    TreeNode root = new TreeNode();
    List<Integer> arrayList = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        if (!arrayList.isEmpty()) {
            return convertBst(0, arrayList.size() - 1);
        }
        return root;
    }

    public TreeNode convertBst(int left, int right) {
        if (left > right) {
            return null;
        }
        int p = (left + right) / 2;
        TreeNode l = convertBst(left, p - 1);
        TreeNode r = convertBst(p + 1, right);
        return new TreeNode(arrayList.get(p), l, r);
    }

    public void reverseWords(char[] s) {
        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            if (c == ' ') {
                strings.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        strings.add(sb.toString());
        int cursor = 0;
        for (int i = strings.size() - 1; i >= 0; i--) {
            String str = strings.get(i);
            for (Character ch : str.toCharArray()) {
                s[cursor] = ch;
                cursor++;
            }
            if (cursor < s.length) {
                s[cursor] = ' ';
                cursor++;
            }
        }
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int[] sumArr = new int[arr.length];
        int len = arr.length;
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        int ans = 0, step = 3;
        ans += sumArr[len - 1];
        while (step <= len) {
            for (int i = step; i <= len; i++) {
                int temp = (i - step - 1) >= 0 ? sumArr[i - step - 1] : 0;
                ans += sumArr[i - 1] - temp;
            }
            step += 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        Day23 day23 = new Day23();
        System.out.println(day23.sumOddLengthSubarrays(new int[] {1,4,2,5,3}));
    }

}
