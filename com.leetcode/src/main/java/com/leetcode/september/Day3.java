package com.leetcode.september;

import com.leetcode.util.tree.TreeNode;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-09-16 10:56:03
 * @author: wanglong16@meicai.cn
 */
public class Day3 {

    public boolean isValidSudoku(char[][] board) {
        //ascii 1 ~ 9 = 49 ~ 57
        boolean result = true;
        // 检查纵横
        for (int i = 0; i < 9; i++) {
            char[] rowChars = new char[9];
            char[] colChars = new char[9];
            for (int j = 0; j < 9; j++) {
                rowChars[j] = board[i][j];
                colChars[j] = board[j][i];
            }
            boolean isRowMatch = isUniqNums(rowChars);
            boolean isColumnMatch = isUniqNums(colChars);
            result = result && isRowMatch && isColumnMatch;
        }
        //检查9个中心点
        for (int i = 1; i < 8; i += 3) {
            for (int j = 1; j < 8; j += 3) {
                result = result && checkCenter(board, i, j);
            }
        }
        return result;
    }

    private boolean isUniqNums(char[] chars) {
        Set<Character> uniqs = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 49 && chars[i] <= 57) {
                if (!uniqs.add(chars[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCenter(char[][] board, int centerX, int centerY) {
        Set<Character> uniqs = new HashSet<>();
        for (int i = centerX - 1; i <= centerX + 1; i++) {
            for (int j = centerY - 1; j <= centerY + 1; j++) {
                if (board[i][j] >= 49 && board[i][j] <= 57) {
                    if (!uniqs.add(board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public int minSteps(int n) {
        int cnt = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                int temp = n / i;
                cnt += temp;
            }
        }
        if (n > 1) {
            return cnt + n;
        }
        return cnt;
    }

    public int countKDifference(int[] nums, int k) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int[] findOriginalArray(int[] changed) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(changed);
        Map<Integer, Integer> target = new HashMap<>();
        for (Integer in : changed) {
            if (target.get(in) == null) {
                target.put(in * 2, target.getOrDefault(in * 2, 0) + 1);
                list.add(in);
            } else {
                target.put(in, target.get(in) - 1);
                if (target.get(in) <= 0) {
                    target.remove(in);
                }
            }
        }
        if (target.size() != 0) {
            int[] ans = new int[changed.length / 2];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println(day3.findOriginalArray(new int[]{6, 3, 0, 1}));
    }

    public int sumOfBeauties(int[] nums) {
        boolean isMatchAll = true;
        int cnt = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int pre = nums[i - 1], cur = nums[i], next = nums[i + 1];
            if (!(pre < cur && cur < next)) {
                isMatchAll = false;
            } else {
                cnt++;
            }
        }
        return isMatchAll ? cnt + 2 : cnt;
    }

    public int finalValueAfterOperations(String[] operations) {
        int base = 0;
        for (String operation : operations) {
            if ("++X".equals(operation) || "X++".equals(operation)) {
                base++;
            } else {
                base--;
            }
        }
        return base;
    }

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        max = Math.max(l + r + 1, max);
        return Math.max(l, r) + 1;
    }


    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int cur = 0;
        int index = 0;
        int lost = 0;
        for (int i = 0; i < gas.length; i++) {
            cur += gas[i] - cost[i];
            if (cur < 0) {
                lost -= cur;
                cur = 0;
                index = i + 1;
            }
        }
        return cur >= lost ? index : -1;
    }

    Node node;

    public Node connect1(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node po = queue.poll();
                po.next = queue.peek();
                if (po.left != null) {
                    queue.offer(po.left);
                }
                if (po.right != null) {
                    queue.offer(po.right);
                }
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next != null ? root.next.left : null;
            connect(root.left);
            connect(root.right);
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
