package com.leetcode.march;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-29 08:19:58
 * @author: wanglong16@meicai.cn
 */
public class Day30 {

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例：
     *
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *
     *
     * 提示：
     *
     * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     */
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb, ans = new StringBuilder();
        for (String st : strings) {
            sb = new StringBuilder(st).reverse();
            ans.append(sb).append(" ");
        }
        return ans.delete(ans.length() - 1, ans.length()).toString();
    }

    /**
     * 1700. 无法吃午餐的学生数量
     * 学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。
     * 所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
     * 餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 栈 里，每一轮：
     *
     * 如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。
     * 否则，这名学生会 放弃这个三明治 并回到队列的尾部。
     * 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
     *
     * 给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i​​​​​​ 个三明治的类型（i = 0 是栈的顶部）， students[j] 是初始队列里第 j​​​​​​ 名学生对三明治的喜好（j = 0 是队列的最开始位置）。
     * 请你返回无法吃午餐的学生数量。
     *
     *
     *
     * 示例 1：
     *
     * 输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
     * 输出：0
     * 解释：
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
     * - 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
     * - 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
     * 所以所有学生都有三明治吃。
     * 示例 2：
     *
     * 输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
     * 输出：3
     *
     *
     * 提示：
     *
     * 1 <= students.length, sandwiches.length <= 100
     * students.length == sandwiches.length
     * sandwiches[i] 要么是 0 ，要么是 1 。
     * students[i] 要么是 0 ，要么是 1 。
     */
    public int countStudents(int[] students, int[] sandwiches) {
        int stu0cnt = 0, stu1cnt = 0, sand0cnt = 0, sand1cnt = 0;
        for (int student : students) {
            if (student == 0) {
                stu0cnt++;
            } else {
                stu1cnt++;
            }
        }
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                sand0cnt++;
            } else {
                sand1cnt++;
            }
        }
        if (sand0cnt >= stu0cnt && sand1cnt >= stu1cnt) {
            return 0;
        }
        return stu0cnt - sand0cnt + stu1cnt - sand1cnt;
    }
}
