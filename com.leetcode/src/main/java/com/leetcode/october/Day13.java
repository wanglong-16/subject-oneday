package com.leetcode.october;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-20 19:22:35
 * @author: wanglong16@meicai.cn
 */
public class Day13 {


    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int total = 0;
        for (int num : nums) {
            total += num - min;
        }
        return total;
    }

    public int findLength(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            List<Integer> indexs = numIndexMap.getOrDefault(nums1[i], Collections.emptyList());
            List<Integer> list = new ArrayList<>(indexs);
            list.add(i);
            numIndexMap.put(nums1[i], list);
        }
        int ans = 0;
        for (int i = 0; i < nums2.length; i++) {
            int curNum = nums2[i];
            List<Integer> startIndexs = numIndexMap.get(curNum);
            if (startIndexs != null && !startIndexs.isEmpty()) {
                for (Integer startIndex : startIndexs) {
                    if (startIndex + ans < nums1.length
                            && i + ans < nums2.length && startIndex + ans < nums1.length
                            && nums2[i + ans] == nums1[startIndex + ans]) {
                        int cursor = 0;
                        while (i + cursor < nums2.length && startIndex + cursor < nums1.length) {
                            if (nums2[i + cursor] == nums1[startIndex + cursor]) {
                                cursor ++;
                            } else {
                                break;
                            }
                        }
                        ans = Math.max(ans, cursor);
                    }
                }
            }
        }
        return ans;
    }

    //[1,2,3,2,1]
    //[3,2,1,4,7]
    public static void main(String[] args) {
        Day13 day13 = new Day13();
        System.out.println(day13.findLength(new int[] {1,2,3,2,1}, new int[] {3,2,1,4,7}));
    }

    public class PropertyKeyConst {
        public static final String ENDPOINT = "endpoint";
        public static final String NAMESPACE = "namespace";
        public static final String ACCESS_KEY = "accessKey";
        public static final String SECRET_KEY = "secretKey";
        public static final String SERVER_ADDR = "serverAddr";
        public static final String CONTEXT_PATH = "contextPath";
        public static final String CLUSTER_NAME = "clusterName";
        public static final String ENCODE = "encode";

        public PropertyKeyConst() {
        }
    }

    public int[] plusOneV1(int[] digits) {
        int pos = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                pos = i;
                break;
            }
        }
        if (digits.length == 1) {
            return digits[0] == 9 ? new int[] {1, 0} : new int[] {digits[0] + 1};
        }
        if (pos == 0) {
            int[] ans;
            if (digits[pos] == 9) {
                ans = new int[digits.length + 1];
                ans[0] = 1;
            } else {
                ans = new int[digits.length];
                ans[0] = digits[0] + 1;
            }
            return ans;
        } else {
            digits[pos] += 1;
            return digits;
        }
    }

    public int[] plusOne(int[] digits) {
        boolean nextOne = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == 0 && digits[0] == 9 && nextOne || digits.length == 1 && digits[0] == 9) {
                int [] ret = new int [digits.length + 1];
                ret[0] = 1;
                return ret;
            }
            int curr = digits[i];
            if (nextOne || i == digits.length - 1) {
                curr ++;
                nextOne = curr > 9;
                digits [i] = curr % 10;
            }
        }
        return digits;
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            int start = i;
            while (start < s.length() && set.add(s.charAt(start)) ) {
                start ++;
            }
            max = Math.max(start - i, max);
            set.clear();
        }
        return max;
    }

}
