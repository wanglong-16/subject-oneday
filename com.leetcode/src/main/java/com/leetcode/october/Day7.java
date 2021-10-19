package com.leetcode.october;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-09 17:06:34
 * @author: wanglong16@meicai.cn
 */
public class Day7 {


    class SummaryRanges {

        boolean[] arr = new boolean[10001];

        public SummaryRanges() {
        }

        public void addNum(int val) {
            arr[val] = true;
        }

        public int[][] getIntervals() {
            List<int[]> ans = new ArrayList<>();
            int first = -1, last = -1;
            for (int i = 0; i < 10001; i++) {
                if (arr[i]) {
                    if (first == -1) {
                        first = i;
                    }
                    last = i;
                } else {
                    if (first != -1) {
                        ans.add(new int[]{first, last});
                        first = -1;
                        last = -1;
                    }
                }
            }
            ans.add(new int[]{first, last});
            return ans.toArray(new int[ans.size()][2]);
        }
    }


    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (Character c : s.toCharArray()) {
            sb.append(c == ' ' ? "%20" : c);
        }
        return sb.toString();
    }

    public boolean findNumberIn2DArrayV1(int[][] matrix, int target) {
        int rs = 0, cs = 0, re = matrix.length - 1, ce = matrix[0].length - 1;
        if (matrix[rs][cs] == target || matrix[re][ce] == target) {
            return true;
        }
        while (rs < re || cs < ce) {
            int rm = (rs + re) >>> 1;
            int cm = (cs + ce) >>> 1;
            int start = matrix[rs][cs];
            int end = matrix[re][ce];
            int mid = matrix[rm][cm];
            if (mid == target) {
                return true;
            } else if (target < mid) {
                re = rm;
                ce = cm;
            } else {
                rs = rm;
                cs = cm;
            }
            if (rs == re - 1 || cs == ce - 1) {
                break;
            }
        }
        for (int i = 0; i <= ce; i++) {
            if (matrix[re][i] == target) {
                return true;
            }
        }
        for (int i = 0; i <= re; i++) {
            if (matrix[i][ce] == target) {
                return true;
            }
        }
        for (int i = cs; i < matrix[0].length; i++) {
            if (matrix[rs][i] == target) {
                return true;
            }
        }
        for (int i = rs; i < matrix.length; i++) {
            if (matrix[i][cs] == target) {
                return true;
            }
        }
        return false;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int cols = matrix[0].length;
        int rows = matrix.length;
        int row = 0, col = cols - 1;
        while (col >= 0 && row < rows) {
            int cur = matrix[row][col];
            if (cur == target) {
                return true;
            } else if (cur > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public int arrangeCoins(int n) {
        int row = 1, total = 1;
        while (total < n) {
            total += row;
            row++;
        }
        return row;
    }

    public List<List<Integer>> threeSumV1(int[] nums) {
        Set<Integer> nag = new HashSet<>();
        Set<Integer> pos = new HashSet<>();
        int zeroCnt = 0;
        List<Integer> nagList = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();
        for (int num : nums) {
            if (num == 0) {
                zeroCnt++;
            } else if (num > 0) {
                pos.add(num);
                posList.add(num);
            } else {
                nag.add(num);
                nagList.add(num);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.addAll(checkListSet(nagList, pos));
        ans.addAll(checkListSet(posList, nag));
        if (zeroCnt > 0) {
            ans.addAll(checkListSetWithZero(new ArrayList<>(nag), pos));
        }
        if (zeroCnt >= 3) {
            ans.add(Arrays.asList(0, 0, 0));
        }
        return ans;
    }

    private List<List<Integer>> checkListSet(List<Integer> list, Set<Integer> set) {
        Set<List<Integer>> ans = new HashSet<>();
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ii = list.get(i);
                int jj = list.get(j);
                if (set.contains(-(ii + jj))) {
                    ans.add(Arrays.asList(ii, jj, -(ii + jj)));
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private List<List<Integer>> checkListSetWithZero(List<Integer> list, Set<Integer> set) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int ii : list) {
            if (set.contains(-(ii))) {
                ans.add(Arrays.asList(ii, 0, -ii));
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return ans;
        }
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++; // 去重
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 3, 8, 13, 13, 18},
                {4, 5, 11, 13, 18, 20},
                {9, 9, 14, 15, 23, 23},
                {13, 18, 22, 22, 25, 27},
                {18, 22, 23, 28, 30, 33},
                {21, 25, 28, 30, 35, 35},
                {24, 25, 33, 36, 37, 40}
        };

        int[][] mt = new int[][]{
                {1, 3, 5}
        };
        Day7 day7 = new Day7();
        System.out.println(day7.findNumberIn2DArray(matrix, 21));
    }


/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
}
