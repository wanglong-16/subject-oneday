package com.leetcode.july;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-07-27 22:53:34
 * @author: wanglong16@meicai.cn
 */
class MagicDictionary {

    String[] dicts;
    /** Initialize your data structure here. */
    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        dicts = dictionary;
    }

    public boolean search(String searchWord) {
        for (String word : dicts) {
            if (word.length() != searchWord.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < searchWord.length(); i++) {
                if (searchWord.charAt(i) != word.charAt(i)) {
                    count ++;
                }
            }
            if (count == 1) {
                return false;
            }
        }
        return true;
    }

}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
