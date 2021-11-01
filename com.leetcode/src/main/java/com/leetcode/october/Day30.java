package com.leetcode.october;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-30 11:03:10
 * @author: wanglong16@meicai.cn
 */
public class Day30 {

    public ListNode deleteListNode(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            if (temp.next == null) {
                break;
            } else {
                if (temp.next.next != null) {
                    temp.next = temp.next.next;
                } else {
                    temp.next = null;
                }
            }
            temp = temp.next;
        }
        return head;
    }


    public int[] analysisHistogram(int[] heights, int cnt) {
        Arrays.sort(heights);
        int len = heights.length;
        if (len == cnt) {
            return heights;
        }
        int minEnd = len, minDiff = Integer.MAX_VALUE;
        for (int i = len; i >= cnt; i--) {
            int start = i - cnt;
            if (heights[i - 1] - heights[start] <= minDiff) {
                minDiff = heights[i - 1] - heights[start];
                minEnd = i;
            }
        }
        int [] ans = new int[cnt];
        System.arraycopy(heights, minEnd - cnt, ans, 0, cnt);
        return ans;
    }

    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer in : nums) {
            if (set.contains(in)) {
                set.remove(in);
            } else {
                set.add(in);
            }
        }
        List<Integer> list = new ArrayList<>(set);
        return new int[] {list.get(0), list.get(1)};
    }

    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int cur = 0;
        String ss = "";
        for (String st: arr) {
            if (map.get(st) == 1) {
                cur ++;
            }
            if (cur == k) {
                ss = st;
                break;
            }
        }
        return ss;
    }

    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (e1, e2) -> e2[2] - e1[2]);
        for (int i = 0; i < events.length; i++) {
            for (int j = i + 1; j < events.length; j++) {
                if (events[i][1] < events[j][0] || events[i][0] > events[j][1]) {
                    return events[i][2] + events[j][2];
                }
            }
        }
        return 0;
    }

    public int distributeCandies(int[] candyType) {
        return Math.min((int)Arrays.stream(candyType).distinct().count(), candyType.length / 2);
    }


    public static void main(String[] args) {
        Integer a = new Integer(2222);
        Integer b = new Integer(2222);
        System.out.println(a.equals(b));
    }
}
