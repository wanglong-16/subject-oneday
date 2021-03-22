package com.leetcode.march;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-16 18:40:06
 * @author: wanglong16@meicai.cn
 */
public class Day16 {

    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * 示例 2：
     *
     *
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     * 示例 3：
     *
     * 输入：matrix = [[1]]
     * 输出：[[1]]
     * 示例 4：
     *
     * 输入：matrix = [[1,2],[3,4]]
     * 输出：[[3,1],[4,2]]
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 00 -> 02
     * 01 -> 12
     *
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void rotateV1(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < (matrix.length + 1) / 2; j++) {
                int temp = matrix[i][j];
                // i = 0 j = 1
                //01 - 12 - 21 - 10
                matrix[i][j] = matrix[matrix.length - j - 1][i];
                matrix[matrix.length - j - 1][i] = matrix[matrix.length - i - 1][matrix.length - j - 1];
                matrix[matrix.length - i - 1][matrix.length - j - 1] = matrix[j][matrix.length - i - 1];
                matrix[j][matrix.length - i - 1] = temp;
            }
        }
    }


    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            int cur = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                cur += account[j];
            }
            max = Math.max(max, cur);
        }
        return max;
    }

    public int numJewelsInStones(String jewels, String stones) {
        int ans = 0;
        for (int i = 0; i < stones.length(); i++) {
            CharSequence sequence = stones.subSequence(i, i);
            if (jewels.contains(stones.subSequence(i, i))) {
                ans ++;
            }
        }
        return ans;
    }


    /**
     * 1773. 统计匹配检索规则的物品数量
     * 给你一个数组 items ，其中 items[i] = [typei, colori, namei] ，描述第 i 件物品的类型、颜色以及名称。
     *
     * 另给你一条由两个字符串 ruleKey 和 ruleValue 表示的检索规则。
     *
     * 如果第 i 件物品能满足下述条件之一，则认为该物品与给定的检索规则 匹配 ：
     *
     * ruleKey == "type" 且 ruleValue == typei 。
     * ruleKey == "color" 且 ruleValue == colori 。
     * ruleKey == "name" 且 ruleValue == namei 。
     * 统计并返回 匹配检索规则的物品数量 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
     * 输出：1
     * 解释：只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
     * 示例 2：
     *
     * 输入：items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
     * 输出：2
     * 解释：只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。
     *
     *
     * 提示：
     *
     * 1 <= items.length <= 104
     * 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
     * ruleKey 等于 "type"、"color" 或 "name"
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = "type".equals(ruleKey) ? 0 : ("color".equals(ruleKey) ? 1 : 2), count = 0;
        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 1431. 拥有最多糖果的孩子
     * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
     *
     * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
     * 示例 1：
     *
     * 输入：candies = [2,3,5,1,3], extraCandies = 3
     * 输出：[true,true,true,false,true]
     * 解释：
     * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 示例 2：
     *
     * 输入：candies = [4,2,1,1,2], extraCandies = 1
     * 输出：[true,false,false,false,false]
     * 解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
     * 示例 3：
     *
     * 输入：candies = [12,1,12], extraCandies = 10
     * 输出：[true,false,true]     *
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (Integer in : candies) {
            max = Math.max(max, in);
        }
        List<Boolean> ans = new ArrayList<>();
        for (Integer in : candies) {
            ans.add(extraCandies + in >= max);
        }
        return ans;
    }

    /**
     * 861. 翻转矩阵后的得分
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     *
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     *
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     *
     * 返回尽可能高的分数。
     * 示例：
     *
     * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * 输出：39
     * 解释：
     * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 20
     * 1 <= A[0].length <= 20
     * A[i][j] 是 0 或 1
     */
    public int matrixScore(int[][] A) {
        int ans = 0, rows = A.length, cols = A[0].length;
        //按照行转
        for (int i = 0; i < rows; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    A[i][j] = A[i][j] == 1 ? 0 : 1;
                }
            }
        }
        //按列统计1，当1多于 rows / 2 反转
        for (int i = 0; i < cols; i++) {
            int cR = 0;
            for (int j = 0; j < rows; j++) {
                if (A[j][i] == 0) {
                    cR ++;
                }
            }
            if (cR > rows / 2) {
                for (int j = 0; j < rows; j++) {
                    A[j][i] = A[j][i] == 1 ? 0 : 1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans += A[i][j] * Math.pow(2, cols - j - 1);
            }
        }
        return ans;
    }

    public int matrixScoreV1(int[][] A) {
        int m = A.length, n = A[0].length;
        int ret = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    nOnes += (1 - A[i][j]); // 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }
}
