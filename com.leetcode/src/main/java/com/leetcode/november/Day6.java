package com.leetcode.november;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-11-17 11:41:01
 * @author: wanglong16@meicai.cn
 */
public class Day6 {

    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) {
            if (cnt.containsKey(key + 1)) {
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }

    //["e","g","f","i","h","o","n","s","r","u","t","w","v"]
    public String originalDigits(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int[] num = new int[10];
        for (int i = cnt.length - 1; i >= 0; i--) {
            if (cnt[i] > 0) {
                if (i == 'z' - 'a') {
                    num[0] = cnt[i];
                } else if (i == 'w' - 'a') {
                    num[2] = cnt[i];
                } else if (i == 'x' - 'a') {
                    num[6] = cnt[i];
                } else if (i == 'g' - 'a') {
                    num[8] = cnt[i];
                } else if (i == 'o' - 'a') {
                    num[1] = Math.min(cnt[i], cnt['n' - 'a']);
                } else if (i == 't' - 'a') {
                    num[3] = Math.min(cnt[i], cnt['h' - 'a']);
                } else if (i == 'f' - 'a') {
                    num[5] = Math.min(cnt[i], cnt['v' - 'a']);
                } else if (i == 's' - 'a') {
                    num[7] = Math.min(cnt[i], cnt['e' - 'a']);
                } else if (i == 'i' - 'a') {
                    num[9] = Math.min(cnt[i], cnt['e' - 'a']);
                } else if (i == 'r' - 'a') {
                    num[4] = Math.min(cnt[i], cnt['u' - 'a']);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0) {
                for (int j = 0; j < num[i]; j++) {
                    sb.append(i);
                }
            }
        }
        return sb.toString();

    }

    public boolean buddyStrings(String s, String goal) {
        List<Integer> diffIdx = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                diffIdx.add(i);
            }
        }
        return diffIdx.size() == 2 &&
                s.charAt(diffIdx.get(0)) == goal.charAt(diffIdx.get(1)) &&
                s.charAt(diffIdx.get(1)) == goal.charAt(diffIdx.get(0));
    }


    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> cnt1 = new HashMap<>(), cnt2 = new HashMap<>();
        for (String s : words1) {
            cnt1.put(s, cnt1.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            cnt2.put(s, cnt2.getOrDefault(s, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<String, Integer> c1 : cnt1.entrySet()) {
            if (c1.getValue() == 1 && cnt2.get(c1.getKey()) != null && cnt2.get(c1.getKey()) == 1) {
                ans++;
            }
        }
        return ans;
    }

    public int maxDistance(int[] colors) {
        int left = colors[0], right = colors[colors.length - 1];
        int max = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] != colors[right]) {
                max = Math.max(right - i, max);
                break;
            }
        }
        for (int i = colors.length - 1; i >= 0; i++) {
            if (colors[i] != colors[left]) {
                max = Math.max(i - left, max);
                break;
            }
        }
        return max;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.length() > s.length()) {
            return ans;
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            String temp = s.substring(i, i + p.length());
            if (isEV(temp, p)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean isEV(String source, String target) {
        int[] sCnt = new int[26], tCnt = new int[26];
        for (int i = 0; i < source.length(); i++) {
            sCnt[source.charAt(i) - 'a']++;
            tCnt[target.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sCnt[i] != tCnt[i]) {
                return false;
            }
        }
        return true;
    }

    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int sLen = s.length(), pLen = p.length();

            if (sLen < pLen) {
                return new ArrayList<>();
            }

            List<Integer> ans = new ArrayList<>();
            int[] sCount = new int[26];
            int[] pCount = new int[26];
            for (int i = 0; i < pLen; ++i) {
                ++sCount[s.charAt(i) - 'a'];
                ++pCount[p.charAt(i) - 'a'];
            }

            if (Arrays.equals(sCount, pCount)) {
                ans.add(0);
            }

            for (int i = 0; i < sLen - pLen; ++i) {
                --sCount[s.charAt(i) - 'a'];
                ++sCount[s.charAt(i + pLen) - 'a'];

                if (Arrays.equals(sCount, pCount)) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }

    public int[] getAverages(int[] nums, int k) {
        int[] res = new int[nums.length];
        long sum = 0L;
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            sum += nums[i];
            if (i >= 2 * k) {
                res[i - k] = (int) (sum / (2 * k + 1));
                sum -= nums[i - 2 * k];
            }
        }
        return res;
    }

    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int minimumDeletions(int[] nums) {
        int maxIdx = 0, minIdx = 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIdx = i;
            }
            if (min > nums[i]) {
                min = nums[i];
                minIdx = i;
            }
        }
        int L = Math.min(minIdx, maxIdx);
        int R = Math.max(minIdx, maxIdx);
        return Math.min(Math.min(R + 1, nums.length - L), L + 1 + nums.length - R);
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(Arrays.toString(day6.getAverages(new int[]{

        }, 3)));
    }

}
