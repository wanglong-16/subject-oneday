package com.leetcode.march;

import com.leetcode.util.linked.ListNode;
import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-11 08:11:46
 * @author: wanglong16@meicai.cn
 */
public class Day9 {

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     * 给你 root1 和 root2 这两棵二叉搜索树。
     *
     * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
     * 示例 1：
     * 输入：root1 = [2,1,4], root2 = [1,0,3]
     * 输出：[0,1,1,2,3,4]
     * 示例 2：
     *
     * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
     * 输出：[-10,0,0,1,2,5,7,10]
     * 示例 3：
     *
     * 输入：root1 = [], root2 = [5,1,7,0,2]
     * 输出：[0,1,2,5,7]
     * 示例 4：
     *
     * 输入：root1 = [0,-10,10], root2 = []
     * 输出：[-10,0,10]
     * 示例 5：
     * 输入：root1 = [1,null,8], root2 = [8,1]
     * 输出：[1,1,8,8]
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> arr = new ArrayList<>();
        visitTree(arr, root1);
        visitTree(arr,root2);
        Collections.sort(arr);
        return arr;
    }

    private void visitTree(List<Integer> vals, TreeNode treeNode) {
        if (treeNode != null) {
            vals.add(treeNode.val);
            visitTree(vals, treeNode.left);
            visitTree(vals, treeNode.right);
        }
    }

    /**
     * 1502. 判断能否形成等差数列
     * 给你一个数字数组 arr 。
     *
     * 如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
     *
     * 如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：arr = [3,5,1]
     * 输出：true
     * 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
     * 示例 2：
     *
     * 输入：arr = [1,2,4]
     * 输出：false
     * 解释：无法通过重新排序得到等差数列。
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        if (arr.length <= 2) {
            return true;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != arr[1] - arr[0]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1636. 按照频率将数组升序排序
     * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
     * 请你返回排序后的数组。
     * 示例 1：
     *
     * 输入：nums = [1,1,2,2,2,3]
     * 输出：[3,1,1,2,2,2]
     * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
     * 示例 2：
     *
     * 输入：nums = [2,3,1,3,2]
     * 输出：[1,3,3,2,2]
     * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
     * 示例 3：
     *
     * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
     * 输出：[5,-1,4,4,-6,-6,1,1,1]
     * 提示：
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> buckets = new HashMap<>(); // 0 = -100
        for (int num : nums) {
            buckets.merge(num, 1, Integer::sum);
        }
        List<BucketItem> items = new ArrayList<>();
        for (Map.Entry entry : buckets.entrySet()) {
            items.add(new BucketItem(Integer.parseInt(entry.getKey().toString()), Integer.parseInt(entry.getValue().toString())));
        }
        Collections.sort(items);
        int [] ans = new int[nums.length];
        int count = 0;
        for (BucketItem i : items) {
            for (int j = 0; j < i.freq; j++) {
                ans[count + j] = i.num;
            }
            count += i.freq;
        }
        return ans;
    }

    public class BucketItem implements Comparable<BucketItem> {

        public int num;
        public int freq;

        public BucketItem(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(BucketItem o) {
            return this.freq == o.freq ? o.num - this.num : o.freq - this.freq;
        }
    }

    /**
     * 1710. 卡车上的最大单元数
     * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
     *
     * numberOfBoxesi 是类型 i 的箱子的数量。
     * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
     * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
     *
     * 返回卡车可以装载 单元 的 最大 总数。
     * 示例 1：
     *
     * 输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
     * 输出：8
     * 解释：箱子的情况如下：
     * - 1 个第一类的箱子，里面含 3 个单元。
     * - 2 个第二类的箱子，每个里面含 2 个单元。
     * - 3 个第三类的箱子，每个里面含 1 个单元。
     * 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
     * 单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
     * 示例 2：
     *
     * 输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
     * 输出：91
     */
    public int maximumUnitsV1(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int maxUnits = 0, currentSize = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            if (currentSize + boxTypes[i][0] >= truckSize) {
                // 装不下全部
                for (int j = 0; j < boxTypes[i][0]; j++) {
                    currentSize ++;
                    maxUnits += boxTypes[i][1];
                    if (currentSize == truckSize) {
                        return maxUnits;
                    }
                }
            } else {
                currentSize += boxTypes[i][0];
                maxUnits += boxTypes[i][0] * boxTypes[i][1];
            }
        }
        return maxUnits;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> (o2[1] - o1[1]));
        int ans = 0;
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int num = Math.min(truckSize, boxTypes[i][0]);
            truckSize -= num;
            ans += num * boxTypes[i][1];
        }
        return ans;
    }

    /**
     * 1772. 按受欢迎程度排列功能
     * 给定一个字符串数组 features ，其中 features[i] 是一个单词，描述你最近参与开发的项目中一个功能的名称。你调查了用户喜欢哪些功能。另给定一个字符串数组 responses，其中 responses[i] 是一个包含以空格分隔的一系列单词的字符串。
     *
     * 你想要按照受欢迎程度排列这些功能。 严格地说，令 appearances(word) 是满足 responses[i] 中包含单词 word 的 i 的个数，则当 appearances(features[x]) > appearances(features[y]) 时，第 x 个功能比第 y 个功能更受欢迎。
     *
     * 返回一个数组 sortedFeatures ，包含按受欢迎程度排列的功能名称。当第 x  个功能和第 y 个功能的受欢迎程度相同且 x < y 时，你应当将第 x 个功能放在第 y 个功能之前。
     * 示例 1：
     *
     * 输入：features = ["cooler","lock","touch"], responses = ["i like cooler cooler","lock touch cool","locker like touch"]
     * 输出：["touch","cooler","lock"]
     * 解释：appearances("cooler") = 1，appearances("lock") = 1，appearances("touch") = 2。由于 "cooler" 和 "lock" 都出现了 1 次，且 "cooler" 在原数组的前面，所以 "cooler" 也应该在结果数组的前面。
     * 示例 2：
     *
     * 输入：features = ["a","aa","b","c"], responses = ["a","a aa","a a a a a","b a"]
     * 输出：["a","aa","b","c"]
     *
     *
     * 提示：
     *
     * 1 <= features.length <= 104
     * 1 <= features[i].length <= 10
     * features 不包含重复项。
     * features[i] 由小写字母构成。
     * 1 <= responses.length <= 102
     * 1 <= responses[i].length <= 103
     * responses[i] 由小写字母和空格组成。
     * responses[i] 不包含两个连续的空格。
     * responses[i] 没有前置或后置空格。
     */
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> featureBucket = new HashMap<>();
        for (String str : features) {
            featureBucket.put(str, 0);
        }
        for (String resp : responses) {
            String [] splitResp = resp.split(" ");
            for (String sp : splitResp) {
                if (featureBucket.get(sp) != null) {
                    featureBucket.put(sp, featureBucket.get(sp) + 1);
                }
            }
        }
        List<Feature> buckets = new ArrayList<>();
        for (Map.Entry e : featureBucket.entrySet()) {
            buckets.add(new Feature(e.getKey().toString(), Integer.parseInt(e.getValue().toString())));
        }
        Collections.sort(buckets);
        String [] ans = new String[buckets.size()];
        for (int i = 0; i < buckets.size(); i++) {
            ans[i] = buckets.get(i).getF();
        }
        return ans;
    }

    public class Feature implements Comparable <Feature>{
        public String f;
        public int c;

        public String getF() {
            return f;
        }

        public Feature(String f, int c) {
            this.f = f;
            this.c = c;
        }

        @Override
        public int compareTo(Feature o) {
            return o.c - this.c;
        }
    }

    public String[] sortFeaturesV1(String[] features, String[] responses) {
        Map<String, Set<Integer>> map = new HashMap<>();
        for(String feature : features) {
            map.put(feature, new HashSet<>());
        }
        for(int i = 0; i < responses.length; i++) {
            String r = responses[i];
            String[] words = r.split(" ");
            for(String w : words) {
                if(map.containsKey(w)) {
                    map.get(w).add(i);
                }
            }
        }
        Arrays.sort(features, (o1, o2) -> map.get(o2).size() - map.get(o1).size());
        return features;
    }
}
