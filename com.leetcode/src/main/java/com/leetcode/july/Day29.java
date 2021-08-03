package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-02 21:25:08
 * @author: wanglong16@meicai.cn
 */
public class Day29 {

    Map<String, Integer> map;
    int[] parents;
    int[] rank;

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int len = 0;
        if (sentence1.length != sentence2.length) {
            return false;
        } else {
            len = sentence1.length;
        }
        Set<String> parentList = new HashSet<>(Arrays.asList(sentence1));
        parentList.addAll(Arrays.asList(sentence2));
        for (List<String> l : similarPairs) {
            parentList.addAll(l);
        }
        map = new HashMap<>(parentList.size() * 2);
        parents = new int[parentList.size()];
        rank = new int[parentList.size()];
        int i = 0;
        for (String str : parentList) {
            map.put(str, i);
            rank[i] = 1;
            parents[i++] = i - 1;
        }
        for (List<String> pair : similarPairs) {
            int x = map.get(pair.get(0)), y = map.get(pair.get(1));
            merge(x, y);
        }
        int[] sent1MainElement = new int[sentence1.length];
        int[] sent2MainElement = new int[sentence2.length];

        for (int j = 0; j < len; j++) {
            sent1MainElement[j] = find(map.get(sentence1[j]));
            sent2MainElement[j] = find(map.get(sentence2[j]));
        }

        Arrays.sort(sent1MainElement);
        Arrays.sort(sent2MainElement);
        for (int j = 0; j < len; j++) {
            if (sent1MainElement[j] != sent2MainElement[j]) {
                return false;
            }
        }

        return true;
    }

    public int find(int x) {
        return x == parents[x] ? x : (parents[x] = find(parents[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] <= rank[ry]) {
                parents[rx] = ry;
                if (rank[rx] == rank[ry]) {
                    rank[ry] ++;
                }
            } else {
                parents[ry] = rx;
            }
        }
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
            ans += dp[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Day29 day29 = new Day29();
        String[] sentence1 = new String[] {"great", "acting", "skills"};
        String[] sentence2 = new String[] {"fine", "drama", "talent"};
        List<List<String>> similarPairs = new ArrayList<>();
        similarPairs.add(Arrays.asList("great", "fine"));
        similarPairs.add(Arrays.asList("acting","drama"));
        similarPairs.add(Arrays.asList("skills","talent"));
        System.out.println(day29.areSentencesSimilarTwo(sentence1, sentence2, similarPairs));
    }


}
