package test.model;

import test.DB;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lk on 2016. 11. 23..
 */
public class Problem {

    int type;
    String question;
    int anwer;
    HashMap<Integer, ArrayList<Student>> map;
    int n = 0;


    public Problem(int type, String question, int answer) {
        this.type = type;
        this.question = question;
        this.anwer = answer;
        map = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            map.put(i, new ArrayList<Student>());
        }
    }


    public HashMap<Integer, ArrayList<Student>> getMap() {
        return map;
    }

    public int getAnwer() {
        return anwer;
    }

    public String getQuestion() {
        return question;
    }

    public void addAnswerToMap(int answerNo, Student s) {
        map.get(answerNo - 1).add(s);
        n++;
    }


    public int getN() {
        return n;
    }
}
