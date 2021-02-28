package com.leetcode.february;

import com.leetcode.february.single.ListNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-25 23:38:10
 * @author: wanglong16@meicai.cn
 */
public class SolutionFifteen {

    /**
     * 1720. 解码异或后的数组
     * 未知 整数数组 arr 由 n 个非负整数组成。
     * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
     *
     * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
     *
     * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
     * 示例 1：
     *
     * 输入：encoded = [1,2,3], first = 1
     * 输出：[1,0,2,1]
     * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
     * 示例 2：
     *
     * 输入：encoded = [6,2,7,3], first = 4
     * 输出：[4,2,0,7,4]
     * 提示：
     *
     * 2 <= n <= 104
     * encoded.length == n - 1
     * 0 <= encoded[i] <= 105
     * 0 <= first <= 105
     */
    public int[] decode(int[] encoded, int first) {
        int [] result = new int[encoded.length + 1];
        result[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            result[i + 1] = encoded[i] ^ first;
            first = result[i + 1];
        }
        return result;
    }

    /**
     * 1290. 二进制链表转整数
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     * 请你返回该链表所表示数字的 十进制值 。
     * 示例 1：
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     * 示例 2：
     *
     * 输入：head = [0]
     * 输出：0
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：1
     * 示例 4：
     *
     * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * 输出：18880
     * 示例 5：
     *
     * 输入：head = [0,0]
     * 输出：0
     *
     *
     * 提示：
     *
     * 链表不为空。
     * 链表的结点总数不超过 30。
     * 每个结点的值不是 0 就是 1。
     */
    public int getDecimalValueV1(ListNode head) {
        List<Integer> bits = new ArrayList<>();
        while (head != null) {
            bits.add(head.val);
            head = head.next;
        }
        int ret = 0;
        if (!bits.isEmpty()) {
            for (int i = bits.size() - 1; i >= 0; i--) {
                ret += bits.get(i) * Math.pow(2, bits.size() - 1 - i);
            }
        }
        return ret;
    }

    public int getDecimalValue(ListNode head) {
        int ret = 0;
        while (head != null) {
            ret = (ret << 1) + head.val;
            head = head.next;
        }
        return ret;
    }

