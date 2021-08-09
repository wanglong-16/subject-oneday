package com.leetcode.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-05 22:41:13
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        parents = new int[n];
        rank = new int[n];
        for (int i = 1; i < n; i++) {
            parents[i] = i;
            rank[i] = i;
        }
        for (int[] edge : edges) {
            int rx = find(edge[0]), ry = find(edge[1]);
            if (rx == ry) {
                return edge;
            } else {
                merge(rx, ry);
            }
        }
        return new int[] {};
    }

    int[] parents;
    int[] rank;


    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int rx, int ry) {
        if (rank[rx] <= rank[ry]) {
            parents[rx] = ry;
            if (rank[rx] == rank[ry]) {
                rank[ry] ++;
            }
        } else {
            parents[ry] = rx;
        }
    }



    public int compareVersion(String version1, String version2) {
        List<Integer> v1 = Arrays.stream(version1.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> v2 = Arrays.stream(version2.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
        int size = Math.min(v1.size(), v2.size());
        for (int i = 0; i < size; i++) {
            if (v1.get(i) < v2.get(i)) {
                return -1;
            } else if (v1.get(i) > v2.get(i)) {
                return 1;
            }
        }
        if (size == v1.size()) {
            for (int i = size; i < v2.size(); i++) {
                if (v2.get(i) > 0) {
                    return -1;
                }
            }
        } else {
            for (int i = size; i < v1.size(); i++) {
                if (v1.get(i) > 0) {
                    return 1;
                }
            }
        }
        return 0;
    }


    public String replaceWords(List<String> dictionary, String sentence) {
        for (String w : dictionary) {
            insert(w);
        }
        StringBuilder ans = new StringBuilder();
        for (String sente : sentence.split(" ")) {
            ans.append(searchWordRoot(sente)).append(" ");
        }
        ans.deleteCharAt(ans.length());
        return ans.toString();
    }

    Node head = new Node();

    public void insert(String word) {
        Node node = head;
        for (Character ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }
            node = node.children[ch - 'a'];
        }
        node.isWord = true;
    }

    public String searchWordRoot(String word) {
        StringBuilder sb = new StringBuilder();
        Node node = head;
        for (Character ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return word;
            } else {
                node = node.children[ch - 'a'];
                sb.append(ch);
                if (node.isWord) {
                    return sb.toString();
                }
            }
        }
        return word;
    }

    class Node {
        Node[] children;
        boolean isWord;

        public Node() {
            children = new Node[26];
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("001"));
    }


}
