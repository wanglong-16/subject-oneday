package com.leetcode.april;

import com.leetcode.util.PrintUtil;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-05 20:03:58
 * @author: wanglong16@meicai.cn
 */
public class Day5 {

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     *
     *
     * 示例 1：
     *
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 2：
     *
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * 提示：
     *
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     * 通过次数113,023提交次数218,176
     */
    public int movingCountV1(int m, int n, int k) {
        int[][] pos = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (calculatePos(i) + calculatePos(j) <= k) {
                    pos[i][j] = 1;
                }
            }
        }
        PrintUtil.printTwoDimArr(pos);
        return -1;
    }

    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<>();
        // 向右和向下的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        boolean[][] vis = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 2; ++i) {
                int tx = dx[i] + x;
                int ty = dy[i] + y;
                if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || calculatePos(tx) + calculatePos(ty) > k) {
                    continue;
                }
                queue.offer(new int[]{tx, ty});
                vis[tx][ty] = true;
                ans++;
            }
        }
        return ans;
    }

    private int calculatePos(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     *
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5]
     * 输出: True
     *
     *
     * 示例 2:
     *
     * 输入: [0,0,1,2,5]
     * 输出: True
     *
     *
     * 限制：
     *
     * 数组长度为 5
     *
     * 数组的数取值为 [0, 13] .
     *
     * 通过次数61,472提交次数134,835
     */
    public boolean isStraight(int[] nums) {
        int zeroCnt = 0;
        Set<Integer> uniq = new HashSet<>();
        List<Integer> nu = new ArrayList<>();
        for (Integer in : nums) {
            if (in == 0) {
                zeroCnt ++;
            } else {
                if (uniq.contains(in)) {
                    return false;
                }
                uniq.add(in);
                nu.add(in);
            }
        }
        Collections.sort(nu);
        return nu.get(4 - zeroCnt) - nu.get(0) == 4;
    }

    /**
     * 1550. 存在连续三个奇数的数组
     * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [2,6,4,1]
     * 输出：false
     * 解释：不存在连续三个元素都是奇数的情况。
     * 示例 2：
     *
     * 输入：arr = [1,2,34,3,4,5,7,23,12]
     * 输出：true
     * 解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 1000
     * 1 <= arr[i] <= 1000
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int jcnt = 0;
        for (Integer in : arr) {
            if (in % 2 == 1) {
                jcnt ++;
            } else {
                jcnt = 0;
            }
            if (jcnt >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *
     *
     * 说明：
     *
     * 为什么返回数值是整数，但输出的答案是数组呢？
     *
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     *
     * 你可以想象内部操作如下:
     *
     * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
     * int len = removeDuplicates(nums);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     *
     * 输入：nums = [0,0,1,1,1,1,2,3,3]
     * 输出：7, nums = [0,0,1,1,2,3,3]
     * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 3 * 104
     * -104 <= nums[i] <= 104
     * nums 已按升序排列
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
