package com.leetcode.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-03 09:08:36
 * @author: wanglong16@meicai.cn
 */
public class Day2 {


    public int[][] construct2DArray(int[] original, int m, int n) {
        if (m * n != original.length) {
            return new int[][] {{}};
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[i * n + j];
            }
        }
        return ans;
    }

    public int numOfPairs(String[] nums, String target) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i != j) {
                    if (String.format("%s%s", nums[i], nums[j]).equals(target)) {
                        cnt ++;
                    }
                    if (String.format("%s%s", nums[j], nums[i]).equals(target)) {
                        cnt ++;
                    }
                }
            }
        }
        return cnt;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        List<Integer> ansList = new ArrayList<>();
        if (answerKey.length() == 0) {
            return 0;
        }
        char first = answerKey.charAt(0), last = answerKey.charAt(0);
        int cnt = 0;
        for (int i = 0; i < answerKey.length(); i++) {
            if (last != answerKey.charAt(i)) {
                ansList.add(cnt);
                cnt = 0;
                last = answerKey.charAt(i);
            }
            cnt ++;
        }
        ansList.add(cnt);


        return cnt;
    }

    public int minimumMoves(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('X' == s.charAt(i)) {
                ans ++;
                i += 2;
            }
        }
        return ans;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int total = mean * (rolls.length + n) - Arrays.stream(rolls).sum();
        // n 个数 总和 = total
        if (total < n || total > n * 6) {
            return new int[] {};
        }
        int dem = total / n, rem = total % n;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < rem) {
                ans[i] = dem + 1;
            } else {
                ans[i] = dem;
            }
        }
        return ans;
    }

    public String licenseKeyFormattingV1(String s, int k) {
        List<Character> characters = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                char chr = Character.toUpperCase(s.charAt(i));
                characters.add(chr);
            }
        }
        if (characters.size() == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        int len = characters.size(), rem = characters.size() / k;
        for (int i = 0; i < rem; i++) {
            for (int j = 0; j < k; j++) {
                char chr = characters.get(i * k + j);
                ans.append(chr);
            }
            ans.append('-');
        }
        for (int i = rem * k; i < len; i++) {
            ans.append(characters.get(i));
        }
        if (ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                cnt++;
                ans.append(Character.toUpperCase(s.charAt(i)));
            }
            if (cnt % k == 0) {
                ans.append("-");
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }


    public static void main(String[] args) {
        Day2 day2 = new Day2();
        System.out.println(day2.licenseKeyFormatting("2-5g-3-J", 2));
    }
}
