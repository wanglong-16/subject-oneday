package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-18 10:03:00
 * @author: wanglong16@meicai.cn
 */
public class Day18 {

    public String toGoatLatin(String sentence) {
        StringBuilder ans = new StringBuilder();
        String[] template = sentence.split(" ");
        List<Character> yuanyin = Arrays.asList('a','e','i','o','u','A','E','I','O','U');
        StringBuilder indexA = new StringBuilder();
        for (String str : template) {
            indexA.append("a");
            if (yuanyin.contains(str.charAt(0))) {
                ans.append(str).append("ma").append(indexA);
            } else {
                ans.append(str.substring(1)).append(str.charAt(0)).append("ma").append(indexA);
            }
            ans.append(" ");
        }
        return ans.deleteCharAt(ans.length() - 1).toString();
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        String[] temp = text.split(" ");
        int ans = 0;
        for (String str : temp) {
            boolean ok = true;
            for (Character c : str.toCharArray()) {
                if (brokenLetters.contains("" + c)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans ++;
            }
        }
        return ans;
    }

    public int addRungs(int[] rungs, int dist) {
        int curr = 0, last = rungs[rungs.length - 1], index = 0;
        int ans = 0;
        while (curr < last) {
            if (curr + dist < rungs[index]) {
                int diff = rungs[index] - curr;
                ans += diff % dist == 0 ? (diff / dist) - 1 : diff / dist;
            }
            curr = rungs[index];
            index ++;
        }
        return ans;
    }

    public long maxPointsV1(int[][] points) {
        int rows = points.length, cols = points[0].length;
        long [][] temp = new long[rows][cols];
        arr = new long[cols];
        for (int i = 0; i < cols; i++) {
            temp[0][i] = points[0][i];
        }
        for (int i = 1; i < rows; i++) {
            calculateNextMaxPoints(points[i], temp[i - 1]);
        }
        long max = 0;
        for (int i = 0; i < cols; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    long[] arr;

    public void calculateNextMaxPoints(int[] tem, long[] last) {
        for (int i = 0; i < tem.length; i++) {
            for (int j = 0; j < tem.length; j++) {
                int diff = tem[j] - Math.abs(j - i);
                if (diff <= 0) {
                    break;
                } else {
                    long cur = Math.max(arr[i], diff);
                    for (int k = 0; k < tem.length; k++) {
                        arr[i] = Math.max(cur + last[k], cur);
                    }
                }
            }
        }
    }

    public class SegmentTree {
        long[] seg;
        long[] lazy;

        public SegmentTree(long[] a, int n) {
            seg = new long[4 * n];
            lazy = new long[4 * n];
            build(a, 0, n - 1, 0);
        }

        void build(long[] a, int begin, int end, int si) {
            if (begin == end) {
                seg[si] = a[begin];
                return;
            }
            int mid = (begin + end) >> 1;
            build(a, begin, mid, si * 2 + 1);
            build(a, mid + 1, end, si * 2 + 2);
            seg[si] = Math.max(seg[si * 2 + 1], seg[si * 2 + 2]);
        }

        void pushDown(int begin, int end, int si) {
            if (lazy[si] != 0) {
                long mid = (begin + end) >> 1;
                lazy[si * 2 + 1] += lazy[si];
                lazy[si * 2 + 2] += lazy[si];
                seg[si * 2 + 1] += lazy[si];
                seg[si * 2 + 2] += lazy[si];
                lazy[si] = 0;
            }
        }

        long queryRange(int begin, int end, int si, long l, long r) {
            if (l <= begin && end <= r) {
                return seg[si];
            }
            pushDown(begin, end, si);
            int mid = (begin + end) >> 1;
            long result = Long.MIN_VALUE;
            if (l <= mid) result = Math.max(result, queryRange(begin, mid, si * 2 + 1, l, r));
            if (r > mid) result = Math.max(result, queryRange(mid + 1, end, si * 2 + 2, l, r));
            return result;
        }

        void updateRange(int begin, int end, int si, long us, long ue, long diff) {
            if (us <= begin && end <= ue) {
                lazy[si] += diff;
                seg[si] += diff;
                return;
            }
            pushDown(begin, end, si);
            int mid = (begin + end) >> 1;
            if (us <= mid) {
                updateRange(begin, mid, si * 2 + 1, us, ue, diff);
            }
            if (ue > mid) {
                updateRange(mid + 1, end, si * 2 + 2, us, ue, diff);
            }
            seg[si] = Math.max(seg[si * 2 + 1], seg[si * 2 + 2]);
        }
    }


    public long maxPoints(int[][] polongs) {
        int m = polongs.length, n = polongs[0].length;
        long[][] dp = new long[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = polongs[0][i];
        }
        for (int i = 1; i < m; i++) {
            long[] a = new long[n];
            System.arraycopy(dp[i - 1], 0, a, 0, n);
            for (int k = 0; k < n; k++) {
                a[k] -= k;
            }
            SegmentTree segtree = new SegmentTree(a, n);
            dp[i][0] = segtree.queryRange(0, n - 1, 0, 0, n - 1) + polongs[i][0];
            for (int j = 1; j < n; j++) {
                segtree.updateRange(0, n - 1, 0, 0, j - 1, -1);
                segtree.updateRange(0, n - 1, 0, j, n - 1, +1);
                dp[i][j] = segtree.queryRange(0, n - 1, 0, 0, n - 1) + polongs[i][j];
            }
        }
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[m - 1][i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Day18 day18 = new Day18();
        int[][] tem = new int[][] {{1,5},{2,3},{4,2}};
        System.out.println(day18.maxPoints(tem));
    }
}
