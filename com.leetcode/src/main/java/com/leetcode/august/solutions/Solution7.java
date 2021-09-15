package com.leetcode.august.solutions;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-05 21:28:29
 * @author: wanglong16@meicai.cn
 */
import java.util.*;

public class Solution7 {


    // 左转 L 右转 R
    // 前 F   后 B
    // 顺 C   逆 A
    static int[] init = new int[] {
            1, 2, 3, 4, 5, 6
    };
    // int[] {左，右，前，后，上，下}
    //        0   1  2  3  4  5


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lineOne = sc.nextLine();
        for (Character c : lineOne.toCharArray()) {
            switch (c) {
                case 'L':
                    L(init);
                    break;
                case 'R':
                    R(init);
                    break;
                case 'F':
                    F(init);
                    break;
                case 'B':
                    B(init);
                    break;
                case 'A':
                    A(init);
                    break;
                case 'C':
                    C(init);
                    break;
                default:
                    break;
            }
        }
        for (Integer in : init) {
            System.out.print(in);
        }
    }

    // int[] {左，右，前，后，上，下}
    //        0   1  2  3  4  5

    //左翻滚，
    // 左-下、下-右、右-上、上-左，
    static int[] L(int[] arr) {
        int temp = arr[0];
        arr[0] = arr[5];
        arr[5] = arr[1];
        arr[1] = arr[4];
        arr[4] = temp;
        return arr;
    }

    // int[] {左，右，前，后，上，下}
    //        0   1  2  3  4  5

    // 右-下、下-左、左-上、上-右、
    static int[] R(int[] arr) {
        int temp = arr[1];
        arr[1] = arr[5];
        arr[5] = arr[0];
        arr[0] = arr[4];
        arr[4] = temp;
        return arr;
    }

    // int[] {左，右，前，后，上，下}
    //        0   1  2  3  4  5
    //        1   2  3  4  5  6
    // 前-下、下-后、后-上、上-前

    static int[] F(int[] arr) {
        int temp = arr[2];
        arr[2] = arr[5];
        arr[5] = arr[3];
        arr[3] = arr[4];
        arr[4] = temp;
        return arr;
    }


    // 前-上、上-后、后-下、下-前
    static int[] B(int[] arr) {
        int temp = arr[2];
        arr[2] = arr[4];
        arr[4] = arr[3];
        arr[3] = arr[5];
        arr[5] = temp;
        return arr;
    }

    // int[] {左，右，前，后，上，下}
    //        0   1  2  3  4  5


    // 前-右、右-后、后-左、左-前
    static int[] A(int[] arr) {
        int temp = arr[2];
        arr[2] = arr[1];
        arr[1] = arr[3];
        arr[3] = arr[0];
        arr[0] = temp;
        return arr;
    }

    // 前-左、左-后、后-右、右-前
    static int[] C(int[] arr) {
        int temp = arr[2];
        arr[2] = arr[0];
        arr[0] = arr[3];
        arr[3] = arr[1];
        arr[1] = temp;
        return arr;
    }

}
