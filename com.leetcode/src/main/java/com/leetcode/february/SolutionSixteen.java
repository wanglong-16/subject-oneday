package com.leetcode.february;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-27 09:24:17
 * @author: wanglong16@meicai.cn
 */
public class SolutionSixteen {

    /**
     * 395. 至少有K个重复字符的最长子串
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
     * 示例 1:
     *
     * 输入:
     * s = "aaabb", k = 3
     *
     * 输出:
     * 3
     *
     * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2:
     *
     * 输入:
     * s = "ababbc", k = 2
     *
     * 输出:
     * 5
     * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i + 1; j <= s.length(); ++j) {
                String str = s.substring(i, j);
                if (matchKStr(str, k)) {
                    max = Math.max(max, str.length());
                }
            }
        }
        return max;
    }

    public boolean matchKStr(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) != null) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            if (Integer.parseInt(entry.getValue().toString()) < k) {
                return false;
            }
        }
        return true;
    }

    /**
     * 302. 包含全部黑色像素的最小矩形
     * 图片在计算机处理中往往是使用二维矩阵来表示的。
     *
     * 假设，这里我们用的是一张黑白的图片，那么 0 代表白色像素，1 代表黑色像素。
     *
     * 其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素（像素点是水平或竖直方向连接的）。
     *
     * 那么，给出某一个黑色像素点 (x, y) 的位置，你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？
     * 示例:
     *
     * 输入:
     * [
     *   "0010",
     *   "0110",
     *   "0100"
     * ]
     * 和 x = 0, y = 2
     *
     * 输出: 6
     */
    public int minArea(char[][] image, int x, int y) {
        int minLeft = x, minTop = y, maxRight = x, maxBottom = y;
        for (int i = 0; i < image.length; ++i) {
            for (int j = 0; j < image[0].length; ++j) {
                if (image[i][j] == '1') {
                    minLeft = Math.min(i, minLeft);
                    minTop = Math.min(j, minTop);
                    maxRight = Math.max(i + 1, maxRight);
                    maxBottom = Math.max(j + 1, maxBottom);
                }
            }
        }
        return (maxRight - minLeft) * (maxBottom - minTop);
    }

