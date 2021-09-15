package com.leetcode.august.solutions;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-05 20:31:42
 * @author: wanglong16@meicai.cn
 */
import java.util.*;

public class Solution5 {

    static String[] vlan;
    static int requireVlan = 0;
    static boolean[] notUsed = new boolean[4095];
    // 20-21,15,18,30,5-10
    // 15

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineOne = sc.nextLine();
        vlan = lineOne.split(",");
        for (int i = 0; i < vlan.length; i++) {
            String[] split = vlan[i].split("-");
            if (split.length == 2) {
                for (int j = Integer.parseInt(split[0]); j < Integer.parseInt(split[1]); j++) {
                    notUsed[j] = true;
                }
            } else if (split.length == 1){
                notUsed[Integer.parseInt(split[0])] = true;
            }
        }
        requireVlan = Integer.parseInt(sc.nextLine());
        notUsed[requireVlan] = false;

        System.out.println(getVlanPollAfterGenerate(requireVlan));
    }

    static String getVlanPollAfterGenerate(int requireVlan) {
        StringBuilder sb = new StringBuilder();
        int right = 1;
        while (right < notUsed.length) {
            if (notUsed[right]) {
                int left = right;
                for (int i = left; i < notUsed.length; i++) {
                    if (notUsed[i]) {
                        right ++;
                    } else {
                        break;
                    }
                }
                if (left == right) {
                    sb.append(left).append(",");
                } else {
                    sb.append(left).append("-").append(right).append(",");
                }
            }
            right ++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
