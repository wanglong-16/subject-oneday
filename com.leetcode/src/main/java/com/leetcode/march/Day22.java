package com.leetcode.march;

import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-22 22:01:08
 * @author: wanglong16@meicai.cn
 */
public class Day22 {

    /**
     * 1732. 找到最高海拔
     * 有一个自行车手打算进行一场公路骑行，这条路线总共由 n + 1 个不同海拔的点组成。自行车手从海拔为 0 的点 0 开始骑行。
     *
     * 给你一个长度为 n 的整数数组 gain ，其中 gain[i] 是点 i 和点 i + 1 的 净海拔高度差（0 <= i < n）。请你返回 最高点的海拔 。
     * 示例 1：
     *
     * 输入：gain = [-5,1,5,0,-7]
     * 输出：1
     * 解释：海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
     * 示例 2：
     *
     * 输入：gain = [-4,-3,-2,-1,4,3,2]
     * 输出：0
     * 解释：海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
     */
    public int largestAltitude(int[] gain) {
        int[] dp = new int[gain.length + 1];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < gain.length + 1; i++) {
            dp[i] = dp[i - 1] + gain[i - 1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 1662. 检查两个字符串数组是否相等
     * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
     *
     * 数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
     * 示例 1：
     *
     * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
     * 输出：true
     * 解释：
     * word1 表示的字符串为 "ab" + "c" -> "abc"
     * word2 表示的字符串为 "a" + "bc" -> "abc"
     * 两个字符串相同，返回 true
     * 示例 2：
     *
     * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
     * 输出：false
     * 示例 3：
     *
     * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
     * 输出：true
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String str : word1) {
            sb1.append(str);
        }
        for (String str : word2) {
            sb2.append(str);
        }
        return sb1.toString().equals(sb2.toString());
    }

    /**
     * 1295. 统计位数为偶数的数字
     * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
     * 示例 1：
     *
     * 输入：nums = [12,345,2,6,7896]
     * 输出：2
     * 解释：
     * 12 是 2 位数字（位数为偶数）
     * 345 是 3 位数字（位数为奇数）
     * 2 是 1 位数字（位数为奇数）
     * 6 是 1 位数字 位数为奇数）
     * 7896 是 4 位数字（位数为偶数）
     * 因此只有 12 和 7896 是位数为偶数的数字
     * 示例 2：
     *
     * 输入：nums = [555,901,482,1771]
     * 输出：1
     * 解释：
     * 只有 1771 是位数为偶数的数字。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 10^5
     * 100000
     */
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (Integer in : nums) {
            if (in >= 10 && in < 100 ||
                    in >= 1000 && in < 10000 ||
                    in == 100000) {
                ans ++;
            }
        }
        return ans;
    }

    /**
     * 1588. 所有奇数长度子数组的和
     * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     *
     * 子数组 定义为原数组中的一个连续子序列。
     *
     * 请你返回 arr 中 所有奇数长度子数组的和 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     * 示例 2：
     *
     * 输入：arr = [1,2]
     * 输出：3
     * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
     * 示例 3：
     *
     * 输入：arr = [10,11,12]
     * 输出：66
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int max = arr.length % 2 == 1 ? arr.length : arr.length - 1, ans = 0;
        for (int i = 0; i < max; i += 2) {
            for (int j = 0; j < arr.length - i; j++) {
                int temp = 0;
                for (int k = j; k < j + i; k++) {
                    temp += arr[k];
                }
                ans += temp;
            }
        }
        return ans;
    }

    /**
     * 1678. 设计 Goal 解析器
     * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
     *
     * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
     *
     *
     *
     * 示例 1：
     *
     * 输入：command = "G()(al)"
     * 输出："Goal"
     * 解释：Goal 解析器解释命令的步骤如下所示：
     * G -> G
     * () -> o
     * (al) -> al
     * 最后连接得到的结果是 "Goal"
     * 示例 2：
     *
     * 输入：command = "G()()()()(al)"
     * 输出："Gooooal"
     * 示例 3：
     *
     * 输入：command = "(al)G(al)()()G"
     * 输出："alGalooG"
     *
     *
     * 提示：
     *
     * 1 <= command.length <= 100
     * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
     */
    public String interpret(String command) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (Character c : command.toCharArray()) {
            if (c == ')') {
                if (stack.peek() == '(') {
                    stack.pop();
                    ans.append("o");
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (!stack.isEmpty()) {
                        Character ch = stack.pop();
                        if (ch != '(') {
                            sb.append(ch);
                        } else {
                            break;
                        }
                    }
                    ans.append(sb.reverse());
                }
            } else if (c == '('){
                StringBuilder sb1 = new StringBuilder();
                while (!stack.empty()) {
                    sb1.append(stack.pop());
                }
                ans.append(sb1);
            } else {
                stack.push(c);
            }
        }
        return ans.toString();
    }

    /**
     * 1266. 访问所有点的最小时间
     * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi] 。请你计算访问所有这些点需要的 最小时间（以秒为单位）。
     *
     * 你需要按照下面的规则在平面上移动：
     *
     * 每一秒内，你可以：
     * 沿水平方向移动一个单位长度，或者
     * 沿竖直方向移动一个单位长度，或者
     * 跨过对角线移动 sqrt(2) 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
     * 必须按照数组中出现的顺序来访问这些点。
     * 在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：points = [[1,1],[3,4],[-1,0]]
     * 输出：7
     * 解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
     * 从 [1,1] 到 [3,4] 需要 3 秒
     * 从 [3,4] 到 [-1,0] 需要 4 秒
     * 一共需要 7 秒
     * 示例 2：
     *
     * 输入：points = [[3,2],[-2,2]]
     * 输出：5
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            ans += calculateTime(points[i - 1], points[i]);
        }
        return ans;
    }

    public int calculateTime(int[] p1, int[] p2) {
        int disY = Math.abs(p1[1] - p2[1]), disX = Math.abs(p1[0] - p2[0]);
        return Math.max(disX, disY);
    }
}
