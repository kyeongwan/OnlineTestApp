package test.ui;

import test.DB;
import test.socket.SocketServerThread;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frame.setBounds(100, 100, 712, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_7 = new ProblemStatus("1번", 1);
		panel.add(panel_7);

		ProblemStatus problem_ = new ProblemStatus("2번", 2);
		panel.add(problem_);

		ProblemStatus problem__1 = new ProblemStatus("3번", 3);
		panel.add(problem__1);

		ProblemStatus problem__2 = new ProblemStatus("4번", 4);
		panel.add(problem__2);

		ProblemStatus problem__3 = new ProblemStatus("5번", 5);
		panel.add(problem__3);

		ProblemStatus problem__4 = new ProblemStatus("6번", 6);
		panel.add(problem__4);

		JPanel panel_6 = new JPanel();
		frame.getContentPane().add(panel_6, BorderLayout.NORTH);

		JButton button = new JButton("학생 관리");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							StudentManager frame = new StudentManager();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_6.add(button);

		JButton btnServerStart = new JButton("서버 시작");
		btnServerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new SocketServerThread().start();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_6.add(btnServerStart);
	}

}
