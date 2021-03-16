package com.leetcode.march.single;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-03-15 23:26:34
 * @author: wanglong16@meicai.cn
 */
class WordsFrequency {

    private final Map<String, Integer> dict = new HashMap<>(20000);

    public WordsFrequency(String[] book) {
        for (String str : book) {
            int count = dict.containsKey(str) ? dict.get(str) + 1 : 1;
            dict.put(str, count);
        }
    }

    public int get(String word) {
        return dict.getOrDefault(word, 0);
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */