package test.socket;

import test.DB;
import test.model.Problem;
import test.model.Student;
import test.ui.Main;
import test.ui.ProblemStatus;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class API {
    private static API instance;

    private API(){

    }

    /**
        하나만 있어도 되는 클래스이므로
        싱글톤으로 구성했습니다
        getInstance 함수는 API 클래스의 인스턴스를 반환합니다
     */
    public static API getInstance(){
        if(instance == null){
            instance = new API();
        }
        return instance;
    }

    /**
     * API에 대한 매핑을 해주는 메소드입니다
     * @param request   String type 으로 요청을 가져옵니다
     * req : login id name
     * res : roomID
     *
     * req : question qID
     * res : type/questionStr
     *
     * req : answer pID aNo sID
     * res : success
     *
     * req : event sID action
     * res : success
     */
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
                Problem p = DB.getInstance().getProblemMap().get(Integer.parseInt(data[1]));
                return p.getType() + "/" + p.getQuestion() + "\r\n";
            }else{
                return "fail\r\n";
            }
        }

        if(request.contains("answer")){     // answer problemNo answerNo StudentID
            String data[] = request.split(" ");
            if(data.length != 4)
                return "fail\r\n";
            if(DB.getInstance().getProblemMap().containsKey(Integer.parseInt(data[1]))){
                int problemNo = Integer.parseInt(data[1]);
                int answerNo = Integer.parseInt(data[2]);
                System.out.println(DB.getInstance().getStudentMap().get(data[3]).getId());
                Problem p = DB.getInstance().getProblemMap().get(problemNo);
                Student s = DB.getInstance().getStudentMap().get(data[3]);
                p.addAnswerToMap(answerNo, s);

                ProblemStatus pa = Main.getInstance().problemPanel[problemNo - 1];
                pa.studentListLabel[answerNo-1].setText(pa.studentListLabel[answerNo-1].getText() + "\n" + s.getName());

                pa.setProgressbar();
                if(p.getAnswer() == Integer.parseInt(data[2]))
                    s.setGrade(s.getGrade() + 10);

                return "success\r\n";
            }
            return "fail\r\n";
        }

        if(request.contains("event")){
            String data[] = request.split(" ");
            String id = data[1];
            String action = data[2];
            JOptionPane.showMessageDialog(Main.getInstance().frame, DB.getInstance().getStudentMap().get(id).getName() + " 이벤트 발생 " + action, "경고", JOptionPane.WARNING_MESSAGE);
            return "success\r\n";
        }

        if(request.contains("done")){
            String data[] = request.split(" ");
            String id = data[1];
            List<Student> list = new ArrayList<>(DB.getInstance().getStudentMap().values());
            list.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if(o1.getGrade() > o2.getGrade())
                        return -1;
                    else if(o1.getGrade() == o2.getGrade())
                        return 0;
                    else
                        return 1;
                }
            });
            for(int i=0; i<list.size(); i++){
                if(list.get(i).getId().equals(id))
                    return (i+1) + "등입니다. (" + list.get(i).getGrade() + "점)\r\n";
            }
        }
        return "fail2\r\n";
    }
}
