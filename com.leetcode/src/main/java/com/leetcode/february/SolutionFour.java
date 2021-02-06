package com.leetcode.february;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-06 16:17:18
 * @author: wanglong16@meicai.cn
 */
public class SolutionFour {

    /**
     * 349. 两个数组的交集
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     *
     *
     * 说明：
     *
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> unique = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        if (nums1.length < nums2.length) {
            for (Integer i : nums1) {
                unique.add(i);
            }
            for (Integer integer: nums2) {
                if (unique.contains(integer)) {
                    result.add(integer);
                }
            }
        } else {
            for (Integer i : nums2) {
                unique.add(i);
            }
            for (Integer integer: nums1) {
                if (unique.contains(integer)) {
                    result.add(integer);
                }
            }
        }
        int [] ret = new int [result.size()];
        List<Object> objects = Arrays.asList(result.toArray());
        for (int i = 0; i < result.size(); i++) {
            ret[i] = Integer.parseInt(objects.get(i).toString());
        }
        return ret;
    }
}
