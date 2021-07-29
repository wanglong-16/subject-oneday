package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-27 22:03:46
 * @author: wanglong16@meicai.cn
 */
class WordDictionary {

    class Node {
        boolean isWord;
        Node[] nodes;

        public Node() {
            this.isWord = false;
            this.nodes = new Node[26];
        }
    }

    Node first;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        first = new Node();
    }

    public void addWord(String word) {
        Node fi = first;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (fi.nodes[ch - 'a'] == null) {
                fi.nodes[ch - 'a'] = new Node();
            }
            fi = fi.nodes[ch - 'a'];
        }
        fi.isWord = true;
    }

    public boolean search(String word) {
        Node fir = first;
        return dfs(word, 0, fir);
    }

    public boolean dfs(String word, int index, Node head) {
        if (index == word.length()) {
            return head.isWord;
        }
        char c = word.charAt(index);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (head.nodes[i] != null) {
                    if (dfs(word, index + 1, head.nodes[i])) {
                        return true;
                    }
                }
            }
        } else {
            int t = c - 'a';
            if (head.nodes[t] != null) {
                if (dfs(word, index + 1, head.nodes[t])) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
