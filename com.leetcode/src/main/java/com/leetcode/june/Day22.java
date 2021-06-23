package com.leetcode.june;

import com.leetcode.util.tree.Node;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-22 22:02:41
 * @author: wanglong16@meicai.cn
 */
public class Day22 {

    public int maxDepthV1(Node root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (!cur.children.isEmpty()) {
                    queue.addAll(cur.children);
                }
            }
            ans++;
        }
        return ans;
    }

    public int maxDepth(Node root) {    //DFS递归写法
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (int i = 0; i < root.children.size(); i++) {
            depth = Math.max(depth, maxDepth(root.children.get(i)));
        }
        return depth + 1;
    }
}
