package com.leetcode.june;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-06-19 14:57:50
 * @author: wanglong16@meicai.cn
 */
public class Day19 {


    public int leastMinutes(int n) {
        int t = n, ans = 0;
        while (t > 0) {
            t >>= 1;
            ans ++;
        }
        return (1 << ans - 1) == n ? ans : ans + 1;
    }


    public int halfQuestions(int[] questions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < questions.length; i++) {
            map.put(questions[i], map.getOrDefault(questions[i], 0) + 1);
        }
        List<Item> itemList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            itemList.add(new Item(entry.getKey(), entry.getValue()));
        }
        Collections.sort(itemList);
        int ans = 0,cnt = 0;
        for (int i = 0; i < itemList.size(); i++) {
            cnt += itemList.get(i).freq;
            ans ++;
            if (cnt >= questions.length / 2) {
                return ans;
            }
        }
        return ans;
    }

    class Item implements Comparable<Item>{
        private int code;
        private int freq;

        public Item(int code, int freq) {
            this.code = code;
            this.freq = freq;
        }

        @Override
        public int compareTo(Item o) {
            return o.freq - this.freq;
        }
    }

    public int largestArea(String[] grid) {
        int rows = grid.length, cols = grid[0].length();
        int[][] arr = new int[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            String zt = grid[i];
            for (int j = 0; j < grid[0].length(); j++) {
                if (zt.charAt(j) == '0') {
                    arr[i][j] = 0;//走廊
                } else {
                    arr[i][j] = 1;//主题
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (arr[i][j] == 1 &&
                arr[i - 1][j] == 1 && arr[i][j - 1] == 1 && arr[i + 1][j] == 1 && arr[i][j + 1] == 1) {
                    ans ++;
                }
            }
        }
        return ans;
    }

    class Solution {
        public int largestArea(String[] grid) {
            int[][] v=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
            int n=grid.length;
            int m=grid[0].length();
            boolean[][] visited=new boolean[n][m];
            int max=0;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    if (grid[i].charAt(j)!='0' && !visited[i][j]){
                        visited[i][j]=true;
                        int f=0;
                        boolean near=false;
                        List<Integer> queue=new ArrayList<>();
                        queue.add(i*m+j);
                        while (f<queue.size()){
                            int now=queue.get(f);
                            int x=now/m;
                            int y=now%m;
                            for (int k=0;k<4;k++){
                                int x1=x+v[k][0];
                                int y1=y+v[k][1];
                                if (x1>=0&&y1>=0&&x1<n&&y1<m){
                                    if (grid[x1].charAt(y1)=='0'){
                                        near=true;
                                    }
                                    if (grid[x1].charAt(y1)==grid[i].charAt(j) && !visited[x1][y1]){
                                        queue.add(x1*m+y1);visited[x1][y1]=true;
                                    }
                                } else {
                                    near=true;
                                }
                            }
                            f++;
                        }
                        if(!near){
                            if (queue.size()>max){
                                max=queue.size();
                            }
                        }
                    }
                }

            }
            return max;
        }
    }


    public static void main(String[] args) {
        Day19 day19 = new Day19();
        System.out.println(day19.leastMinutes(6));
    }
}
