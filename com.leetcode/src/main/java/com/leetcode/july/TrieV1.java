package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-19 22:04:02
 * @author: wanglong16@meicai.cn
 */
class TrieV1 {
    boolean isEnd;
    TrieV1[] children;

    /** Initialize your data structure here. */
    public TrieV1() {
        this.children = new TrieV1[26];
        this.isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieV1 root = this;
        for (Character w : word.toCharArray()) {
            if (root.children[w - 'a'] == null) {
                root.children[w - 'a'] = new TrieV1();
            }
            root = root.children[w - 'a'];
        }
        root.isEnd = true; //最后一个节点 = true 表示从根节点到这个节点的单词是完整的单词，否则是某个单词的前缀
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieV1 node = this;
        for (Character c : word.toCharArray()) {
            if (node.children[c -'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieV1 node = this;
        for (Character c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }



    /** Returns if the word is in the trie. */
    public boolean searchV1(String word) {
        TrieV1 node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWithV1(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // 查找某个单词前缀，如果返回的节点中isEnd == true 表示是单词的结尾
    private TrieV1 searchPrefix(String prefix) {
        TrieV1 root = this;
        for (Character c : prefix.toCharArray()) {
            if (root.children[c - 'a'] == null) {
                return null;
            }
            root = root.children[c - 'a'];
        }
        return root;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
