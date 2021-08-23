package com.leetcode.august.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-18 23:41:08
 * @author: wanglong16@meicai.cn
 */
public class Solution2 {

    static List<List<Integer>> arr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            List<Integer> ar = new ArrayList<>();
            for (String st : s.split(" ")) {
                ar.add(Integer.parseInt(st));
            }
            arr.add(ar);
        }
        int[] tt = calculate(n);
        for (int i = 0; i < n; i++) {
            System.out.print(tt[i]);
            System.out.print(" ");
        }
    }

    public static int[] calculate(int n) {
        int[] ans = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> temp = arr.get(i);
            for (Integer in : temp) {
                if (!used[in - 1]) {
                    used[in - 1] = true;
                    ans[i] = in;
                    break;
                }
            }
        }
        return ans;
    }


}
