package com.leetcode.january;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-01-20 22:51:55
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        SolutionOne solutionOne = new SolutionOne();
//        System.out.println(solutionOne.maximumProduct(new int[] {1,2,3}));

        //System.out.println(solutionOne.generateParenthesis(3).toString());

        SolutionTwo solutionTwo = new SolutionTwo();
        int ret = solutionTwo.findLengthOfLCIS(new int [] {1,3,5,7});
        System.out.println(ret);
    }
}