    /**
     * 面试题 05.06. 整数转换
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     *
     * 示例1:
     *
     *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
     *  输出：2
     * 示例2:
     *
     *  输入：A = 1，B = 2
     *  输出：2
     * 提示:
     *
     * A，B范围在[-2147483648, 2147483647]之间
     */
    public int convertInteger(int A, int B) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += (A & 1) ^ (B & 1);
            A >>>= 1;
            B >>>= 1;
        }
        return ans;
    }

    /**
     * 面试题 17.04. 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 注意：本题相对书上原题稍作改动
     *
     * 示例 1：
     *
     * 输入：[3,0,1]
     * 输出：2
     * 示例 2：
     *
     * 输入：[9,6,4,2,3,5,7,0,1]
     * 输出：8
     */
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i] ^ i;
        }
        return ans;
    }

    /**
     * 896. 单调数列
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     *
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     *
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     * 示例 1：
     *
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     *
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     *
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     *
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     *
     * 输入：[1,1,1]
     * 输出：true
     */

    public boolean isMonotonic(int[] A) {
        int aStart = A[0], aEnd = A[A.length - 1];
        int incr = 0, decr = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= aStart) {
                decr ++;
                aStart = A[i];
            }
            if (A[A.length - i - 1] <= aEnd) {
                incr ++;
                aEnd = A[A.length - i -1];
            }
        }
        return incr == A.length || decr == A.length;
    }

    public boolean isMonotonicV1(int[] A) {
        int a = A[0];
        boolean incr = false, decr = false;
        for (int value : A) {
            if (value <= a) {
                decr = true;
            } else {
                decr = false;
                break;
            }
            a = value;
        }

        for (int value : A) {
            if (value >= a) {
                incr = true;
            } else {
                incr = false;
                break;
            }
            a = value;
        }
        return incr || decr;
    }

    public boolean isMonotonicV2(int[] A) {
        boolean inc = true, dec = true;
        int n = A.length;
        for (int i = 0; i < n - 1; ++i) {
            if (A[i] > A[i + 1]) {
                inc = false;
            }
            if (A[i] < A[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }

    /**
     * 面试题 17.01. 不用加号的加法
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     *
     * 示例:
     *
     * 输入: a = 1, b = 1
     * 输出: 2
     * 提示：
     *
     * a, b 均可能是负数或 0
     * 结果不会溢出 32 位整数
     * 通过次数8,394提交次数14,162
     */
    public int add(int a, int b) {
        int curBit, nextBit;
        while(b != 0) {
            curBit = a ^ b; //当前位的值
            nextBit = (a & b) << 1; // 同时为1进位
            a = curBit;
            b = nextBit;
        }
        return a;
    }

    /**
     *
     买卖股票的最佳时机
     给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     示例 1：
     输入：[7,1,5,3,6,4]
     输出：5
     解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     示例 2：
     输入：prices = [7,6,4,3,1]
     输出：0
     解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfitV1(int[] prices) {
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int pf = prices[j] - prices[i];
                if (pf > maxPrice) {
                    maxPrice = pf;
                }
            }
        }
        return maxPrice;
    }

    public int maxProfit(int[] prices) {
        int historyMin = Integer.MAX_VALUE, maxPrice = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            historyMin = Math.min(historyMin, prices[i]);
            maxPrice = Math.max(prices[i] - historyMin, maxPrice);
        }
        return maxPrice;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     *
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public int maxProfitI(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] - prices[i] > 0) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        return maxProfit;
    }

    /**
     * 36. 有效的数独
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 上图是一个部分填充的有效的数独。
     *
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   ["5","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: true
     * 示例 2:
     *
     * 输入:
     * [
     *   ["8","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: false
     * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     * 说明:
     *
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 给定数独序列只包含数字 1-9 和字符 '.' 。
     * 给定数独永远是 9x9 形式的。
     *
     * [["8","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]]
     */
    public boolean isValidSudoku(char[][] board) {
        //ascii 1 ~ 9 = 49 ~ 57
        boolean result = true;
        // 检查纵横
        for (int i = 0; i < 9; i++) {
            char [] rowChars = new char[9];
            char [] colChars = new char[9];
            for (int j = 0; j < 9; j++) {
                rowChars[j] = board[i][j];
                colChars[j] = board[j][i];
            }
            boolean isRowMatch = isUniqNums(rowChars);
            boolean isColumnMatch = isUniqNums(colChars);
            result = result && isRowMatch && isColumnMatch;
        }
        //检查9个中心点的 3 * 3
        for (int i = 1; i < 8; i+=3) {
            for (int j = 1; j < 8; j+=3) {
                result = result && checkCenter(board, i, j);
            }
        }
        return result;
    }

    private boolean isUniqNums(char[] chars) {
        Set<Character> uniqs = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 49 && chars[i] <= 57) {
                if (uniqs.contains(chars[i])) {
                    return false;
                } else {
                    uniqs.add(chars[i]);
                }
            }
        }
        return true;
    }

    private boolean checkCenter(char[][] board, int centerX, int centerY) {
        Set<Character> uniqs = new HashSet<>();
        for (int i = centerX - 1; i <= centerX + 1; i++) {
            for (int j = centerY - 1; j <= centerY + 1; j++) {
                if (board[i][j] >= 49 && board[i][j] <= 57) {
                    if (uniqs.contains(board[i][j])) {
                        return false;
                    } else {
                        uniqs.add(board[i][j]);
                    }
                }
            }
        }
        return true;
    }


    public boolean isValidSudokuVGuanFang(char[][] board) {
        // init data
        HashMap<Integer, Integer> [] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



}
