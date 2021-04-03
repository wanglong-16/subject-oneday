package com.leetcode.april;

import com.leetcode.util.linked.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-04-02 17:58:13
 * @author: wanglong16@meicai.cn
 */
public class Day2 {

    /**
     * 1426. 数元素
     * 给你一个整数数组 arr， 对于元素 x ，只有当 x + 1 也在数组 arr 里时，才能记为 1 个数。
     *
     * 如果数组 arr 里有重复的数，每个重复的数单独计算。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [1,2,3]
     * 输出：2
     * 解释：1 和 2 被计算次数因为 2 和 3 在数组 arr 里。
     * 示例 2：
     *
     * 输入：arr = [1,1,3,3,5,5,7,7]
     * 输出：0
     * 解释：所有的数都不算, 因为数组里没有 2、4、6、8。
     * 示例 3：
     *
     * 输入：arr = [1,3,2,3,5,0]
     * 输出：3
     * 解释：0、1、2 被计算了因为 1、2、3 在数组里。
     * 示例 4：
     *
     * 输入：arr = [1,1,2,2]
     * 输出：2
     * 解释：两个 1 被计算了因为有 2 在数组里。
     */
    public int countElements(int[] arr) {
        int[] map = new int[1001];
        for (Integer in : arr) {
            map[in] ++;
        }
        int ans = 0;
        for (int i = 0; i < map.length - 1; i++) {
            if (map[i] >= 1 && map[i + 1] >= 1) {
                ans += map[i];
            }
        }
        return ans;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 示例1:
     *
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     *
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> occurred = new HashSet<Integer>();
        occurred.add(head.val);
        ListNode pos = head;
        // 枚举前驱节点
        while (pos.next != null) {
            // 当前待删除节点
            ListNode cur = pos.next;
            if (occurred.add(cur.val)) {
                pos = pos.next;
            } else {
                pos.next = pos.next.next;
            }
        }
        pos.next = null;
        return head;
    }

    /**
     * 1196. 最多可以买到的苹果数量
     * 楼下水果店正在促销，你打算买些苹果，arr[i] 表示第 i 个苹果的单位重量。
     *
     * 你有一个购物袋，最多可以装 5000 单位重量的东西，算一算，最多可以往购物袋里装入多少苹果。
     *
     *
     *
     * 示例 1：
     *
     * 输入：arr = [100,200,150,1000]
     * 输出：4
     * 解释：所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
     * 示例 2：
     *
     * 输入：arr = [900,950,800,1000,700,800]
     * 输出：5
     * 解释：6 个苹果的总重量超过了 5000，所以我们只能从中任选 5 个。
     *
     *
     * 提示：
     *
     * 1 <= arr.length <= 10^3
     * 1 <= arr[i] <= 10^3
     */
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int ans = 0, cur = 0;
        for (Integer in : arr) {
            if ((cur += in) < 5000) {
                ans ++;
            } else {
                break;
            }
        }
        return ans;
    }

    /**
     * 面试题 01.08. 零矩阵
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     *
     *
     * 示例 1：
     *
     * 输入：
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出：
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2：
     *
     * 输入：
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出：
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     */
    public void setZeroes(int[][] matrix) {
        List<Integer> rZ = new ArrayList<>();
        List<Integer> cZ = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rZ.add(i);
                    cZ.add(j);
                }
            }
        }
        for (Integer in : rZ) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[in][i] = 0;
            }
        }
        for (Integer in : cZ) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][in] = 0;
            }
        }
    }

    /**
     * 1160. 拼写单词
     * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
     *
     * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
     *
     * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
     *
     * 返回词汇表 words 中你掌握的所有单词的 长度之和。
     *
     *
     *
     * 示例 1：
     *
     * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
     * 输出：6
     * 解释：
     * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
     * 示例 2：
     *
     * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * 输出：10
     * 解释：
     * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
     *
     *
     * 提示：
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length, chars.length <= 100
     * 所有字符串中都仅包含小写英文字母
     */
    public int countCharacters(String[] words, String chars) {
        int[] ys = new int[26];
        for(Character c : chars.toCharArray()) {
            ys[c - 97] ++;
        }
        boolean spell;
        int ans = 0;
        int[] wordYs;
        for (String word : words) {
            spell = true;
            wordYs = new int[26];
            for (Character ch : word.toCharArray()) {
                if (wordYs[ch - 97] < ys[ch - 97]) {
                    wordYs[ch - 97] ++;
                } else {
                    spell = false;
                    break;
                }
            }
            if (spell) {
                ans += word.length();
            }
        }
        return ans;
    }
}
