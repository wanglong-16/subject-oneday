package com.leetcode.march.single;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-02 06:45:11
 * @author: wanglong16@meicai.cn
 */
class SparseVector {

    int [] numArr;

    SparseVector(int[] nums) {
        numArr = nums;
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        for (int i = 0; i < numArr.length; i++) {
            result += numArr[i] * vec.numArr[i];
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
