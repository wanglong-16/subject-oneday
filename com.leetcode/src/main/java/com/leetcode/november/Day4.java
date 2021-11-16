package com.leetcode.november;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-15 19:19:47
 * @author: wanglong16@meicai.cn
 */
public class Day4 {

    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] w1cnt = new int[26], w2cnt = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            w1cnt[word1.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < word2.length(); i++) {
            w2cnt[word2.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < 26; i++) {
            if (Math.abs(w1cnt[i] - w2cnt[i]) > 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。一个机器人 初始 在格子 (0, 0) ，方向为 "East" 。
     *
     * 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。
     *
     * 沿着当前方向尝试 往前一步 。
     * 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。
     * 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。
     *
     * 请你实现 Robot 类：
     *
     * Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。
     * void move(int num) 给机器人下达前进 num 步的指令。
     * int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。
     * String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。
     *
     */


    static class Robot {
        List<int []> positions = new ArrayList<>();
        List<String> directions = new ArrayList<>();
        int totalMove = 0;
        int width, height;
        public Robot(int width, int height) {
            // x = i, y = 0, dirs = East
            this.width = width;
            this.height = height;
            for (int i = 0; i < width - 1; i++) {
                positions.add(new int[] {i, 0});
                directions.add("East");
            }
            for (int i = 0; i < height - 1; i++) {
                positions.add(new int[] {width - 1, i});
                directions.add("North");
            }
            for (int i = width - 1; i > 0; i--) {
                positions.add(new int[] {i, height - 1});
                directions.add("West");
            }
            for (int i = height - 1; i > 0; i--) {
                positions.add(new int[] {0, i});
                directions.add("South");
            }
        }

        public void move(int num) {
            totalMove += num;
        }

        public int[] getPos() {
            return positions.get(totalMove % positions.size());
        }

        public String getDir() {
            return directions.get(totalMove % positions.size());
        }
    }

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.move(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */

    public static void main(String[] args) {
        Robot r = new Robot(20, 13);
    }
}
