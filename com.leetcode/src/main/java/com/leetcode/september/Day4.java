package com.leetcode.september;

import com.leetcode.util.tree.TreeNode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-21 09:58:18
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    public int lengthOfLastWord(String s) {
        String[] arr = s.trim().split(" ");
        return arr[arr.length - 1].length();
    }

    List<List<Integer>> ans = new ArrayList<>();
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) throws IOException {
        Arrays.sort(nums);
        vis = new boolean[nums.length];
        backTracking(nums, 0, new ArrayList<>());
        Socket socket = new Socket();
        ServerSocket s = new ServerSocket();

        return ans;
    }

    public void backTracking(int[] nums, int idx, List<Integer> arr) {
        if (arr.size() == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            arr.add(nums[i]);
            vis[i] = true;
            backTracking(nums, i + 1, arr);
            arr.remove(arr.size() - 1);
            vis[i] = false;
        }
    }

    int[] parents;

    public List<Integer> findSmallestSetOfVerticesBCJ(int n, List<List<Integer>> edges) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (List<Integer> edge : edges) {
            merge(edge.get(0), edge.get(1));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x);
        parents[y] = rx;
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[][] g = new int[n][2];
        for (List<Integer> list : edges) {
            g[list.get(0)][1]++;
            g[list.get(1)][0]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[i][0] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int start = -1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length() - 1; i++) {
            String right = s.substring(i + 1);
            set.add(s.charAt(i));
            if (!contain(set, right)) {
                ans.add(i - start);
                start = i;
                set.clear();
            }
        }
        ans.add(s.length() - start - 1);
        return ans;
    }

    public boolean contain(Set<Character> set, String str) {
        for (Character c : str.toCharArray()) {
            if (set.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> partitionLabelsV1(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return total;
    }

    int total = 0;

    public void dfs(TreeNode node, int cur) {
        if (node == null) {
            return;
        }
        cur = cur * 10 + node.val;
        if (node.left == null && node.right == null) {
            total += cur;
            return;
        }
        dfs(node.left, cur);
        dfs(node.right, cur);
    }



    public static void main(String[] args) {
        Day4 day4 = new Day4();
        List<List<Integer>> eg = new ArrayList<>();
        //[0,1],[0,2],[2,5],[3,4],[4,2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(day4.sumNumbers(root));
    }
}
