package com.leetcode.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-10 12:43:03
 * @author: wanglong16@meicai.cn
 */
public class Day7 {

    /**
     * 893. 特殊等价字符串组
     * 你将得到一个字符串数组 A。
     *
     * 每次移动都可以交换 S 的任意两个偶数下标的字符或任意两个奇数下标的字符。
     *
     * 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是 特殊等价 的。
     *
     * 例如，S = "zzxy" 和 T = "xyzz" 是一对特殊等价字符串，因为可以先交换 S[0] 和 S[2]，然后交换 S[1] 和 S[3]，使得 "zzxy" -> "xzzy" -> "xyzz" 。
     *
     * 现在规定，A 的 一组特殊等价字符串 就是 A 的一个同时满足下述条件的非空子集：
     *
     * 该组中的每一对字符串都是 特殊等价 的
     * 该组字符串已经涵盖了该类别中的所有特殊等价字符串，容量达到理论上的最大值（也就是说，如果一个字符串不在该组中，那么这个字符串就 不会 与该组内任何字符串特殊等价）
     * 返回 A 中特殊等价字符串组的数量。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
     * 输出：3
     * 解释：
     * 其中一组为 ["abcd", "cdab", "cbad"]，因为它们是成对的特殊等价字符串，且没有其他字符串与这些字符串特殊等价。
     * 另外两组分别是 ["xyzz", "zzxy"] 和 ["zzyx"]。特别需要注意的是，"zzxy" 不与 "zzyx" 特殊等价。
     * 示例 2：
     *
     * 输入：["abc","acb","bac","bca","cab","cba"]
     * 输出：3
     * 解释：3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 1000
     * 1 <= A[i].length <= 20
     * 所有 A[i] 都具有相同的长度。
     * 所有 A[i] 都只由小写字母组成。
     */
    public int numSpecialEquivGroups(String[] A) {
        List<String> uniq = new ArrayList<>();
        return -1;
    }

    private boolean isStrEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        List<Character> s1Even = new ArrayList<>(), s1Odd = new ArrayList<>();
        List<Character> s2Even = new ArrayList<>(), s2Odd = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                s1Odd.add(s1.charAt(i));
                s2Odd.add(s2.charAt(i));
            } else {
                s1Even.add(s1.charAt(i));
                s2Even.add(s2.charAt(i));
            }
        }
        Collections.sort(s1Even); Collections.sort(s2Even); Collections.sort(s1Odd); Collections.sort(s2Odd);
        return s1Even.equals(s2Even) && s1Odd.equals(s2Odd);
    }

    /**
     * 1619. 删除某些元素后的数组均值
     * 给你一个整数数组 arr ，请你删除最小 5% 的数字和最大 5% 的数字后，剩余数字的平均值。
     *
     * 与 标准答案 误差在 10-5 的结果都被视为正确结果。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
     * 输出：2.00000
     * 解释：删除数组中最大和最小的元素后，所有元素都等于 2，所以平均值为 2 。
     * 示例 2：
     *
     * 输入：arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
     * 输出：4.00000
     * 示例 3：
     *
     * 输入：arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
     * 输出：4.77778
     * 示例 4：
     *
     * 输入：arr = [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
     * 输出：5.27778
     * 示例 5：
     *
     * 输入：arr = [4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1]
     * 输出：5.29167
     *
     *
     * 提示：
     *
     * 20 <= arr.length <= 1000
     * arr.length 是 20 的 倍数
     * 0 <= arr[i] <= 105
     */
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int minIndex = (int) (arr.length * 0.05);
        int maxIndex = arr.length - (int) (arr.length * 0.05);
        int total = 0;
        for (int i = minIndex; i < maxIndex; i++) {
            total += arr[i];
        }
        return total / (arr.length * 0.9);
    }

    /**
     * 1716. 计算力扣银行的钱
     * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
     *
     * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
     *
     * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 4
     * 输出：10
     * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
     * 示例 2：
     *
     * 输入：n = 10
     * 输出：37
     * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
     * 示例 3：
     *
     * 输入：n = 20
     * 输出：96
     * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
     *
     *
     * 提示：
     *
     * 1 <= n <= 1000
     */
    public int totalMoney(int n) {
        int zl = 1, ans = 0;
        for (int i = 0; i < n / 7; i++) {
            for (int j = zl; j < zl + 7; j++) {
                ans += zl;
            }
            zl ++;
        }
        for (int i = 0; i < n % 7; i++) {
            ans += zl;
        }
        return ans;
    }

    /**
     * 1189. “气球” 的最大数量
     * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
     *
     * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：text = "nlaebolko"
     * 输出：1
     * 示例 2：
     *
     *
     *
     * 输入：text = "loonbalxballpoon"
     * 输出：2
     * 示例 3：
     *
     * 输入：text = "leetcode"
     * 输出：0
     *
     *
     * 提示：
     * balloon
     *
     * 1 <= text.length <= 10^4
     * text 全部由小写英文字母组成
     */
    public int maxNumberOfBalloons(String text) {
        int[] charcht = new int[26];
        for (Character ch : text.toCharArray()) {
            charcht[ch - 97] ++;
        }
        int aCnt = charcht[0], bCnt = charcht[1], lCnt = charcht[11] / 2;
        int oCnt = charcht[14] / 2, nCnt = charcht[13];
        return Math.min(Math.min(Math.min(Math.min(aCnt, bCnt), lCnt), oCnt), nCnt);
    }

    /**
     * 264. 丑数 II
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     *
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     */
    public int nthUglyNumber(int n) {
        int p1 = 1, p2 = 1, p3 = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] temp = {2, 3, 5};
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p1] * temp[0], dp[p2] * temp[1]), dp[p3] * temp[2]);
            if (dp[i] == dp[p1] * temp[0]) {
                p1 ++;
            }
            if (dp[i] == dp[p2] * temp[1]){
                p2 ++;
            }
            if (dp[i] == dp[p3] * temp[2]){
                p3 ++;
            }
        }
        return dp[n - 1];
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     *
     *
     * 示例 1：
     *
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     *
     * 输入: n = 10, m = 17
     * 输出: 2
     */
    public int lastRemaining(int n, int m) {
        return 0;
    }

    /**
     * 883. 三维形体投影面积
     * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
     *
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
     *
     * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
     *
     * 投影就像影子，将三维形体映射到一个二维平面上。
     *
     * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
     *
     * 返回所有三个投影的总面积。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[[2]]
     * 输出：5
     * 示例 2：
     *
     * 输入：[[1,2],[3,4]]
     * 输出：17
     * 解释：
     * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
     *
     * 示例 3：
     *
     * 输入：[[1,0],[0,2]]
     * 输出：8
     * 示例 4：
     *
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：14
     * 示例 5：
     *
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：21
     *
     *
     * 提示：
     *
     * 1 <= grid.length = grid[0].length <= 50
     * 0 <= grid[i][j] <= 50
     */
    public int projectionArea(int[][] grid) {
        int tX = 0, tY = 0, tZ = 0;
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(grid[i][j], max);
                if (grid[i][j] > 0) {
                    tZ ++;
                }
            }
            tX += max;
        }

        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(grid[j][i], max);
            }
            tY += max;
        }
        return tX + tY + tZ;
    }

}
