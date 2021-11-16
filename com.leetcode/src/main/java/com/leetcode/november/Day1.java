package com.leetcode.november;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-01 16:39:46
 * @author: wanglong16@meicai.cn
 */
public class Day1 {

    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i % 10) {
                return i;
            }
        }
        return -1;
    }

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int index = 0;
        ListNode prev = null;
        List<Integer> avaPoints = new ArrayList<>();
        while (head != null) {
            if (prev != null) {
                if (head.next != null) {
                    if ((prev.val > head.val && head.next.val > head.val)
                    || (prev.val < head.val && head.next.val < head.val)) {
                        avaPoints.add(index);
                    }
                }
            }
            prev = head;
            head = head.next;
            ++ index;
        }
        if (avaPoints.size() < 2) {
            return new int[] {-1,-1};
        } else {
            int[] ans = new int[] {Integer.MAX_VALUE, avaPoints.get(avaPoints.size() - 1) - avaPoints.get(0)};
            for (int i = 1; i < avaPoints.size(); i++) {
                ans[0] = Math.min(ans[0], avaPoints.get(i) - avaPoints.get(i - 1));
            }
            return ans;
        }
    }

    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> queue = new ArrayDeque<>();
        int ans = 1;
        queue.offer(start);
        Set<Integer> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (Integer n : nums) {
                    int add = cur + n;
                    int min = cur - n;
                    int xor = cur ^ n;
                    if (add == goal || min == goal || xor == goal) {
                        return ans;
                    }
                    if (add >= 0 && add <= 1000 && set.add(add)) {
                        queue.offer(add);
                    }
                    if (min >= 0 && min <= 1000 && set.add(min)) {
                        queue.offer(min);
                    }
                    if (xor >= 0 && xor <= 1000 && set.add(xor)) {
                        queue.offer(xor);
                    }
                }
            }
            ans ++;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
