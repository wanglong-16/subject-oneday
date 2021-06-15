package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-09 14:51:26
 * @author: wanglong16@meicai.cn
 */
public class Day9 {

    /**
     * 1004. 最大连续1的个数 III
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     *
     * 返回仅包含 1 的最长（连续）子数组的长度。
     *
     *
     *
     * 示例 1：
     *
     * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：
     * [1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * 示例 2：
     *
     * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 20000
     * 0 <= K <= A.length
     * A[i] 为 0 或 1
     */
    public int longestOnesV1(int[] nums, int k) {
        int[] sumZero = new int[nums.length + 1];
        sumZero[0] = 0;
        for (int i = 0; i <= nums.length; i++) {
            sumZero[i + 1] = nums[i] == 0 ? sumZero[i] + 1 : sumZero[i];
        }
        int ans = 0;
        for (int i = 0; i < sumZero.length; i++) {
            for (int j = i + 1; j < sumZero.length; j++) {
                if (sumZero[j] - sumZero[i] <= k) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    public int longestOnes(int[] nums, int k) {
        int l = 0, zeroSum = 0, r = 0;
        for (;r < nums.length; r++) {
            if (zeroSum > k) {
                if (nums[l] == 0) {
                    zeroSum --;
                }
                l ++;
            } else {
                if (nums[r] == 0) {
                    zeroSum ++;
                }
            }
        }
        return r - l;
    }


    /**
     * 239. 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     *
     * 输入：nums = [1], k = 1
     * 输出：[1]
     * 示例 3：
     *
     * 输入：nums = [1,-1], k = 1
     * 输出：[1,-1]
     * 示例 4：
     *
     * 输入：nums = [9,11], k = 2
     * 输出：[11]
     * 示例 5：
     *
     * 输入：nums = [4,-2], k = 2
     * 输出：[4]
     */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//
//        List<Integer> current = new LinkedList<>();
//        for (int i = 0; i < k; i++) {
//            current.add(nums[i]);
//        }
//        current.sort(Comparator.comparingInt(a -> -a));
//        int[] ans = new int[nums.length - k + 1];
//        ans[0]
//        int l = 0, r = k;
//        while (r < nums.length) {
//
//
//
//        }
//
//    }


    /**
     * 1176. 健身计划评估
     * 你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。
     *
     * 他会有一份计划消耗的卡路里表，其中 calories[i] 给出了你的这位好友在第 i 天需要消耗的卡路里总量。
     *
     * 为了更好地评估这份计划，对于卡路里表中的每一天，你都需要计算他 「这一天以及之后的连续几天」 （共 k 天）内消耗的总卡路里 T：
     *
     * 如果 T < lower，那么这份计划相对糟糕，并失去 1 分；
     * 如果 T > upper，那么这份计划相对优秀，并获得 1 分；
     * 否则，这份计划普普通通，分值不做变动。
     * 请返回统计完所有 calories.length 天后得到的总分作为评估结果。
     *
     * 注意：总分可能是负数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
     * 输出：0
     * 解释：calories[0], calories[1] < lower 而 calories[3], calories[4] > upper, 总分 = 0.
     * 示例 2：
     *
     * 输入：calories = [3,2], k = 2, lower = 0, upper = 1
     * 输出：1
     * 解释：calories[0] + calories[1] > upper, 总分 = 1.
     * 示例 3：
     *
     * 输入：calories = [6,5,0,0], k = 2, lower = 1, upper = 5
     * 输出：0
     * 解释：calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, 总分 = 0.
     *
     *
     * 提示：
     *
     * 1 <= k <= calories.length <= 10^5
     * 0 <= calories[i] <= 20000
     * 0 <= lower <= upper
     */
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int l = 0, r = k - 1, sum = 0, score = 0;
        for (int i = 0; i < k; i++) {
            sum += calories[i];
        }
        while (r < calories.length) {
            if (sum < lower) {
                score --;
            }
            if (sum > upper) {
                score ++;
            }
            sum -= calories[l];
            l ++;
            r ++;
            if (r < calories.length) {
                sum += calories[r];
            }
        }
        return score;
    }


    /**
     * 1100. 长度为 K 的无重复字符子串
     * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：S = "havefunonleetcode", K = 5
     * 输出：6
     * 解释：
     * 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
     * 示例 2：
     *
     * 输入：S = "home", K = 5
     * 输出：0
     * 解释：
     * 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
     *
     *
     * 提示：
     *
     * 1 <= S.length <= 10^4
     * S 中的所有字符均为小写英文字母
     * 1 <= K <= 10^4
     */
    public int numKLenSubstrNoRepeats(String S, int K) {
        int length = S.length();
        int res = 0;
        int left = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (int right = 0; right < length; right++) {
            char ch = S.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            if (window.get(ch) == 1 && (right - left + 1 == K)) {
                res++;
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
                continue;
            }
            while (window.get(ch) > 1) {
                window.put(S.charAt(left), window.get(S.charAt(left)) - 1);
                left++;
            }
        }
        return res;
    }

    /**
     * 1629. 按键持续时间最长的键
     * LeetCode 设计了一款新式键盘，正在测试其可用性。测试人员将会点击一系列键（总计 n 个），每次一个。
     *
     * 给你一个长度为 n 的字符串 keysPressed ，其中 keysPressed[i] 表示测试序列中第 i 个被按下的键。releaseTimes 是一个升序排列的列表，其中 releaseTimes[i] 表示松开第 i 个键的时间。字符串和数组的 下标都从 0 开始 。第 0 个键在时间为 0 时被按下，接下来每个键都 恰好 在前一个键松开时被按下。
     *
     * 测试人员想要找出按键 持续时间最长 的键。第 i 次按键的持续时间为 releaseTimes[i] - releaseTimes[i - 1] ，第 0 次按键的持续时间为 releaseTimes[0] 。
     *
     * 注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
     *
     * 请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
     *
     *
     *
     * 示例 1：
     *
     * 输入：releaseTimes = [9,29,49,50], keysPressed = "cbcd"
     * 输出："c"
     * 解释：按键顺序和持续时间如下：
     * 按下 'c' ，持续时间 9（时间 0 按下，时间 9 松开）
     * 按下 'b' ，持续时间 29 - 9 = 20（松开上一个键的时间 9 按下，时间 29 松开）
     * 按下 'c' ，持续时间 49 - 29 = 20（松开上一个键的时间 29 按下，时间 49 松开）
     * 按下 'd' ，持续时间 50 - 49 = 1（松开上一个键的时间 49 按下，时间 50 松开）
     * 按键持续时间最长的键是 'b' 和 'c'（第二次按下时），持续时间都是 20
     * 'c' 按字母顺序排列比 'b' 大，所以答案是 'c'
     * 示例 2：
     *
     * 输入：releaseTimes = [12,23,36,46,62], keysPressed = "spuda"
     * 输出："a"
     * 解释：按键顺序和持续时间如下：
     * 按下 's' ，持续时间 12
     * 按下 'p' ，持续时间 23 - 12 = 11
     * 按下 'u' ，持续时间 36 - 23 = 13
     * 按下 'd' ，持续时间 46 - 36 = 10
     * 按下 'a' ，持续时间 62 - 46 = 16
     * 按键持续时间最长的键是 'a' ，持续时间 16
     *
     *
     * 提示：
     *
     * releaseTimes.length == n
     * keysPressed.length == n
     * 2 <= n <= 1000
     * 1 <= releaseTimes[i] <= 109
     * releaseTimes[i] < releaseTimes[i+1]
     * keysPressed 仅由小写英文字母组成
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[][] press = new int[keysPressed.length()][2];
        for (int i = 0; i < keysPressed.length(); i++) {
            press[i][0] = keysPressed.charAt(i);
            press[i][1] = i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i - 1];
        }
        Arrays.sort(press, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        return (char) press[0][0];
    }

}
