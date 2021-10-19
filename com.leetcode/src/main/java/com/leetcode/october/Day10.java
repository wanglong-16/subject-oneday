package com.leetcode.october;


import com.leetcode.util.tree.TreeNode;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-11 21:30:54
 * @author: wanglong16@meicai.cn
 */
public class Day10 {



    /**
     * eg:  read read[addr=0x17,mask=0xff,val=0x7],read_his[addr=0xff,mask=0xff,val=0x1],read[addr=0xf0,mask=0xff,val=0x80]
     * @param target
     * @param sources
     * @return
     */
    static List<String> matchAddress(String target, String sources) {
        String[] thatSources = sources.split("]");
        List<String> res = new ArrayList<>();
        if (thatSources.length == 0) {
            return res;
        }
        for (int i = 0; i < thatSources.length; i++) {
            // ignore first ','
            String ss = i == 0 ? thatSources[i] : thatSources[i].substring(1);
            String[] split = ss.split("\\[");
            String head = split[0], body = split[1];
            if (head.equals(target)) {
                String[] bodyItems = body.split(",");
                int matchCnt = 0;
                String[] matchTemp = new String[3];
                for (String bodyItem : bodyItems) {
                    if (bodyItem.contains("addr=")) {
                        if (isValidateAddress(bodyItem.split("=")[1])) {
                            matchCnt++;
                            matchTemp[0] = bodyItem.split("=")[1];
                        }
                    } else if (bodyItem.contains("mask=")) {
                        if (isValidateAddress(bodyItem.split("=")[1])) {
                            matchCnt++;
                            matchTemp[1] = bodyItem.split("=")[1];
                        }
                    } else if (bodyItem.contains("val=")) {
                        if (isValidateAddress(bodyItem.split("=")[1])) {
                            matchCnt++;
                            matchTemp[2] = bodyItem.split("=")[1];
                        }
                    }
                }
                if (matchCnt >= 3) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < 3; j++) {
                        sb.append(matchTemp[j]);
                        if (j != 2) {
                            sb.append(" ");
                        }
                    }
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }

    static List<Character> positiveChars = Arrays.asList('0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F');

    static boolean isValidateAddress(String addStr) {
        String firstTwo = addStr.substring(0, 2);
        if ("0X".equals(firstTwo) || "0x".equals(firstTwo)) {
            for (int i = 2; i < addStr.length(); i++) {
                if (! positiveChars.contains(addStr.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode nd = queue.poll();
                if (i == size - 1) {
                    ans.add(nd.val);
                }
                if (nd.left != null) {
                    queue.add(nd.left);
                }
                if (nd.right != null) {
                    queue.add(nd.right);
                }
            }
        }
        return ans;
    }

    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for (int i = 0; i < seats.length; i++) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }

    public boolean winnerOfGame(String colors) {
        int aaCnt = 0, bbCnt = 0;
        for (int i = 1; i < colors.length() - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i + 1) && colors.charAt(i) == colors.charAt(i - 1)) {
                if (colors.charAt(i) == 'A') {
                    aaCnt ++;
                } else {
                    bbCnt ++;
                }
            }
        }
        return aaCnt > bbCnt;


    }

    //BBAAABBABBABB
    public static void main(String[] args) {
        Day10 day10 = new Day10();
        System.out.println(day10.winnerOfGame("AAAABBBB"));
    }
}
