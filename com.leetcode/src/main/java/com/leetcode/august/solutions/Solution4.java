package com.leetcode.august.solutions;

import java.util.Scanner;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-05 20:22:08
 * @author: wanglong16@meicai.cn
 */
public class Solution4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        String temp = scanner.nextLine();
        System.out.println(calculate(temp, len));
    }

    public static int calculate(String str, int len) {
        int result = 0, sum = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == 'E') {
                sum ++;
            } else {
                sum --;
            }
            result = Math.max(sum, result);
            if (sum < 0) {
                sum = 0;
            }
        }
        return result;
    }

}
