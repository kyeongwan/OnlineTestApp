package test.ui;

import test.socket.SocketServerThread;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JFrame frame;
    private static Main instance;
    public ProblemStatus problemPanel[];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Main window = new Main();
        window.frame.setVisible(true);
        instance = window;
    }


    public static Main getInstance() {
        return instance;
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1370, 545);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        problemPanel = new ProblemStatus[5];
        for (int i = 0; i < problemPanel.length; i++) {
            problemPanel[i] = new ProblemStatus((i + 1) + "번", i + 1);
            problemPanel[i].setBounds(10 + i*250 + i*20, 0, 250, 300);
            panel.add(problemPanel[i]);
        }

        JPanel panel_6 = new JPanel();
        frame.getContentPane().add(panel_6, BorderLayout.NORTH);

        JButton button = new JButton("학생 관리");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentManager frame = new StudentManager();
                frame.setVisible(true);
            }
        });
        panel_6.add(button);

        JButton btnServerStart = new JButton("서버 시작");
        btnServerStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SocketServerThread().start();
            }
        });
        panel_6.add(btnServerStart);
    }

}
