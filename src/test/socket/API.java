package test.socket;

import test.DB;
import test.model.Problem;
import test.model.Student;

/**
 * Created by lk on 2016. 11. 23..
 */
public class API {
    private static API instance;

    private API(){

    }

    public static API getInstance(){
        if(instance == null){
            instance = new API();
        }
        return instance;
    }

    public String request(String request) {
        if(request.contains("login")){
            System.out.println("aa");
            String data[] = request.split(" ");
            if(DB.getInstance().getStudentMap().containsKey(data[1])){
                return DB.getInstance().getStudentMap().get(data[1]).getRoomCode() + "\r\n";
            }else{
                return "fail\r\n";
            }
        }

        if(request.contains("question")){
            String data[] = request.split(" ");
            if(DB.getInstance().getProblemMap().containsKey(Integer.parseInt(data[1]))){
                return DB.getInstance().getProblemMap().get(Integer.parseInt(data[1])).getQuestion() + "\r\n";
            }else{
                return "fail\r\n";
            }
        }

        if(request.contains("answer")){
            String data[] = request.split(" ");
            if(data.length != 4)
                return "fail\r\n";
            if(DB.getInstance().getProblemMap().containsKey(Integer.parseInt(data[1]))){
                System.out.println(DB.getInstance().getStudentMap().get(data[3]).getId());
                Problem p = DB.getInstance().getProblemMap().get(Integer.parseInt(data[1]));
                Student s = DB.getInstance().getStudentMap().get(data[3]);
                p.getMap().get(Integer.parseInt(data[2])).add(s);
                if(p.getAnwer() == Integer.parseInt(data[2]))
                    s.setGrade(s.getGrade() + 10);

                return "success\r\n";
            }
            return "fail\r\n";
        }
        return "fail2\r\n";
    }
}
