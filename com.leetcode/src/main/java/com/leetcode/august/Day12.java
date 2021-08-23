package com.leetcode.august;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-17 22:34:40
 * @author: wanglong16@meicai.cn
 */
public class Day12 {


    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(new ArrayList<>(), n, k, 1);
        return ans;
    }

    int ansV4 = 0;
    int[] list;

    public int combinationSum4_1(int[] nums, int target) {
        list = nums;
        backTrackingV4(target);
        return ansV4;
    }

    public void backTrackingV4(int sum) {
        if (sum == 0) {
            ansV4 ++;
        } else if (sum > 0) {
            for (Integer i : list) {
                if (sum >= i) {
                    backTrackingV4(sum - i);
                }
            }
        }
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }


    public void backTracking(List<Integer> temp, int sum, int total, int index) {
        if (temp.size() == total && sum == 0) {
            ans.add(new ArrayList<>(temp));
        } else if (temp.size() < total) {
            for (int i = index; i <= 9; i++) {
                if (sum >= i) {
                    temp.add(i);
                    backTracking(temp, sum - i, total, i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public int calculateTree(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return calculateTree(root.left) + calculateTree(root.right);
        }
    }

    // i / i + 1
    //   2
    //  3 4
    // 6 5 7
    //4 1 8 3
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size(), cols = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[rows][cols];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < triangle.get(i).size() - 1; j++) {
                int x = dp[i - 1][i - 1] + triangle.get(i).get(j);
                int y = dp[i - 1][i - 1] + triangle.get(i).get(j + 1);
                dp[i][j] = Math.min(x, y);
            }
        }
        int ans = 0;
        for (int i = 0; i < dp[0].length; i++) {
            ans = Math.max(dp[rows - 1][i], ans);
        }
        return 1;
    }




    public static void main(String[] args) {
        Day12 day12 = new Day12();
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(Arrays.asList(2));
        arr.add(Arrays.asList(3,4));
        arr.add(Arrays.asList(6,5,7));
        arr.add(Arrays.asList(4,1,8,3));

        System.out.println(day12.minimumTotal(arr));
    }

    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



}
