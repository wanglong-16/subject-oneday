package com.leetcode.august;

import com.leetcode.util.tree.TreeNode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-14 09:15:06
 * @author: wanglong16@meicai.cn
 */
public class Day9 {

    List<List<Integer>> ans = new ArrayList<>();
    int targetSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        if (root != null) {
            dfs(root, new ArrayList<>(), 0);
        }
        return ans;
    }

    public void dfs(TreeNode root, List<Integer> elements, int sum) {
        sum += root.val;
        elements.add(root.val);
        if (sum < targetSum) {
            if (root.left != null) {
                dfs(root.left, elements, sum);
                elements.remove(elements.size() - 1);
            }
            if (root.right != null) {
                dfs(root.right, elements, sum);
                elements.remove(elements.size() - 1);
            }
        } else if (sum == targetSum){
            if (root.right == null && root.left == null) {
                ans.add(new ArrayList<>(elements));
            }
        }
    }



    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        while (n >= 10) {
            n = calculate(n);
        }
        System.out.print(n);
    }

    public static int calculate(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}
