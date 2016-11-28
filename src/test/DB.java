package test;

import test.model.Problem;
import test.model.Student;

import java.util.HashMap;

public class DB {

    private static DB instance = null;
    private HashMap<String, Student> studentMap;
    private HashMap<Integer, Problem> problemMap;


    private DB() {
        studentMap = new HashMap<>();
        problemMap = new HashMap<>();

        /*
            problemMap 에다가 데이터를 넣습니다.
            key : 문제 번호 int 형
            value : Problem 인스턴스
         */
        problemMap.put(1, new Problem(1, Global.problem1, Global.answer1));
        problemMap.put(2, new Problem(1, Global.problem2, Global.answer2));
        problemMap.put(3, new Problem(1, Global.problem3, Global.answer3));
        problemMap.put(4, new Problem(1, Global.problem4, Global.answer4));
        problemMap.put(5, new Problem(1, Global.problem5, Global.answer5));
        problemMap.put(6, new Problem(2, Global.problem6, Global.answer6));
        problemMap.put(7, new Problem(2, Global.problem7, Global.answer7));
        problemMap.put(8, new Problem(2, Global.problem8, Global.answer8));
        problemMap.put(9, new Problem(2, Global.problem9, Global.answer9));
        problemMap.put(10, new Problem(2, Global.problem10, Global.answer10));
    }

    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }

    public HashMap<Integer, Problem> getProblemMap() {
        return problemMap;
    }

    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }


}
