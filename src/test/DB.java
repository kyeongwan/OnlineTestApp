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

        problemMap.put(1, new Problem(1, "1 답은 뭔가요??", 3));
        problemMap.put(2, new Problem(1, "2 답은 뭔가요??", 3));
        problemMap.put(3, new Problem(1, "3 답은 뭔가요??", 3));
        problemMap.put(4, new Problem(1, "4 답은 뭔가요??", 3));
        problemMap.put(5, new Problem(1, "5 답은 뭔가요??", 3));
        problemMap.put(6, new Problem(2, "6 답은 뭔가요??", 3));
        problemMap.put(7, new Problem(2, "7 답은 뭔가요??", 3));
        problemMap.put(8, new Problem(2, "8 답은 뭔가요??", 3));
        problemMap.put(9, new Problem(2, "9 답은 뭔가요??", 3));
        problemMap.put(10, new Problem(2, "10 답은 뭔가요??", 3));
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
