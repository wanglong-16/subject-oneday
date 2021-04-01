package com.leetcode.march;

import java.util.*;

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

    /**
     * 942. 增减字符串匹配
     * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
     *
     * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
     *
     * 如果 S[i] == "I"，那么 A[i] < A[i+1]
     * 如果 S[i] == "D"，那么 A[i] > A[i+1]
     *
     *
     * 示例 1：
     *
     * 输入："IDID"
     * 输出：[0,4,1,3,2]
     * 示例 2：
     *
     * 输入："III"
     * 输出：[0,1,2,3]
     * 示例 3：
     *
     * 输入："DDI"
     * 输出：[3,2,0,1]
     */
    public int[] diStringMatch(String S) {
        int[] ans = new int[S.length() + 1];
        int left = 0, right = S.length();
        int cnt = 0;
        for (Character c : S.toCharArray()) {
            if (c == 'I') {
                ans[cnt] = left;
                left ++;
            } else {
                ans[cnt] = right;
                right --;
            }
            cnt ++;
        }
        ans[cnt] = left;
        return ans;
    }

    /**
     * 1380. 矩阵中的幸运数
     * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
     *
     * 幸运数是指矩阵中满足同时下列两个条件的元素：
     *
     * 在同一行的所有元素中最小
     * 在同一列的所有元素中最大
     *
     *
     * 示例 1：
     *
     * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
     * 输出：[15]
     * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * 示例 2：
     *
     * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
     * 输出：[12]
     * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
     * 示例 3：
     *
     * 输入：matrix = [[7,8],[1,2]]
     * 输出：[7]
     */
    public List<Integer> luckyNumbers (int[][] matrix) {
        int[][] rowMin = new int[matrix.length][2];
        int[][] colMin = new int[matrix[0].length][2];
        for (int i = 0; i < matrix.length; i++) {
            int min = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < matrix[i][min]) {
                    min = j;
                }
            }
            rowMin[i][0] = i;
            rowMin[i][1] = min;
        }

        for (int i = 0; i < matrix[0].length; i++) {
            //第i列
            int max = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] > matrix[max][i]) {
                    max = j;
                }
            }
            colMin[i][0] = max;
            colMin[i][1] = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] rp : rowMin) {
            for (int[] cp : colMin) {
                if (rp[0] == cp[0] && rp[1] == cp[1]) {
                    ans.add(matrix[rp[0]][rp[1]]);
                }
            }
        }
        return ans;
    }

    /**
     * 1002. 查找常用字符
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答案。
     *
     *
     *
     * 示例 1：
     *
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     *
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     *
     * 提示：
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * A[i][j] 是小写字母
     */
    public List<String> commonChars(String[] A) {
        List<Map<Character, Integer>> aMap = new ArrayList<>(A.length);
        Map<Character, Integer> map;
        for (String st : A) {
            map = new HashMap<>();
            for (Character cr : st.toCharArray()) {
                if (map.get(cr) != null) {
                    map.put(cr, map.get(cr) + 1);
                } else {
                    map.put(cr, 1);
                }
            }
            aMap.add(map);
        }
        List<String> ans = new ArrayList<>();
        for (Character e : aMap.get(0).keySet()) {
            int minCount = Integer.MAX_VALUE;
            boolean find = true;
            for (Map<Character, Integer> mp : aMap) {
                if (mp.get(e) == null) {
                    find = false;
                    break;
                } else {
                    minCount = Math.min(mp.get(e), minCount);
                }
            }
            if (find) {
                for (int i = 0; i < minCount; i++) {
                    ans.add(String.valueOf(e));
                }
            }
        }
        return ans;
    }

}
