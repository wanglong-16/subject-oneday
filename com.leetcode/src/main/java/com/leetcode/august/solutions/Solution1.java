package com.leetcode.august.solutions;

import java.util.Scanner;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-17 23:16:35
 * @author: wanglong16@meicai.cn
 */
public class Solution1 {
    static int n = 10;
    static String string = "MMATSATMMT";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        string = scanner.nextLine();
        System.out.println(getLongestStr());
    }

    private static String getLongestStr() {
        int firstM = string.indexOf("M");
        int firstT = string.substring(firstM).indexOf("T");
        int lastT = string.lastIndexOf("T");
        int lastM = string.substring(0, lastT).lastIndexOf("M");
        if (lastM == -1 || firstT == -1 || firstT >= lastM) {
            return "";
        } else {
            return string.substring(firstT + 1, lastM);
        }
    }

}
