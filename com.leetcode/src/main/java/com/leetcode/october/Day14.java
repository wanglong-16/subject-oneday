package com.leetcode.october;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-24 10:17:10
 * @author: wanglong16@meicai.cn
 */
public class Day14 {

    public List<Integer> majorityElement(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }

    //仅由小写字母、连字符和/或标点（不含数字）。
    //至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
    //至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。

    public int countValidWords(String sentence) {
        String[] tokens = sentence.split(" ");
        int ret = 0;
        for (String token : tokens) {
            if (!"".equals(token) && isValidate(token)) {
                ret ++;
            }
        }
        return ret;
    }

    Set<Character> sz = new HashSet<Character>() {
        {
            add('1');add('2');add('3');add('4');add('5');add('6');add('7');add('8');add('9');add('0');
        }
    };
    Set<Character> bd = new HashSet<Character>() {
        {
            add('!');add(',');add('.');
        }
    };
    public boolean isValidate(String token) {
        int split_cnt = 0, split_idx = 0;
        int biaodianCnt = 0, bdIdx = 0;

        for (int i = 0; i < token.length(); i++) {
            if (sz.contains(token.charAt(i))) {
                return false;
            }
            if (token.charAt(i) == '-') {
                split_cnt ++;
                split_idx = i;
            }

            if (bd.contains(token.charAt(i))) {
                biaodianCnt ++;
                bdIdx = i;
            }
        }
        if (split_cnt == 0 && biaodianCnt == 0) {
            return true;
        } else if (split_cnt == 1 && biaodianCnt == 0) {
            return split_idx > 0 && split_idx < token.length() - 1 &&
                    'a' <= token.charAt(split_idx - 1) && token.charAt(split_idx - 1) <= 'z' &&
                    'a' <= token.charAt(split_idx + 1) && token.charAt(split_idx + 1) <= 'z';
        } else if (split_cnt == 0 && biaodianCnt == 1) {
            return bdIdx == token.length() - 1;
        } else if (split_cnt == 1 && biaodianCnt == 1) {
            return bdIdx == token.length() - 1 && split_idx > 0 && split_idx < token.length() - 1
            && 'a' <= token.charAt(split_idx + 1) && token.charAt(split_idx + 1) <= 'z'
                    && 'a' <= token.charAt(split_idx - 1) && token.charAt(split_idx - 1) <= 'z';
        } else {
            return false;
        }
    }


    //
    public int nextBeautifulNumber(int n) {
        int[] arr = new int[] {
                1, 22, 122, 212, 221, 333,
                1333, 3133, 3313, 3331, 4444,
                14444, 22333,23233,23323,23332,32233,32323,32332,33223,33232,33322,41444, 44144, 44414, 44441, 55555,
                122333,123233,123323,123332,132233,132323,132332,133223,133232,133322,212333,213233,213323,213332,221333,223133,223313,223331,231233,231323,231332,232133,232313,232331,233123,233132,233213,233231,233312,233321,312233,312323,312332,313223,313232,313322,321233,321323,321332,322133,322313,322331,323123,323132,323213,323231,323312,323321,331223,331232,331322,332123,332132,332213,332231,332312,332321,333122,333212,333221,
                224444,242444,244244,244424,244442,422444,424244,424424,424442,442244,442424,442442,444224,444242,444422,
                155555,515555,551555,555155,555515,555551, 666666,
                1224444
        };
        Arrays.sort(arr);
        //4 = 1 3, 4
        //5 = 2 3, 1 4, 5
        //6 = 1 2 3, 2 4, 1 5, 6

        //7 = 3 4, 1 2 4, 1 6, 2 5, 7

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > n) {
                return arr[i];
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        Day14 day14 = new Day14();
        final A a = new A(11);
        a.setBb(11);
        System.out.println(day14.majorityElement(new int[] {1, 1, 2, 3, 2}));
    }

    static class A {
        private int aa;
        private int bb;

        public A(int aa) {
            this.aa = aa;
        }

        public int getAa() {
            return aa;
        }

        public void setAa(int aa) {
            this.aa = aa;
        }

        public int getBb() {
            return bb;
        }

        public void setBb(int bb) {
            this.bb = bb;
        }
    }

}
