package com.leetcode.july;

import com.leetcode.util.tree.TreeNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-28 23:08:49
 * @author: wanglong16@meicai.cn
 */
public class Day26 {

    class Node {
        PriorityQueue<String> matchWords;
        Node[] children;

        public Node() {
            matchWords = new PriorityQueue<>();
            this.children = new Node[26];
        }
    }

    Node head = new Node();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String product : products) {
            insert(product);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            PriorityQueue<String> tem = startWith(searchWord.substring(0, i));
            List<String> t = new ArrayList<>();
            for (int j = 0; j < Math.min(3, tem.size()); j++) {
                t.add(tem.poll());
            }
            ans.add(t);
        }
        return ans;
    }

    public void insert(String string) {
        Node node = head;
        for (Character ch : string.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }
            node = node.children[ch - 'a'];
            node.matchWords.offer(string);
        }
    }

    public PriorityQueue<String> startWith(String prefix) {
        Node node = head;
        for (Character ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return new PriorityQueue<>();
            }
            node = node.children[ch - 'a'];
        }
        return node.matchWords;
    }

    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        ArrayList[] buckets = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            buckets[i] = new ArrayList();
        }
        for (String word : words) {
            Character key = word.charAt(0);
            buckets[key - 'a'].add(word);
        }
        for (int i = 0; i < s.length(); i++) {
            Character chr = s.charAt(i);
            List strings = new ArrayList(buckets[chr - 'a']);
            buckets[chr - 'a'].clear();
            //移动当前桶的所有单词到下一个桶，key是每个单词的下一个字符
            for (Object str : strings) {
                String string = str.toString();
                if (string.length() == 1) {
                    ans ++;
                } else {
                    Character key = string.charAt(1);
                    String val = string.substring(1);
                    buckets[key - 'a'].add(val);
                }
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    int sum = 0;

    public int goodNodes(TreeNode root) {
        count(root, Integer.MIN_VALUE);
        return sum;
    }

    public void count(TreeNode root, int max) {
        if (root != null) {
            if (root.val >= max) {
                sum ++;
            }
            int ma = Math.max(max, root.val);
            count(root.left, ma);
            count(root.right, ma);
        }
    }

    public String[] permutation(String s) {
        backtrack(s, 0, new StringBuilder());
        String[] ans = new String[stringSet.size()];
        int i = 0;
        for (String str : stringSet) {
            ans[i++] = str;
        }
        return ans;
    }

    Set<String> stringSet = new HashSet<>();

    public void backtrack(String pattern, int start, StringBuilder sb) {
        if (start == pattern.length()) {
            stringSet.add(sb.toString());
            return;
        }
        sb.append(pattern.charAt(start));
        backtrack(pattern, start + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        backtrack(pattern, start + 1, sb);
    }

    public static void main(String[] args) {
        Day26 day26 = new Day26();
        System.out.println(Arrays.toString(day26.permutation("abc")));
    }

}
