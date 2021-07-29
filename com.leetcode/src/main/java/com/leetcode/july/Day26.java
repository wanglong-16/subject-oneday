package com.leetcode.july;

import java.util.*;
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


}
