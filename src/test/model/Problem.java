package test.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Problem {

    int type;       // 참거짓 문제라면 1, 5지선다라면 2
    String question;
    int answer;
    HashMap<Integer, ArrayList<Student>> map;
    int n = 0;


    public Problem(int type, String question, int answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
        map = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            map.put(i, new ArrayList<>());
        }
    }


    public HashMap<Integer, ArrayList<Student>> getMap() {
        return map;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void addAnswerToMap(int answerNo, Student s) {
        map.get(answerNo - 1).add(s);
        n++;
    }

    public int getType() {
        return type;
    }


    public int getN() {
        return n;
    }
}
