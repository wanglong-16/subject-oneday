package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-01 10:15:26
 * @author: wanglong16@meicai.cn
 */
public class Day28 {

    public Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("aa", 1);
        }
    };


    public boolean isThree(int n) {
        if (n <= 3) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return sqrt * sqrt == n;
    }

    public long numberOfWeeksV1(int[] milestones) {
        if (milestones.length == 1) {
            return 1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (Integer in : milestones) {
            queue.offer(in);
        }
        long ans = 0;
        while (queue.size() >= 4) {
            int first = queue.poll(), second = queue.poll();
            ans += second * 2;
            if (first != second) {
                queue.offer(first - second);
            }
        }
        if (!queue.isEmpty()) {
            if (queue.size() == 3) {
                int first = queue.poll(), second = queue.poll(), third = queue.poll();
                if (second + third >= first) {
                    ans += first + second + third;
                } else {
                    ans += (third + second) * 2 + 1;
                }
            } else if (queue.size() == 2) {
                int first = queue.poll(), second = queue.poll();
                if (second == first) {
                    ans += second + first;
                } else {
                    ans += second * 2 + 1;
                }
            } else {
                ans++;
            }
        }
        return ans;
    }


    public long numberOfWeeks(int[] ms) {
        Arrays.sort(ms);
        long total = 0;
        for (int x : ms) {
            total += x;
        }
        int n = ms.length;
        long max = ms[n - 1];
        if (max + max - 1 <= total) {
            return total;
        }
        return (total - max) * 2 + 1;
    }


    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {},
                {},
                {}
        };
        Day28 day28 = new Day28();
        //[5,9,4,4,8,9,9,8,7,3] 67 66

        // [4,5,5,2]  15 16
        System.out.println(day28.numberOfWeeks(new int[]{4, 5, 5, 2}));
    }


    public long minimumPerimeter(long neededApples) {
        long total = 0, index = 1;
        while (total < neededApples) {
            total += calculateIndex(index);
            index++;
        }
        return (index - 1) * 8;
    }

    public long calculateIndex(long index) {
        long ans = 0;

        return 12 * index * index;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        for (int i = 0; i < mat.length; i++) {
            mat[i][1] = i;
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][0] += mat[i][j] * j;
            }
        }
        Arrays.sort(mat, Comparator.comparingInt(a -> a[0]));
        int [] ans = new int[k];
        for (int i = 0; i < mat.length; i++) {
            ans[i] = mat[i][1];
        }
        return ans;
    }


    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

}
