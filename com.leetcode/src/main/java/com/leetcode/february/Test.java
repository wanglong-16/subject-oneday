package com.leetcode.february;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @version: 1.0
 * @date: 2021-02-01 21:19:18
 * @author: wanglong16@meicai.cn
 */
public class Test {

    public static void main(String[] args) {
        SolutionFifteen solutionFifteen = new SolutionFifteen();
        String[] skills = new String[]{"java", "nodejs", "reactjs"};

        List<String> team1 = new ArrayList<String>() {{add("java"); add("nodejs");}};
        List<String> team2 = new ArrayList<String>() {{add("nodejs"); add("reactjs");}};

        List<List<String>> team = new ArrayList<>();
        team.add(team1);
        team.add(team2);

        SolutionSixteen solutionSixteen = new SolutionSixteen();

    }

}
