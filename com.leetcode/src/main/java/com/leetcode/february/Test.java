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

        char[][] chars = new char[][] {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        SolutionSeventeen solutionSeventeen = new SolutionSeventeen();
        System.out.println(solutionSeventeen.numSteps("1101"));

    }

}
