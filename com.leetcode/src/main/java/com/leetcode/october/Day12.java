package com.leetcode.october;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-10-19 22:09:23
 * @author: wanglong16@meicai.cn
 */
public class Day12 {

    public boolean areNumbersAscending(String s) {
        String[] tokens = s.split(" ");
        boolean isFirst = true;
        int pre = 0;
        for (String token : tokens) {
            if (isNumber(token)) {
                int current = Integer.parseInt(token);
                if (isFirst) {
                    isFirst = false;
                    pre = current;
                } else {
                    if (current <= pre) {
                        return false;
                    } else {
                        pre = current;
                    }
                }
            }
        }
        return true;
    }

    private boolean isNumber(String token) {
        for (Character c : token.toCharArray()) {
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    class Bank {

        long[] balance;
        int bkR;

        public Bank(long[] balance) {
            this.balance = balance;
            bkR = balance.length - 1;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (isAccountNotEffective(account1) || isAccountNotEffective(account2) || isAccountNotEnoughMoney(account1, money)) {
                return false;
            }
            balance[account1] -= money;
            balance[account2] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if (isAccountNotEffective(account)) {
                return false;
            }
            balance[account] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (isAccountNotEffective(account) || isAccountNotEnoughMoney(account, money)) {
                return false;
            }
            balance[account] -= money;
            return true;
        }

        private boolean isAccountNotEffective(int account) {
            return account < 0 || account > bkR;
        }

        private boolean isAccountNotEnoughMoney(int account, long money) {
            return balance[account] < money;
        }

    }

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */

    public int countMaxOrSubsets(int[] nums) {
        for (int num : nums) {
            target |= num;
        }
        backTrack(nums, 0, 0, false);
        return ans;
    }

    private int target = 0;
    private int ans = 0;

    private void backTrack(int[] nums, int idx, int total, boolean flag) {
        if (total == this.target && flag) {
            ans ++;
        }
        if (idx == nums.length) {
            return;
        }
        backTrack(nums, idx + 1, total | nums[idx], true);
        backTrack(nums, idx + 1, total, false);
    }



    List<List<Integer>> arr = new ArrayList<>();

    public void backTrackNums(int [] nums, int target, List<Integer> temp) {
        if (target == 0) {
            List<Integer> _t = new ArrayList<>(temp);
            Collections.sort(_t);
            if (!arr.contains(_t)) {
                arr.add(_t);
            }
        } else if (target > 0) {
            for (int num : nums) {
                temp.add(num);
                backTrackNums(nums, target - num, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Day12 day12 = new Day12();
        day12.backTrackNums(new int[] {1, 2, 3}, 6, new ArrayList<>());
        System.out.println(day12.arr);
    }

}
