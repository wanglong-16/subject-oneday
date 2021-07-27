package com.leetcode.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-25 10:19:31
 * @author: wanglong16@meicai.cn
 */
public class Day23 {

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(), tem = new StringBuilder();
        for (int i = 0; i < s.length(); i += k) {
            tem.append(s, i, i + k);
            sb.append(s, i, i + k);
            sb.reverse();
            int end = Math.min(2 * k + i, s.length());
            sb.append(s, i + k, end);
        }
        return sb.toString();
    }

    public int getLucky(String s, int k) {
        List<Integer> arr = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            Integer in = c - 'a' + 1;
            if (in < 10) {
                arr.add(in);
            } else {
                arr.add(in / 10);
                arr.add(in % 10);
            }
        }
        for (int i = 0; i < k; i++) {
            arr = convert(arr);
        }
        int res = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            int po = (int)Math.pow(10, arr.size() - i - 1);
            res += arr.get(i) * po;
        }
        return res;
    }
    
    public List<Integer> convert(List<Integer> arr) {
        List<Integer> temp = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < arr.size(); i++) {
            t += arr.get(i);
        }
        String s = String.valueOf(t);
        for (String c : s.split("")
             ) {
            temp.add(Integer.valueOf(c));
        }
        return temp;
    }


    public String maximumNumber(String num, int[] change) {
        int[] nums = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            nums[i] = num.charAt(i) - '0';
        }
        int start = -1, end = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int cha = change[nums[i]]; // map hou de
            if (nums[i] < cha) {
                start = i;
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > change[nums[j]]) {
                        end = j;
                        break;
                    }
                }
                break;
            }
        }
        if (start != -1) {
            StringBuilder ans = new StringBuilder();
            for (int j = 0; j < nums.length; j++) {
                if (j >= start && j < end) {
                    ans.append(change[nums[j]]);
                } else {
                    ans.append(nums[j]);
                }
            }
            return ans.toString();
        } else {
            return num;
        }

    }


    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int[][] stuAnswers = new int[students.length][2];
        int[][] menAnswers = new int[mentors.length][2];
        int len = students[0].length;
        for (int i = 0; i < stuAnswers.length; i++) {
            stuAnswers[i] = new int[] {i, transToBinary(students[i])};
            menAnswers[i] = new int[] {i, transToBinary(mentors[i])};
        }
        Arrays.sort(stuAnswers, Comparator.comparingInt(a -> a[1]));
        Arrays.sort(menAnswers, Comparator.comparingInt(a -> a[1]));
        int ans = 0;

        List<int[]> temp = new ArrayList<>();
        boolean[] isNotMatch = new boolean[stuAnswers.length];
        for (int i = 0; i < students.length; i++) {
            if (stuAnswers[i][1] == menAnswers[i][1]) {
                ans += len;
            } else {
                isNotMatch[i] = true;
                temp.add(menAnswers[i]);
            }
        }
        for (int i = 0; i < stuAnswers.length; i++) {
            if (isNotMatch[i]) {
                int score = stuAnswers[i][1];
                int index = -1, max = 0;
                for (int j = 0; j < temp.size(); j++) {
                    int diff = diff(score, temp.get(j)[1], len);
                    if (diff > max) {
                        max = diff;
                        index = j;
                    }
                }
                if (index != -1) {
                    temp.remove(index);
                }
                ans += max;
            }
        }
        return ans;
    }

    public int diff(int x, int y, int len) {
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (((1 << i) & x) == ((1 << i) & y)) {
                ans ++;
            }
        }
        return ans;
    }

    public int transToBinary(int [] answer) {
        int ans = 0;
        for (int i = answer.length - 1; i >= 0; i--) {
            int t = answer.length - i - 1;
            if (answer[i] == 1) {
                ans += 1 << t;
            }
        }
        return ans;
    }

    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 2) {
            return 0;
        }
        for (int i = 2; i < cost.length; i++) {
            int p = cost[i - 1];
            int q = cost[i - 2];
            cost[i] = cost[i] + Math.min(p, q);
        }
        return cost[cost.length - 1];
    }

    public int minCostClimbingStairsV(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }


    public static void main(String[] args) {

        //[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
        //输出：8
        int[][] stu = new int[][] {
                {1,1,0},
                {1,0,1},
                {0,0,1}
        };

        int[][] mea = new int[][] {
                {1,0,0},
                {0,0,1},
                {1,1,0}
        };

        int[] ans = new int[] {1,0,1,1};
        Day23 day23 = new Day23();
        Integer in = day23.transToBinary(ans);
        System.out.println(in);

        System.out.println(day23.minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
