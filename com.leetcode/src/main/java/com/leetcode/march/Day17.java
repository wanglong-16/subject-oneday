package com.leetcode.march;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-17 10:24:37
 * @author: wanglong16@meicai.cn
 */
public class Day17 {

    /**
     * 41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     * 示例 1：
     *
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     */
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (i < nums.length - 1 && nums[i + 1] == count) {
                } else if (nums[i] != count){
                    break;
                } else {
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 1762. 能看到海景的建筑物
     * 有 n 座建筑物。给你一个大小为 n 的整数数组 heights 表示每一个建筑物的高度。
     *
     * 建筑物的右边是海洋。如果建筑物可以无障碍地看到海洋，则建筑物能看到海景。确切地说，如果一座建筑物右边的所有建筑都比它 矮 时，就认为它能看到海景。
     *
     * 返回能看到海景建筑物的下标列表（下标 从 0 开始 ），并按升序排列。
     * 示例 1：
     *
     * 输入：heights = [4,2,3,1]
     * 输出：[0,2,3]
     * 解释：1 号建筑物看不到海景，因为 2 号建筑物比它高
     * 示例 2：
     *
     * 输入：heights = [4,3,2,1]
     * 输出：[0,1,2,3]
     * 解释：所有的建筑物都能看到海景。
     * 示例 3：
     *
     * 输入：heights = [1,3,2,4]
     * 输出：[3]
     * 解释：只有 3 号建筑物能看到海景。
     * 示例 4：
     *
     * 输入：heights = [2,2,2,2]
     * 输出：[3]
     * 解释：如果建筑物右边有相同高度的建筑物则无法看到海景。
     */
    public int[] findBuildings(int[] heights) {
        int maxRight = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i++) {
            if (heights[i] > maxRight) {
                temp.add(i);
                maxRight = heights[i];
            }
        }
        Collections.sort(temp);
        int[] ans = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            ans[i] = temp.get(i);
        }
        return ans;
    }

    /**
     * 1282. 用户分组
     * 有 n 位用户参加活动，他们的 ID 从 0 到 n - 1，每位用户都 恰好 属于某一用户组。给你一个长度为 n 的数组 groupSizes，其中包含每位用户所处的用户组的大小，请你返回用户分组情况（存在的用户组以及每个组中用户的 ID）。
     *
     * 你可以任何顺序返回解决方案，ID 的顺序也不受限制。此外，题目给出的数据保证至少存在一种解决方案。
     * 示例 1：
     *
     * 输入：groupSizes = [3,3,3,3,3,1,3]
     * 输出：[[5],[0,1,2],[3,4,6]]
     * 解释：
     * 其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
     * 示例 2：
     *
     * 输入：groupSizes = [2,1,3,3,3,2]
     * 输出：[[1],[0,5],[2,3,4]]
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] template = new int[groupSizes.length][2];
        for (int i = 0; i < groupSizes.length; i++) {
            template[i][0] = i;
            template[i][1] = groupSizes[i];
        }
        Arrays.sort(template, Comparator.comparingInt(o -> o[1]));
        int currLen = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = groupSizes.length - 1; i >= 0; i--) {
            if (currLen <= template[i][1]) {
                temp.add(template[i][0]);
                currLen ++;
            } else {
                currLen = 0;
                ans.add(new ArrayList<>(temp));
                temp.clear();
            }
        }
        return ans;
    }

    /**
     * 1402. 做菜顺序
     * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
     *
     * 一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
     *
     * 请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
     *
     * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。

     * 示例 1：
     *
     * 输入：satisfaction = [-1,-8,0,5,-9]
     * 输出：14
     * 解释：去掉第二道和最后一道菜，最大的喜爱时间系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
     * 示例 2：
     *
     * 输入：satisfaction = [4,3,2]
     * 输出：20
     * 解释：按照原来顺序相反的时间做菜 (2*1 + 3*2 + 4*3 = 20)
     * 示例 3：
     *
     * 输入：satisfaction = [-1,-4,-5]
     * 输出：0
     * 解释：大家都不喜欢这些菜，所以不做任何菜可以获得最大的喜爱时间系数。
     * 示例 4：
     *
     * 输入：satisfaction = [-2,5,-1,0,3,-3]
     * 输出：35
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        if (satisfaction[satisfaction.length - 1] <= 0) {
            return 0;
        }
        int firstLessZero = 0, ans = 0;
        for (int i = satisfaction.length - 1; i >= 0; i--) {
            if (satisfaction[i] < 0) {
                firstLessZero = i;
                break;
            }
        }
        for (int i = 0; i < firstLessZero + 1; i--) {
            int total = 0;
            for (int j = i; j < satisfaction.length; j++) {
                total += (j - i + 1) * satisfaction[j];
            }
            ans = Math.max(total, ans);
        }
        return ans;
    }
}
