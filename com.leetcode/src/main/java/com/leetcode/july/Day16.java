package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-15 19:45:54
 * @author: wanglong16@meicai.cn
 */
public class Day16 {


    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] type3 = new int[n];
        for (int i = 0; i < n; i++) {
            type3[i] = i;
        }
        Arrays.sort(edges);

        int[] type1 = Arrays.copyOf(type3, n);
        int[] type2 = Arrays.copyOf(type3, n);

        return 0;
    }

    public int find(int x, int[] parent) {
        return x == parent[x] ? x : (parent[x] = find(parent[x], parent));
    }

    public boolean merge(int x, int y, int[] parent) {
        int rx = find(x, parent), ry = find(y, parent);
        if (rx == ry) {
            return false;
        } else {
            parent[rx] = ry;
            return true;
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            this.merge(pair.get(0), pair.get(1), parent);
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            int root = find(i, parent);
            PriorityQueue<Character> set = map.getOrDefault(root, new PriorityQueue<>());
            set.offer(s.charAt(i));
            map.put(root, set);
            indices[i] = root;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = indices[i];
            ans.append(map.get(root).poll());
        }
        return ans.toString();
    }




    public int[][] multiSearch(String big, String[] smalls) {
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            ans[i] = indexOFStr(big, smalls[i]);
        }
        return ans;
    }

    public int[] indexOFStr(String big, String str) {
        List<Integer> ans = new ArrayList<>();
        if ("".equals(str)) {
            return new int[0];
        }
        int idx = 0;
        do {
            idx = big.indexOf(str, idx) + 1;
            if (idx > 0) {
                ans.add(idx - 1);
            }
        } while (idx != 0);
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }

    class Solution {
        private int[][] res;
        private String global_big;

        public int[][] multiSearch(String big, String[] smalls) {
            int len = smalls.length;
            res = new int[len][];
            global_big = big;
            for (int i = 0; i < len; ++i) {
                addToRes(i, smalls[i]);
            }
            return res;
        }

        private void addToRes(int position, String str) {
            if ("".equals(str)) {
                res[position] = new int[0];
                return;
            }
            LinkedList<Integer> list = new LinkedList<>();
            int idx = 0;
            while ((idx = global_big.indexOf(str, idx) + 1) != 0) {
                list.addLast(idx - 1);
            }
            res[position] = list.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    public int searchV1(int[] nums, int target) {
        int ans = 0;
        for (int num : nums) {
            if (num == target) {
                ans++;
            }
        }
        return ans;
    }

    public int search(int[] nums, int target) {
        int ans = 0, mid = binarySearch(nums, target);
        if (mid == -1) {
            return 0;
        }
        for (int l = mid, r = mid; l >=0 || r < nums.length; l --, r ++) {
            if (l >=0 && nums[l] == target) {
                ans++;
            }
            if (r < nums.length && nums[r] == target) {
                ans++;
            }
        }
        return ans - 1;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Day16 day16 = new Day16();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(0,3));
        lists.add(Arrays.asList(1,2));
//"mississippi"
//["is","ppi","i"]
        System.out.println(day16.search(new int[] {6}, 6));
    }

}
