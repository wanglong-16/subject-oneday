package com.leetcode.july;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-27 08:21:59
 * @author: wanglong16@meicai.cn
 */
public class Day25 {

    public String interpret(String command) {
        StringBuilder ans = new StringBuilder();
        int tem = 0;
        while (tem < command.length()) {
            if (command.charAt(tem) == 'G') {
                ans.append('G');
                tem ++;
            } else if (command.charAt(tem) == '(') {
                if (command.charAt(tem + 1) == ')') {
                    ans.append('o');
                    tem += 2;
                } else {
                    ans.append("al");
                    tem += 4;
                }
            }
        }
        return ans.toString();
    }
}
