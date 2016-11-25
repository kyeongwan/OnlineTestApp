package test.ui;

import test.DB;
import test.model.Problem;
import test.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemStatus extends JPanel {

    private Problem problem;
    public JProgressBar[] progressbar;
    public JTextArea[] studentListLabel;
    int no;
    int cnt;

    /**
     * Create the panel.
     */
    public ProblemStatus(String title, int no) {
        setLayout(new BorderLayout(0, 0));

        this.no = no - 1;
        problem = DB.getInstance().getProblemMap().get(no);
        HashMap<Integer, ArrayList<Student>> map = problem.getMap();

        progressbar = new JProgressBar[6];
        studentListLabel = new JTextArea[6];

        JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 5, 0, 0));

        JLabel lblNewLabel_1 = new JLabel(title);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(1, 5, 0, 0));

        cnt = problem.getType() == 1 ? 2 : 5;

        JScrollPane scrollPane;
        for (int i = 0; i < cnt; i++) {

            JPanel answerOnePanel = new JPanel();
            answerOnePanel.setBounds(i * 50, 0, 50, 300);
            panel_1.add(answerOnePanel);
            answerOnePanel.setLayout(null);

            progressbar[i] = new JProgressBar();
            progressbar[i].setBounds(0, 0, 50, 150);
            progressbar[i].setOrientation(SwingConstants.VERTICAL);

            studentListLabel[i] = new JTextArea((i+1) + "\n" + "학생이름");
            studentListLabel[i].setEnabled(false);
            studentListLabel[i].setEditable(false);
            scrollPane = new JScrollPane(studentListLabel[i]);
            scrollPane.setBounds(0, 151, 50, 140);

            for (int j = 0; i < map.get(i).size(); j++) {
                studentListLabel[i].setText(studentListLabel[i].getText() + map.get(i).get(j).getName() + "\n");
            }

            progressbar[i].setMinimum(0); //진행바 최소값 설정
            progressbar[i].setMaximum(100); //진행바 최대값 설정
            progressbar[i].setStringPainted(true); //진행사항 퍼센티지로 보여주기 설정
            progressbar[i].setForeground(Color.DARK_GRAY); //진행바 색설정

            answerOnePanel.add(progressbar[i]);
            answerOnePanel.add(scrollPane);
        }
    }

    public void setProgressbar() {

        int n = DB.getInstance().getProblemMap().get(no + 1).getN();

        for (int i = 0; i < cnt; i++) {
            progressbar[i].setValue((int) ((float)problem.getMap().get(i).size() / (float)n * (float)100));
        }

    }

}
