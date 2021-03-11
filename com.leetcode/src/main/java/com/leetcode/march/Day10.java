package com.leetcode.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-10 21:22:55
 * @author: wanglong16@meicai.cn
 */
public class Day10 {

    /**
     * 1637. 两点之间不包含任何点的最宽垂直面积
     * 给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直面积 的宽度。
     * 垂直面积 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直面积 为宽度最大的一个垂直面积。
     *
     * 请注意，垂直区域 边上 的点 不在 区域内。
     * 示例 1：
     * 输入：points = [[8,7],[9,9],[7,4],[9,7]]
     * 输出：1
     * 解释：红色区域和蓝色区域都是最优区域。
     * 示例 2：
     *
     * 输入：points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
     * 输出：3
     */
    public int maxWidthOfVerticalAreaV1(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            if ((points[i + 1][0] - points[i][0]) > max) {
                max = (points[i + 1][0] - points[i][0]);
            }
        }
        return max;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        int[] target = new int[points.length];
        for (int i = 0; i < target.length; i++) {
            target[i] = points[i][0];
        }
        Arrays.sort(target);
        int maxValue = 0 ;
        for (int i = 0; i < points.length - 1; i++) {
            if(target[i + 1] - target[i] > maxValue ) {
                maxValue = target[i + 1] - target[i];
            }
        }
        return maxValue;
    }


    /**
     * 1561. 你可以获得的最大硬币数目
     * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
     *
     * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
     * Alice 将会取走硬币数量最多的那一堆。
     * 你将会取走硬币数量第二多的那一堆。
     * Bob 将会取走最后一堆。
     * 重复这个过程，直到没有更多硬币。
     * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
     *
     * 返回你可以获得的最大硬币数目。
     * 示例 1：
     *
     * 输入：piles = [2,4,1,2,7,8]
     * 输出：9
     * 解释：选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 7 枚硬币的那堆，Bob 取走最后一堆。
     * 选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 2 枚硬币的那堆，Bob 取走最后一堆。
     * 你可以获得的最大硬币数目：7 + 2 = 9.
     * 考虑另外一种情况，如果选出的是 (1, 2, 8) 和 (2, 4, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
     * 示例 2：
     *
     * 输入：piles = [2,4,5]
     * 输出：4
     * 示例 3：
     *
     * 输入：piles = [9,8,7,6,5,1,2,3,4]
     * 输出：18
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int total = 0;
        for (int i = piles.length - 1; i >= piles.length / 3; i -= 2) {
            total += piles[i - 1];
        }
        return total;
    }

    /**
     * 1370. 上升下降字符串
     * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
     *
     * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s 中选择字符。
     * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
     * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
     * 重复步骤 5 ，直到你没法从 s 中选择字符。
     * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
     *
     * 请你返回将 s 中字符重新排序后的 结果字符串 。
     * 示例 1：
     *
     * 输入：s = "aaaabbbbcccc"
     * 输出："abccbaabccba"
     * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
     * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
     * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
     * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
     * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
     * 示例 2：
     *
     * 输入：s = "rat"
     * 输出："art"
     * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
     * 示例 3：
     *
     * 输入：s = "leetcode"
     * 输出："cdelotee"
     * 示例 4：
     *
     * 输入：s = "ggggggg"
     * 输出："ggggggg"
     * 示例 5：
     * 输入：s = "spo"
     * 输出："ops"
     */
    public String sortString(String s) {
        StringBuilder ans = new StringBuilder();
        int [] buckets = new int[26]; // 0 => a
        for (int i = 0; i < s.length(); i++) {
            buckets[s.charAt(i) - 97] ++;
        }
        int count = 0;
        while (count < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (buckets[i] > 0) {
                    buckets[i] --;
                    ans.append((char)(i + 97));
                    count ++;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (buckets[i] > 0) {
                    buckets[i] --;
                    ans.append((char)(i + 97));
                    count ++;
                }
            }
        }
        return ans.toString();
    }

    /**
     * 1630. 等差子数组
     * 如果一个数列由至少两个元素组成，且每两个连续元素之间的差值都相同，那么这个序列就是 等差数列 。更正式地，数列 s 是等差数列，只需要满足：对于每个有效的 i ， s[i+1] - s[i] == s[1] - s[0] 都成立。
     *
     * 例如，下面这些都是 等差数列 ：
     *
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * 下面的数列 不是等差数列 ：
     *
     * 1, 1, 2, 5, 7
     * 给你一个由 n 个整数组成的数组 nums，和两个由 m 个整数组成的数组 l 和 r，后两个数组表示 m 组范围查询，其中第 i 个查询对应范围 [l[i], r[i]] 。所有数组的下标都是 从 0 开始 的。
     *
     * 返回 boolean 元素构成的答案列表 answer 。如果子数组 nums[l[i]], nums[l[i]+1], ... , nums[r[i]] 可以 重新排列 形成 等差数列 ，answer[i] 的值就是 true；否则answer[i] 的值就是 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
     * 输出：[true,false,true]
     * 解释：
     * 第 0 个查询，对应子数组 [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。
     * 第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。
     * 第 2 个查询，对应子数组 [5,9,3,7] 。可以重新排列为等差数列 [3,5,7,9] 。
     * 示例 2：
     *
     * 输入：nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
     * 输出：[false,true,false,false,true,true]
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int [] temp = new int[r[i] - l[i] + 1];
            if (r[i] + 1 - l[i] >= 0) {
                System.arraycopy(nums, l[i], temp, 0, r[i] + 1 - l[i]);
            }
            Arrays.sort(temp);
            ans.add(check(temp));
        }
        return ans;
    }

    private boolean check(int [] arr) {
        for (int k = 1; k < arr.length - 1; k++) {
            if (arr[k + 1] - arr[k] != arr[1] - arr[0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1329. 将矩阵按对角线排序
     * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
     *
     * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
     * 示例 1：
     * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
     * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
     * 示例 2：
     *
     * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
     * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
     * 提示：
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * 1 <= mat[i][j] <= 100
     * 通过次数5,494提交次数7,089
     *
     *  3 3 1 1
     *  2 2 1 2
     *  1 1 1 2
     *   todo
     */
    public int[][] diagonalSort(int[][] mat) {
        int r = mat.length, c = mat[0].length; // r = 3, c = 4
        // 对角线排序
        for (int i = 0; i < Math.min(r, c) ; i++) {
            for (int j = 0; j < Math.min(r, c) - 1; j++) {
                if (mat[j][j] > mat[j + 1][j + 1]) {
                    //swap
                    mat[j][j] = mat[j][j] + mat[j + 1][j + 1];
                    mat[j + 1][j + 1] = mat[j][j] - mat[j + 1][j + 1];
                    mat[j][j] = mat[j][j] - mat[j + 1][j + 1];
                }
            }
        }
        //列开始冒泡排序
        if (c >= 3) {
            // i = 1, 2
            for (int i = 1; i < c - 1; i++) {//标示第几列
                for (int j = i; j < c - i; j++) { //从第几个 j 1 2 3
                    for (int k = i; k < c - i - 1; k++) {// 1, 2
                        if (mat[k - 1][i + k] > mat[k + 1][i + k + 1]) {
                            // 1, 2
                            //swap
                            mat[k][i + k] = mat[k][i + k] + mat[k + 1][i + k + 1];
                            mat[k + 1][i + k + 1] = mat[k][i + k] - mat[k + 1][i + k + 1];
                            mat[k][i + k] = mat[k][i + k] - mat[k + 1][i + k + 1];
                        }
                    }
                }
            }
        }
        //列冒泡排序
        if (r >= 3) {
            for (int i = 1; i < r - 2; i++) {
                for (int j = 0; j <= r - i; j++) {
                    for (int k = 0; k <= r - i - 1; k++) {
                        if (mat[i + k][k] > mat[i + k + 1][k + 1]) {
                            //swap
                            mat[i + k][k] = mat[i + k][k] + mat[i + k + 1][k + 1];
                            mat[i + k + 1][k + 1] = mat[k][k] - mat[i + k + 1][k + 1];
                            mat[i + k][k] = mat[i + k][k] - mat[i + k + 1][k + 1];
                        }
                    }
                }
            }
        }
        return mat;
    }

}
