package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-20 22:35:39
 * @author: wanglong16@meicai.cn
 */
public class Day20 {

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root = new TrieNode();

    String longestWord = "";

    public String longestWord(String[] words) {
        for (String word : words) {
            insert(word);
        }
        StringBuilder sb = new StringBuilder();
        dfs(sb, root);
        return longestWord;
    }

    public void dfs(StringBuilder sb, TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null && node.children[i].isWord) {
                sb.append((char) ('a' + i));
                dfs(sb, node.children[i]);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        longestWord = sb.length() > longestWord.length() ? sb.toString() : longestWord;
    }


    public void insert(String string) {
        TrieNode node = root;
        for (Character c : string.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String string) {
        TrieNode node = root;
        for (Character c : string.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }

    public boolean prefix(String prefix) {
        TrieNode node = root;
        for (Character c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }

    public int minProductSum(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            ans += nums1[i] * nums2[nums1.length - i - 1];
        }
        return ans;
    }



    public int maximumMinimumPath(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int ans = Math.min(grid[0][0], grid[rows- 1][cols - 1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        UFS ufs = new UFS(rows * cols);
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                queue.offer(new int[] {i, j, grid[i][j]});
            }
        }
        int[][] dirs = new int[][] {{-1,0}, {1, 0}, {0,-1}, {0, 1}};
        while (ufs.find(grid[0][0]) != ufs.find(grid[rows - 1][cols - 1])) {
            int[] cur = queue.poll();
            ans = Math.min(cur[2], ans);
            used[cur[0]][cur[1]] = true;
            for (int[] dir : dirs) {
                int[] nCr = new int[] {cur[0] + dir[0], cur[1] + dir[1]};
                if (nCr[0] < 0 || nCr[0] >= rows || nCr[1] < 0 || nCr[1] >= cols || !used[nCr[0]][nCr[1]]) {
                    continue;
                }
                ufs.merge(cur[0] * cols + cur[1], nCr[0] * cols + nCr[1]);
            }
        }
        return ans;
    }


    class UFS {

        int[] parent;
        int[] rank;

        public UFS(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public boolean merge(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            } else {
                while(rx != ry)
                {
                    int temp = parent[ry];
                    parent[ry] = rx;
                    ry = temp;
                }
                return true;
            }
        }
    }

    private List<String> list = new ArrayList<>();

    public String[] permutationV1(String S) {
        StringBuilder sb = new StringBuilder();
        dfs(S, sb);
        return list.toArray(new String[0]);
    }

    public void dfs(String str, StringBuilder sb) {
        if (sb.length() == str.length()) {
            list.add(sb.toString());
        } else {
            for (Character c : str.toCharArray()) {
                if (!sb.toString().contains(String.valueOf(c))) {
                    sb.append(c);
                    dfs(str, sb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer in : nums) {
            set.add(in);
        }
        dfs(set, new ArrayList<>());
        return ans;
    }

    public void dfs(Set<Integer> nums,  List<Integer> temp) {
        if (temp.size() == nums.size()) {
            ans.add(new ArrayList<>(temp));
        } else {
            for (Integer in : nums) {
                if (!temp.contains(in)) {
                    temp.add(in);
                    dfs(nums, temp);
                    temp.remove(in);
                }
            }
        }
    }


    String[] res = null;
    public String[] permutation(String S) {
        int size = 1;
        char[] chars = S.toCharArray();
        for(int i = 1; i <= chars.length; i++) {
            size *= i;
        }
        res = new String[size];
        helper(chars, new int[]{0}, 0);
        return res;
    }

    public void helper(char[] chars, int[] i, int index) {
        if(index == chars.length - 1) {
            res[i[0]++] = new String(chars);
            return;
        }

        for(int j = index; j < chars.length; j++) {
            exch(chars, index, j);
            helper(chars, i, index + 1);
            exch(chars, index, j);
        }
    }

    public void exch(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20();
        int[][] grid = new int[][] {
                {5,4,5},{1,2,6},{7,4,6}
        };
        System.out.println(day20.permute(new int[] {1,2,3}));
    }


}
