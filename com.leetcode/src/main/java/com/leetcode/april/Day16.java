package com.leetcode.april;

import java.lang.annotation.Target;
import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-17 10:01:46
 * @author: wanglong16@meicai.cn
 */
public class Day16 {

    /**
     * 220. 存在重复元素 III
     * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
     *
     * 如果存在则返回 true，不存在返回 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3,1], k = 3, t = 0
     * 输出：true
     * 示例 2：
     *
     * 输入：nums = [1,0,1,1], k = 1, t = 2
     * 输出：true
     * 示例 3：
     *
     * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
     * 输出：false
     *
     *
     * 提示：
     *
     * 0 <= nums.length <= 2 * 104
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 104
     * 0 <= t <= 231 - 1
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        int len = nums.length;
        if (len <= k) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    long diff = nums[i] > nums[j] ? (long) nums[i] - (long) nums[j] :  (long) nums[j] - (long) nums[i];
                    if (diff <= t) {
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= Math.min(i + k, len - 1); j++) {
                long diff = nums[i] > nums[j] ? (long) nums[i] - (long) nums[j] :  (long) nums[j] - (long) nums[i];
                if (diff <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicateV1(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *
     *
     * 说明:
     *
     * 为什么返回数值是整数，但输出的答案是数组呢?
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
     * 示例 1：
     *
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     *
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     */
    public int removeDuplicates(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[ans]) {
                nums[ans + 1] = nums[i];
                ans ++;
            }
        }
        return ans;
    }

    public boolean checkIfPangram(String sentence) {
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < sentence.length(); i++) {
            characterSet.add(sentence.charAt(i));
            if (characterSet.size() == 26) {
                return true;
            }
        }
        return false;
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        long total = 0;
        int ans = 0;
        for (Integer in : costs) {
            total += in;
            if (total <= coins) {
                ans ++;
            }
        }
        return ans;
    }

    public int[] getOrder(int[][] tasks) {
        //按照任务
        List<Task> tas = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            int[] t = tasks[i];
            tas.add(new Task(t[0], t[1], i));
        }
        Collections.sort(tas, Comparator.comparingInt(o -> o.start));

        int[] ans = new int[tas.size()];
        Task task = tas.get(0);
        int currTime = task.start + task.cost;
        ans[0] = task.index;
        tas.remove(task);

        return ans;
    }


    private class Task {
        private int start;
        private int cost;
        public int index;

        public Task(int start, int cost, int index) {
            this.start = start;
            this.cost = cost;
            this.index = index;
        }
    }

    public int getXORSum(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length;
        int[][] andMat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                andMat[i][j] = arr1[i] & arr2[j];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans ^= andMat[i][j];
            }
        }
        return ans;
    }
}
