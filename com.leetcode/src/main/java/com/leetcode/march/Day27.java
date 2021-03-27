package com.leetcode.march;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-26 18:44:28
 * @author: wanglong16@meicai.cn
 */
public class Day27 {

    /**
     * 1134. 阿姆斯特朗数
     * 假设存在一个 k 位数 N，其每一位上的数字的 k 次幂的总和也是 N，那么这个数是阿姆斯特朗数。
     *
     * 给你一个正整数 N，让你来判定他是否是阿姆斯特朗数，是则返回 true，不是则返回 false。
     *
     *
     *
     * 示例 1：
     *
     * 输入：153
     * 输出：true
     * 示例：
     * 153 是一个 3 位数，且 153 = 1^3 + 5^3 + 3^3。
     * 示例 2：
     *
     * 输入：123
     * 输出：false
     * 解释：
     * 123 是一个 3 位数，且 123 != 1^3 + 2^3 + 3^3 = 36。
     */

    public boolean isArmstrong(int n) {
        int temp = n, ans = 0;
        while (temp > 0) {
            ans += Math.pow(temp % 10, 3);
            temp /= 10;
        }
        return ans == n;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[] {-1, -1};
        int min = 1, max = nums.length - 1, mid;
        while (min < max) {
            mid = (max + min) % 2;
            if (nums[mid] == target) {
                for (int i = mid; i < nums.length; i++) {
                    if (nums[i] != target) {
                        ans[1] = i - 1;
                        break;
                    }
                }
                for (int i = mid; i >= 0; i--) {
                    if (nums[i] != target) {
                        ans[0] = i + 1;
                        break;
                    }
                }
                break;
            } else if (nums[mid] > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 804. 唯一摩尔斯密码词
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
     *
     * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
     *
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作单词翻译。
     *
     * 返回我们可以获得所有词不同单词翻译的数量。
     *
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     *
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] mors = new String[] {".-","-...","-.-.","-..","."
                ,"..-.","--.","....","..",".---","-.-",".-..","--"
                ,"-.","---",".--.","--.-",".-.","...","-","..-"
                ,"...-",".--","-..-","-.--","--.."};
        Set<String> translates = new HashSet<>();
        StringBuilder sb;
        for (String word : words) {
            sb = new StringBuilder();
            for (Character chr: word.toCharArray()) {
                sb.append(mors[chr - 97]);
            }
            translates.add(sb.toString());
        }
        return translates.size();
    }

    /**
     * 1180. 统计只含单一字母的子串
     * 给你一个字符串 S，返回只含 单一字母 的子串个数。
     *
     * 示例 1：
     *
     * 输入： "aaaba"
     * 输出： 8
     * 解释：
     * 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
     * "aaa" 出现 1 次。
     * "aa" 出现 2 次。
     * "a" 出现 4 次。
     * "b" 出现 1 次。
     * 所以答案是 1 + 2 + 4 + 1 = 8。
     * 示例 2:
     *
     * 输入： "aaaaaaaaaa"
     * 输出： 55
     */
    public int countLetters(String S) {
        int st = 0, cnt = 1, ans = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != S.charAt(st)) {
                st = i;
            }
            ans += i - st + 1;
        }
        return ans;
    }

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2 = 0, p3 = 0, min;
        List<Integer> ans = new ArrayList<>();
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] == arr2[p2] && arr1[p1] == arr3[p3]) {
                ans.add(arr1[p1]);
                p1 ++;
                p2 ++;
                p3 ++;
            } else {
                min = Math.min(Math.min(arr1[p1], arr2[p2]), arr3[p3]);
                if (min == arr1[p1]) {
                    p1 ++;
                }
                if (min == arr2[p2]) {
                    p2 ++;
                }
                if (min == arr3[p3]) {
                    p3 ++;
                }
            }
        }
        return ans;
    }

    /**
     * 796. 旋转字符串
     * 给定两个字符串, A 和 B。
     *
     * A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
     *
     * 示例 1:
     * 输入: A = 'abcde', B = 'cdeab'
     * 输出: true
     *
     * 示例 2:
     * 输入: A = 'abcde', B = 'abced'
     * 输出: false
     */
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            return true;
        }
        for (int i = 0; i < A.length(); i ++) {
            if (A.equals(B)) {
                return true;
            }
            A = A.substring(1) + A.substring(0, 1);
        }
        return false;
    }

    /**
     * 1252. 奇数值单元格的数目
     * 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
     *
     * 另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
     *
     * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
     *
     * ri 行上的所有单元格，加 1 。
     * ci 列上的所有单元格，加 1 。
     * 给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
     *
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：m = 2, n = 3, indices = [[0,1],[1,1]]
     * 输出：6
     * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
     * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
     * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
     * 示例 2：
     *
     *
     *
     * 输入：m = 2, n = 2, indices = [[1,1],[0,0]]
     * 输出：0
     * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
     *
     *
     * 提示：
     *
     * 1 <= m, n <= 50
     * 1 <= indices.length <= 100
     * 0 <= ri < m
     * 0 <= ci < n
     */
    public int oddCells(int m, int n, int[][] indices) {
        int[] rowIdx = new int[m], colIdx = new int[n];
        for (int[] index : indices) {
            rowIdx[index[0]] ++;
            colIdx[index[1]] ++;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((rowIdx[i] + colIdx[j]) % 2 == 1) {
                    ans ++;
                }
            }
        }
        return ans;
    }


    /**
     * 1351. 统计有序矩阵中的负数
     * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。
     *
     * 请你统计并返回 grid 中 负数 的数目。
     *
     *
     *
     * 示例 1：
     *
     * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * 输出：8
     * 解释：矩阵中共有 8 个负数。
     * 示例 2：
     *
     * 输入：grid = [[3,2],[1,0]]
     * 输出：0
     * 示例 3：
     *
     * 输入：grid = [[1,-1],[-1,-1]]
     * 输出：3
     * 示例 4：
     *
     * 输入：grid = [[-1]]
     * 输出：1
     *
     *
     * 提示：
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -100 <= grid[i][j] <= 100
     *
     *
     * 进阶：你可以设计一个时间复杂度为 O(n + m) 的解决方案吗？
     */
    public int countNegatives(int[][] grid) {
        // 优化
        int ans = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (ints[j] < 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
