package com.leetcode.february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-23 09:49:31
 * @author: wanglong16@meicai.cn
 */
public class SolutionThirteen {

    /**
     * 922. 按奇偶排序数组 II
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * 你可以返回任何满足上述条件的数组作为答案。
     * 示例：
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     *
     * 提示：
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     */
    public int[] sortArrayByParityII(int[] A) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if ((A[i] & 1) == 0) {
                odds.add(A[i]);
            } else {
                evens.add(A[i]);
            }
        }
        Integer [] evenArr = evens.toArray(new Integer[] {});
        Integer [] oddArr = odds.toArray(new Integer[] {});
        Arrays.sort(evenArr);
        Arrays.sort(oddArr);
        int [] result = new int[A.length];
        for (int i = 0; i < evenArr.length; i++) {
            result[i * 2 + 1] = evenArr[i];
            result[i * 2] = oddArr[i];
        }
        return result;
    }
}
