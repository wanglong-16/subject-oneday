package com.leetcode.august;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-29 10:14:43
 * @author: wanglong16@meicai.cn
 */
public class Day24 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node pt = p, qt = q;
        while (pt != qt) {
            pt = pt.parent == null ? q : pt.parent;
            qt = qt.parent == null ? p : qt.parent;
        }
        return pt;
    }


    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            min = Math.min((nums[i + k - 1] - nums[i]), min);
        }
        return min;
    }

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        return nums[nums.length - k];
    }


    class Solution {
        int[] data;
        int[] preSum;
        int size;
        public Solution(int[] w) {
            data = w;
            preSum = new int[w.length + 1];
            for (int i = 1; i < w.length + 1; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }
            size = w.length;
        }

        public int pickIndex() {
            int rand = new Random().nextInt(size);
            return data[rand];
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        System.out.println(day24.minimumDifference(new int[] {1, 4, 7, 8}, 2));
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    public boolean match(String source, String target) {
        Map<Character, Character> map = new HashMap<>();
        if (source.length() != target.length()) {
            return false;
        }
        for (int i = 0; i < target.length(); i++) {
            Character ch = map.getOrDefault(source.charAt(i), target.charAt(i));
            if (ch != target.charAt(i)) {
                return false;
            }
            map.put(source.charAt(i), target.charAt(i));
        }
        return true;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n + 1], ans = new int[n];
        for (int[] b : bookings) {
            arr[b[0] - 1] += b[2];
            arr[b[1]] -= b[2];
        }
        ans[0] = arr[0];
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        return arr;
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (Integer in : stones) {
            queue.offer(in);
        }
        while (queue.size() > 1) {
            Integer first = queue.poll();
            Integer second = queue.poll();
            queue.offer(first - second);
        }
        return queue.poll();
    }

    public List<List<Integer>> largeGroupPositionsV1(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        if (s.length() < 3) {
            return ans;
        }
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(pre)) {
                if (i - 1 - pre >= 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(pre);
                    list.add(i - 1);
                    ans.add(list);
                }
                pre = i;
            }
        }
        return ans;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = s.length();
        int num = 1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (num >= 3) {
                    ret.add(Arrays.asList(i - num + 1, i));
                }
                num = 1;
            } else {
                num++;
            }
        }
        return ret;
    }

    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode temp;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public int maxsumofSubarray (int[] arr) {
        // write code here
        int max = 0, sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }


}