    /**
     * 1310. 子数组异或查询
     * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
     * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
     * 并返回一个包含给定查询 queries 所有结果的数组。
     * 示例 1：
     *
     * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
     * 输出：[2,7,14,8]
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001
     * 3 = 0011
     * 4 = 0100
     * 8 = 1000
     * 查询的 XOR 值为：
     * [0,1] = 1 xor 3 = 2
     * [1,2] = 3 xor 4 = 7
     * [0,3] = 1 xor 3 xor 4 xor 8 = 14
     * [3,3] = 8
     * 示例 2：
     *
     * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
     * 输出：[8,0,4,4]
     * 提示：
     *
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 10^9
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] < arr.length
     */
    public int[] xorQueriesV1(int[] arr, int[][] queries) {
        int [] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int xor = arr[queries[i][0]];
            int l = queries[i][0] + 1, r = queries[i][1];
            for (int j = l; j <= r; j++) {
                xor = xor ^ arr[j];
            }
            result[i] = xor;
        }
        return result;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] pre = new int[n + 1];
        //计算每个位置的前缀和 pre[i] 表示前i项的异或和
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] ^ arr[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int j = 0; j < queries.length; j++) {
            ans[j] = pre[queries[j][0]] ^ pre[queries[j][1] + 1];
        }
        return ans;
    }

    /**
     * 898. 子数组按位或操作
     * 我们有一个非负整数数组 A。
     *
     * 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B 中的每个元素进行按位或操作，获得结果 A[i] | A[i+1] | ... | A[j]。
     *
     * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
     * 示例 1：
     *
     * 输入：[0]
     * 输出：1
     * 解释：
     * 只有一个可能的结果 0 。
     * 示例 2：
     *
     * 输入：[1,1,2]
     * 输出：3
     * 解释：
     * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
     * 产生的结果为 1，1，2，1，3，3 。
     * 有三个唯一值，所以答案是 3 。
     * 示例 3：
     *
     * 输入：[1,2,4]
     * 输出：6
     * 解释：
     * 可能的结果是 1，2，3，4，6，以及 7 。
     */
    public int subarrayBitwiseORs(int[] arr) {
        //获取子数组
        if (arr.length <= 1) {
            return arr.length;
        }
        Set<Integer> ans = new HashSet<>();
        for (int i = 1; i < 1 << arr.length; i++) {
            int p = i, temp = 0;
            if (isSerie(p)) {
                for (int j = arr.length - 1; j >=0 ; j--) {
                    if ((p & 1) == 1) {
                        temp |= arr[j];
                    }
                    p >>= 1;
                }
                ans.add(temp);
            }
        }
        return ans.size();
    }

    //todo

    public boolean isSerie(int n) {
        boolean serie = true, startOne = false;
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                startOne = true;
            }
            if (startOne && (n & 1) == 0) {
                count ++;
            }
            n >>= 1;
        }
        return count == 1;
    }

    /**
     * 1125. 最小的必要团队
     * 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
     *
     * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。
     *
     * 我们可以用每个人的编号来表示团队中的成员：例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
     *
     * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按任意顺序返回答案，本题保证答案存在。
     * 示例 1：
     *
     * 输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
     * 输出：[0,2]
     * 示例 2：
     *
     * 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
     * 输出：[1,2]
     * 提示：
     *
     * 1 <= req_skills.length <= 16
     * 1 <= people.length <= 60
     * 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
     * req_skills 和 people[i] 中的元素分别各不相同
     * req_skills[i][j], people[i][j][k] 都由小写英文字母组成
     * 本题保证「必要团队」一定存在
     */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int bitLen = req_skills.length;
        Map<String, Integer> reqSkillsMap = new HashMap<>();
        int fullSkills = (1 << bitLen) - 1;
        for (int i = 0; i < bitLen; i++) {
            reqSkillsMap.put(req_skills[i], 1 << (bitLen - i - 1));
        }
        List<Integer> peopleBit = new ArrayList<>();
        for (List<String> team : people) {
            Integer teamBit = 0;
            for (String string : team) {
                teamBit += reqSkillsMap.get(string);
            }
            peopleBit.add(teamBit);
        }
        List<Integer> smallTeam = new ArrayList<>();
        int smallSkills = Integer.MAX_VALUE;
        for (int i = 1; i < 1 << peopleBit.size(); i++) {
            int p = i, temp = 0, teamTotalSkills = 0; // 团队总共使用的
            List<Integer> tempTeam = new ArrayList<>();
            for (int j = peopleBit.size() - 1; j >=0 ; j--) {
                if ((p & 1) == 1) {
                    temp |= peopleBit.get(j);
                    teamTotalSkills += peopleBit.get(j);
                    tempTeam.add(j);
                }
                p >>= 1;
            }
            if (temp == fullSkills) {
                if (teamTotalSkills < smallSkills) {
                    smallTeam = tempTeam;
                }
            }
        }
        int [] result = new int[smallTeam.size()];
        for (int i = 0; i < smallTeam.size(); i++) {
            result[i] = smallTeam.get(i);
        }
        return result;
    }


    /**
     * 171. Excel表列序号
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     *
     * 例如，
     *
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     * 示例 1:
     *
     * 输入: "A"
     * 输出: 1
     * 示例 2:
     *
     * 输入: "AB"
     * 输出: 28
     * 示例 3:
     *
     * 输入: "ZY"
     * 输出: 701
     * 致谢：
     * 特别感谢 @ts 添加此问题并创建所有测试用例。
     *
     * 通过次数62,381提交次数90,489
     */
    public int titleToNumber(String s) {
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int colNum = (s.charAt(i) - 64);
            result += colNum * Math.pow(26, s.length() - 1 - i);
        }
        return result;
    }

    public int titleToNumberV1(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }

    /**
     * 168. Excel表列名称
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     * 例如，
     *
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     * 示例 1:
     *
     * 输入: 1
     * 输出: "A"
     * 示例 2:
     *
     * 输入: 28
     * 输出: "AB"
     * 示例 3:
     *
     * 输入: 701
     * 输出: "ZY"
     */
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n -= 1;
            result.append((char) (n % 26 + 65));
            n /= 26;
        }
        return result.reverse().toString();
    }
}
