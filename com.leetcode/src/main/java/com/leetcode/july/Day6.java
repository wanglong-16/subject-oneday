package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-08 08:49:24
 * @author: wanglong16@meicai.cn
 */
public class Day6 {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0, right = 0, cnt = 0, ans = 0;
        cnt = nums[0];
        while (right < nums.length) {
            if (cnt < goal) {
                right ++;
                cnt += nums[right];
            } else {
                if (cnt == goal) {
                    ans ++;
                } else {
                    left ++;
                    cnt -= nums[left];
                }
            }
        }
        return ans;
    }

    public boolean isOverHalf(int[] nums) {
        int cancidate = -1;
        int count = 0;
        for (Integer in : nums) {
            if (count == 0) {
                cancidate = in;
            }
            if (in == cancidate) {
                count ++;
            } else {
                count --;
            }
        }
        int cnt = 0;
        for (Integer in : nums) {
            if (in == cancidate) {
                cnt ++;
            }
        }
        return cnt * 2 > nums.length;
    }

    /**
     * 286. 墙与门
     * 你被给定一个 m × n 的二维网格 rooms ，网格中有以下三种可能的初始化值：
     *
     * -1 表示墙或是障碍物
     * 0 表示一扇门
     * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
     * 你要给每个空房间位上填上该房间到 最近门的距离 ，如果无法到达门，则填 INF 即可。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
     * 输出：[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
     * 示例 2：
     *
     * 输入：rooms = [[-1]]
     * 输出：[[-1]]
     * 示例 3：
     *
     * 输入：rooms = [[2147483647]]
     * 输出：[[2147483647]]
     * 示例 4：
     *
     * 输入：rooms = [[0]]
     * 输出：[[0]]
     *
     *
     * 提示：
     *
     * m == rooms.length
     * n == rooms[i].length
     * 1 <= m, n <= 250
     * rooms[i][j] 是 -1、0 或 231 - 1
     */
    public void wallsAndGates(int[][] rooms) {



    }


    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.isOverHalf(new int[] {1, 2, 1, 1, 1, 4, 3, 5}));
    }
}
