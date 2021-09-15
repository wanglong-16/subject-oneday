package com.leetcode.august.solutions;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-05 20:31:42
 * @author: wanglong16@meicai.cn
 */
// 本题为考试多行输入输出规范示例，无需提交，不计分。
import java.util.*;

public class Solution6 {

    static String[] strings;

    // 1,0,0,0,0,1,0,0,1,0,1

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineOne = sc.nextLine();
        strings = lineOne.split(",");
        int maxParts = strings.length;
        List<Integer> parties = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            if ("1".equals(strings[i])) {
                parties.add(i);
            }
        }
        int maxDiff = parties.get(0);
        for (int i = 1; i < parties.size(); i++) {
            maxDiff = Math.max(maxDiff, (parties.get(i) - parties.get(i - 1)) / 2);
        }
        maxDiff = Math.max(maxDiff, maxParts - parties.get(parties.size() - 1) - 1);
        System.out.println(maxDiff);
    }

}

