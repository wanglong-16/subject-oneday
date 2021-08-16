package com.leetcode.august;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-15 22:06:29
 * @author: wanglong16@meicai.cn
 */
public class Main {

    static List<String> userList = new ArrayList<>();
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
//        for (int i = 0; i < n; i++) {
//            userList.add(scanner.nextLine());
//        }
//        for (String s : userList) {
//            System.out.println(checkUser(s));
//        }
        System.out.println(checkUser("Oook1"));
    }

    public static String checkUser(String name) {
        if (name.length() <= 1) {
            return "Wrong";
        }
        char first = name.charAt(0);
        if (first >= '0' && first <= '9') {
            return "Wrong";
        }
        int charCnt = 0, numCnt = 0;
        for (Character c : name.toCharArray()) {
            if (c >= 'a' && c <= 'z' || c > 'A' && c < 'Z') {
                charCnt ++;
            } else if (c >= '0' && c <= '9') {
                numCnt ++;
            } else {
                return "Wrong";
            }
        }
        return  charCnt >= 1 &&  numCnt >= 1 ? "Accept" : "Wrong";
    }

}
