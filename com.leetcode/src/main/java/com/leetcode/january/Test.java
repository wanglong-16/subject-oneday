package com.leetcode.january;

import java.util.List;

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
        SolutionThree solutionThree = new SolutionThree();

        //List<List<Integer>> ret = solutionTwo.permute(new int [] {1,2,3});
        System.out.println(solutionThree.isPalindrome("baceadae".toCharArray()));
        System.out.println(solutionThree.longestPalindrome("abaceadaebac"));
    }
}
