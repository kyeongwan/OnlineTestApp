package test.ui;

import test.DB;
import test.model.Problem;
import test.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemStatus extends JPanel {


	public JProgressBar[] progressbar;
	public JTextArea[] studentListLabel;
	/**
	 * Create the panel.
	 */
	public ProblemStatus(String title, int no) {
		setLayout(new BorderLayout(0, 0));

		Problem problem = DB.getInstance().getProblemMap().get(no);
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



		JScrollPane scrollPane;
		for(int i=0; i<5; i++) {

			JPanel answerOnePanel = new JPanel();
			answerOnePanel.setBounds(i*50, 0, 50, 300);
			panel_1.add(answerOnePanel);
			answerOnePanel.setLayout(null);

			progressbar[i] = new JProgressBar();
			progressbar[i].setBounds(0, 0, 50, 150);
			progressbar[i].setOrientation(SwingConstants.VERTICAL);

			studentListLabel[i] = new JTextArea("학생이름");
			studentListLabel[i].setEnabled(false);
			studentListLabel[i].setEditable(false);
			scrollPane = new JScrollPane(studentListLabel[i]);
			scrollPane.setBounds(0, 151, 50, 140);

			for(int j=0; i<map.get(i).size(); j++){
				studentListLabel[i].setText(studentListLabel[i].getText() + map.get(i).get(j).getName() + "\n");
			}

			answerOnePanel.add(progressbar[i]);
			answerOnePanel.add(scrollPane);
		}
	}

}
