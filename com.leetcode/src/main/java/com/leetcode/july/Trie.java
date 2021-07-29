package com.leetcode.july;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-28 22:01:15
 * @author: wanglong16@meicai.cn
 */
class Trie {

    Map<String, Integer> map = new HashMap<>(10000);

    class Node {
        boolean isEnd = false;
        Node[] children;
        int count;

        public Node() {
            this.children = new Node[26];
            this.count = 0;
        }
    }

    Node head;
    public Trie() {
        head = new Node();
    }

    public void insert(String word) {
        Node node = head;
        for (Character ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node();
            }
            node = node.children[ch - 'a'];
            node.count ++;
        }
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    public int countWordsEqualTo(String word) {
        return map.get(word);
    }

    public int countWordsStartingWith(String prefix) {
        Node node = head;
        for (Character ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return -1;
            }
            node = node.children[ch - 'a'];
        }
        return node.count;
    }

    public void erase(String word) {
        Node node = head;
        if (map.get(word) == null || map.get(word) <= 0) {
            return;
        } else {
            map.put(word, map.get(word) - 1);
        }
        for (Character ch : word.toCharArray()) {
            if (node.children[ch - 'a'] != null) {
                node.children[ch - 'a'].count --;
            }
            node = node.children[ch - 'a'];
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
