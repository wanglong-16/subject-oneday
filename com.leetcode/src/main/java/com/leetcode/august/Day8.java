package com.leetcode.august;

import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.tools.javac.util.StringUtils;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-08-10 23:22:34
 * @author: wanglong16@meicai.cn
 */
public class Day8 {

    List<String> kh = new ArrayList<>();

    public List<String> generateParenthesisV1(int n) {
        backTracking(new StringBuilder(), 0, 0, n);
        return kh;
    }

    public void backTracking(StringBuilder sb, int left, int right, int half) {
        if (left + right == 2 * half) {
            kh.add(sb.toString());
        }
        if (left < half) {
            sb.append("(");
            backTracking(sb, left + 1, right, half);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            sb.append(")");
            backTracking(sb, left, right + 1, half);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << 2 * n; i++) {
            if (Integer.bitCount(i) == n) {
                int count = 0;
                boolean match = true;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 2 * n; j++) {
                    if ((i >>> j & 1) == 0) {
                        sb.append("(");
                        count ++;
                    } else {
                        sb.append(")");
                        count --;
                    }
                    if (count < 0) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    ans.add(sb.toString());
                }
            }
        }
        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            List<String> strings = map.getOrDefault(genHashStr(s), new ArrayList<>());
            strings.add(s);
            map.put(genHashStr(s), strings);
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> kv : map.entrySet()) {
            ans.add(kv.getValue());
        }
        return ans;
    }

    public String genHashStrV1(String string) {
        int[] chrArr = new int[26];
        for (Character ch : string.toCharArray()) {
            chrArr[ch - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (chrArr[i] > 0) {
                sb.append(i).append('a' + chrArr[i]);
            }
        }
        return sb.toString();
    }

    public String genHashStr(String string) {
        char[] chrArr = string.toCharArray();
        Arrays.sort(chrArr);
        return String.valueOf(chrArr);
    }

    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }

    public int[] relativeSortArrayV1(int[] arr1, int[] arr2) {
        Map<Integer, Integer> existBucket = new HashMap<>();
        Map<Integer, Integer> notExistBucket = new HashMap<>();
        for (Integer in : arr2) {
            existBucket.put(in, 0);
        }
        for (Integer in : arr1) {
            if (existBucket.get(in) != null) {
                existBucket.put(in, existBucket.get(in) + 1);
            } else {
                notExistBucket.merge(in, 1, Integer::sum);
            }
        }
        int [][] notExist = new int[notExistBucket.size()][2];
        if (notExistBucket.size() != 0) {
            int cnt = 0;
            for (Map.Entry e : notExistBucket.entrySet()) {
                notExist[cnt] = new int[] {Integer.parseInt(e.getKey().toString()), Integer.parseInt(e.getValue().toString())};
                cnt ++;
            }
            Arrays.sort(notExist, Comparator.comparingInt(a -> a[0]));
        }
        int [] ans = new int[arr1.length];
        int count = 0;
        for (int i = 0; i < arr2.length; i++) {
            int val = arr2[i], times = existBucket.get(val);
            for (int j = 1; j <= times; j++) {
                ans[count] = val;
                count ++;
            }
        }
        for (int i = 0; i < notExist.length; i++) {
            int val = notExist[i][0], times = notExist[i][1];
            for (int j = 1; j <= times; j++) {
                ans[count] = val;
                count ++;
            }
        }
        return ans;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        for (int a : arr1) {
            counts[a]++;
        }
        int index = 0;
        for (int a : arr2) {
            while (counts[a] > 0) {
                arr1[index++] = a;
                counts[a]--;
            }
        }
        for (int i = 0; i < 1001; i++) {
            while (counts[i] != 0) {
                arr1[index++] = i;
                counts[i]--;
            }
        }
        return arr1;
    }


    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for(Integer in : nums) {
            if (!set.add(in)) {
                return true;
            }
        }
        return false;
    }

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        System.out.println(day8.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }


}
