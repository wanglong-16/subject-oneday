package com.leetcode.july;

import java.util.*;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-16 21:57:26
 * @author: wanglong16@meicai.cn
 */
public class Day17 {


    public String[] trulyMostPopularVBug(String[] names, String[] synonyms) {
        //存储名称对应的索引
        Map<String, Integer> indexMap = new HashMap<>();
        int n = names.length;
        //存储没个名字对应的频率
        int[] scores = new int[n];
        //初始化并差集
        parent = new int[n];
        rank = new int[n];
        roots = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        //初始化索引和频率数据
        for (int i = 0; i < n; i++) {
            String[] sp = names[i].split("\\(");
            indexMap.put(sp[0], i);
            scores[i] = Integer.parseInt(sp[1].split("\\)")[0]);
            names[i] = sp[0];
        }
        //按照代表元的字典序合并，
        for (String str : synonyms) {
            String[] arr = str.split(",");
            String s1 = arr[0].substring(1), s2 = arr[1].substring(0, arr[1].length() - 1);
            if (indexMap.get(s1) != null && indexMap.get(s2) != null) {
                int s1Index = indexMap.get(s1), s2Index = indexMap.get(s2);
                if (names[find(s1Index)].compareTo(names[find(indexMap.get(s2))]) >= 0) {
                    merge(s1Index, s2Index);
                } else {
                    merge(s2Index, s1Index);
                }
            }
        }
        //把得分都交给代表元
        for (int i = 0; i < n; i++) {
            int root = find(i);
            if (i != root) {
                scores[root] += scores[i];
                scores[i] = 0;
            }
        }
        //输出结果
        String[] ans = new String[roots];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (scores[i] != 0) {
                ans[cnt] = String.format("%s(%s)", names[i].split("\\(")[0], scores[i]);
                cnt ++;
            }
        }
        return ans;
    }


    int[] parent;
    int[] rank;
    int roots;

    //路径压缩
    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    //并查集内部按秩合并
    public boolean merge(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) {
            return false;
        }
        if (rank[ry] > rank[rx]) {
            int temp = ry;
            ry = rx;
            rx = temp;
        }
        rank[rx] += rank[ry];
        parent[rx] = ry;
        roots --;
        return true;
    }



    static class UFSet {

        int[] parent;
        int[] rank;
        int roots;
        public UFSet(int n) {
            parent = new int[n];
            rank = new int[n];
            roots = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public boolean merge(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            } else {
                parent[rx] = ry;
            }
            if (rank[ry] > rank[rx]) {
                int temp = ry;
                ry = rx;
                rx = temp;
            }
            rank[rx] += rank[ry];
            parent[rx] = ry;
            roots --;
            return true;
        }

        public int getRoots() {
            return roots;
        }
    }


    public static void main(String[] args) {
        Day17 day17 = new Day17();
        //["a(10)","c(13)"]
        //["(a,b)","(c,d)","(b,c)"]
        String[] strings1 = day17.trulyMostPopular(
                new String[] {"a(10)","c(13)"
                }
                , new String[] {
                        "(a,b)","(c,d)","(b,c)"
                }
        );
        String[] stirings2 = new String[] {"Npilye(25)","Drzsnw(87)","Fpaf(219)","Axwtno(57)","Avmzs(39)","Knpuq(61)","Avcp(41)","Naf(3)","Aeax(646)","Kgabb(236)","Chhmx(259)","Dwifi(237)","Ofl(72)","Nzaz(51)","Msyr(211)","Csh(238)","Kufa(95)","Ebov(96)","Onnev(77)","Chycnm(253)","Bnea(179)","Yjc(94)","Koaak(53)","Gauuk(75)","Hfp(97)","Jfq(26)","Dnsay(72)","Hljt(51)","Ibink(32)","Obcbxb(124)","Alrksy(69)","Qbmk(45)","Qiy(26)","Uvkdpn(71)","Unsb(26)","Weusps(79)","Fvkhz(104)","Fcclu(70)","Gnplfi(109)","Jvqg(47)","Mtz(72)","Ucqh(51)","Ntfr(70)","Nsgbth(26)","Hcvcgc(97)","Oltadg(95)","Nuq(61)","Kasgmw(95)","Nekuam(17)","Dhe(79)","Okwuq(96)","Qyqifg(85)","Mcnef(59)","Ijveol(46)","Acohsf(86)","Gbkq(77)","Mwwvj(70)","Yeekgc(11)","Ard(82)"};
        Arrays.sort(stirings2);
        Arrays.sort(strings1);
        System.out.println(Arrays.toString(strings1));
        System.out.println(Arrays.toString(stirings2));

    }


    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        UnionFind uf = new UnionFind();
        for(String str : names) {
            int index1 = str.indexOf('('), index2 = str.indexOf(')');
            String name = str.substring(0, index1);
            int count = Integer.valueOf(str.substring(index1 + 1, index2));
            //并查集初始化
            uf.parent.put(name, name);
            uf.size.put(name, count);

        }
        for(String synonym : synonyms) {
            int index = synonym.indexOf(',');
            String name1 = synonym.substring(1, index);
            String name2 = synonym.substring(index + 1, synonym.length() - 1);
            //避免漏网之鱼
            if(!uf.parent.containsKey(name1)) {
                uf.parent.put(name1, name1);
                //注意人数为0
                uf.size.put(name1, 0);
            }
            if(!uf.parent.containsKey(name2)) {
                uf.parent.put(name2, name2);
                uf.size.put(name2, 0);
            }
            uf.union(name1, name2);
        }
        List<String> res = new ArrayList<>();
        for(String str : names) {
            int index1 = str.indexOf('('), index2 = str.indexOf(')');
            String name = str.substring(0, index1);
            //根节点
            if(name.equals(uf.find(name))) {
                res.add(name + "(" + uf.size.get(name) + ")");
            }
        }
        return res.toArray(new String[res.size()]);
    }



    class UnionFind {
        //当前节点的父亲节点
        Map<String, String> parent;
        //当前节点人数
        Map<String, Integer> size;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
        }

        //找到x的根节点
        public String find(String x) {
            if (parent.get(x).equals(x)) {
                return x;
            }
            //路径压缩
            parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        public void union(String x, String y) {
            String str1 = find(x), str2 = find(y);
            if (str1.equals(str2)) {
                return;
            }
            //字典序小的作为根
            if (str1.compareTo(str2) > 0) {
                parent.put(str1, str2);
                //人数累加到根节点
                size.put(str2, size.get(str1) + size.get(str2));
            } else {
                parent.put(str2, str1);
                size.put(str1, size.get(str2) + size.get(str1));
            }
        }
    }

}
