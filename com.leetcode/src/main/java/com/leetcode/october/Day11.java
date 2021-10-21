package com.leetcode.october;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-18 15:33:57
 * @author: wanglong16@meicai.cn
 */
public class Day11 {

    public int findComplement(int num) {
        int maxPos = 0, ans = 0;
        for (int i = 0; i < 32; i++) {
            int cur = (num >> i) & 1;
            if (cur == 1) {
                maxPos = i;
            }
        }
        for (int i = 0; i <= maxPos; i++) {
            if (((num >> i) & 1) == 0) {
                ans += 1 << i;
            }
        }
        ExecutorService tp = Executors.newSingleThreadExecutor();
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        return ans;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.build("abcdef");
        trie.build("abcf");
        trie.build("abchhaa");
        trie.build("abcdgh");
        trie.build("abcdf");

        System.out.println(trie.allPrefWith("abcd"));
    }


}

class Trie {

    private Node head;

    public Trie() {
        this.head = new Node(false);
    }

    public void build(String str) {
        Node nd = head;
        for (char c : str.toCharArray()) {
            if (nd.children[c - 'a'] == null) {
                nd.children[c - 'a'] = new Node(false);
            }
            nd = nd.children[c - 'a'];
        }
        nd.isEnd = true;
    }

    public List<String> allPrefWith(String pref) {
        Node nd = head;
        List<String> ans = new ArrayList<>();
        for (char c : pref.toCharArray()) {
            if (nd.children[c - 'a'] != null) {
                nd = nd.children[c - 'a'];
            } else {
                return ans;
            }
        }
        backTrackIngNode(nd, new StringBuilder(pref));
        return btBuff;
    }

    List<String> btBuff = new ArrayList<>();

    private void backTrackIngNode(Node node, StringBuilder pref) {
        if (node != null) {
            if (node.isEnd) {
                btBuff.add(pref.toString());
            } else {
                for (int i = 0; i < node.children.length; i++) {
                    pref.append((char) ('a' + i));
                    backTrackIngNode(node.children[i], pref);
                    pref.deleteCharAt(pref.length() - 1);
                }
            }
        }
    }

    class Node {
        private boolean isEnd;
        private final Node[] children;

        public Node(boolean isEnd) {
            this.isEnd = isEnd;
            this.children = new Node[26];
        }
    }
}

