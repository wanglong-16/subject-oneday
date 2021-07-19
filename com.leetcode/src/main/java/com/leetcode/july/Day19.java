package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-18 19:51:57
 * @author: wanglong16@meicai.cn
 */
public class Day19 {

    int[] parent;
    int[] rank;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConn(accounts.get(i).subList(1, accounts.get(i).size()), accounts.get(j).subList(1, accounts.get(j).size()))) {
                    merge(i, j);
                }
            }
        }
        Map<Integer, Set<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            List<String> list = accounts.get(i);
            Set<String> strings = map.getOrDefault(root, new HashSet<>());
            strings.addAll(list);
            map.put(root, strings);
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : map.entrySet()) {
            Set<String> set = entry.getValue();
            String name = accounts.get(entry.getKey()).get(0);
            set.remove(name);
            List<String> temp = new ArrayList<>(set);
            Collections.sort(temp);
            temp.add(0, name);
            ans.add(temp);
        }
        return ans;
    }



    public boolean isConn(List<String> acc1, List<String> acc2) {
        for (int i = 0; i < acc1.size(); i++) {
            if (acc2.contains(acc1.get(i))) {
                return true;
            }
        }
        return false;
    }


    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public void merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] < rank[ry]) {
                int tem = rx;
                ry = tem;
                rx = tem;
            }
            rank[rx] += rank[ry];
            parent[rx] = ry;
        }
    }

    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
            Map<String, String> emailToName = new HashMap<String, String>();
            int emailsCount = 0;
            for (List<String> account : accounts) {
                String name = account.get(0);
                int size = account.size();
                for (int i = 1; i < size; i++) {
                    String email = account.get(i);
                    if (!emailToIndex.containsKey(email)) {
                        emailToIndex.put(email, emailsCount++);
                        emailToName.put(email, name);
                    }
                }
            }
            UnionFind uf = new UnionFind(emailsCount);
            for (List<String> account : accounts) {
                String firstEmail = account.get(1);
                int firstIndex = emailToIndex.get(firstEmail);
                int size = account.size();
                for (int i = 2; i < size; i++) {
                    String nextEmail = account.get(i);
                    int nextIndex = emailToIndex.get(nextEmail);
                    uf.union(firstIndex, nextIndex);
                }
            }
            Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
            for (String email : emailToIndex.keySet()) {
                int index = uf.find(emailToIndex.get(email));
                List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
                account.add(email);
                indexToEmails.put(index, account);
            }
            List<List<String>> merged = new ArrayList<List<String>>();
            for (List<String> emails : indexToEmails.values()) {
                Collections.sort(emails);
                String name = emailToName.get(emails.get(0));
                List<String> account = new ArrayList<String>();
                account.add(name);
                account.addAll(emails);
                merged.add(account);
            }
            return merged;
        }
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String st : strs) {
            Integer hash = strHash(st);
            List<String> strings = map.getOrDefault(hash, new ArrayList<>());
            strings.add(st);
            map.put(hash, strings);
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry: map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    public Integer strHash(String str) {
        int[] arr = new int[26];
        for (Character c : str.toCharArray()) {
            arr[c - 'a'] ++;
        }
        return arr.hashCode();
    }

    public int maxFrequencyV1(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (ans >= i) {
                break;
            }
            int temp = k, _ans = 1;
            int t = nums[i], index = i - 1;
            while (temp > 0 && index >= 0) {
                if (t - nums[index] <= temp) {
                    temp -= t - nums[index];
                    _ans ++;
                }
                index --;
            }
            ans = Math.max(ans, _ans);
        }
        return ans;
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        Day19 day19 = new Day19();
        List<List<String>> arr = new ArrayList<>();
        //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        arr.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        arr.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        arr.add(Arrays.asList("Mary","mary@mail.com"));
        arr.add(Arrays.asList("John","johnnybravo@mail.com"));
        int str = day19.maxFrequency(new int[] {3,9,6,10,11,16}, 2);
        System.out.println(str);
    }
}
