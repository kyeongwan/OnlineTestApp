package test.ui;

import test.DB;
import test.model.Problem;
import test.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemStatus extends JPanel {


	JProgressBar[] progressbar;
	/**
	 * Create the panel.
	 */
	public ProblemStatus(String title, int no) {
		setLayout(new BorderLayout(0, 0));

		Problem problem = DB.getInstance().getProblemMap().get(no);
		HashMap<Integer, ArrayList<Student>> map = problem.getMap();

		progressbar = new JProgressBar[5];

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 5, 0, 0));

		JLabel lblNewLabel_1 = new JLabel(title);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(1, 5, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		progressbar[0].setOrientation(SwingConstants.VERTICAL);
		panel_2.add(progressbar[0]);

		JLabel lblNewLabel = new JLabel("<html><center>1<br>학생이름<br>");
		for(int i=0; i<map.get(0).size(); i++){
			lblNewLabel.setText(lblNewLabel.getText() + map.get(0).get(i).getName() + "<br>");
		}
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		progressbar[1].setOrientation(SwingConstants.VERTICAL);
		panel_2.add(progressbar[1]);

		JLabel label = new JLabel("<html><center>2<br>학생이름<br>");
		for(int i=0; i<map.get(1).size(); i++){
			lblNewLabel.setText(lblNewLabel.getText() + map.get(1).get(i).getName() + "<br>");
		}
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label, BorderLayout.SOUTH);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		progressbar[2].setOrientation(SwingConstants.VERTICAL);
		panel_2.add(progressbar[2]);

		JLabel label_1 = new JLabel("<html><center>3<br>학생이름<br>");
		for(int i=0; i<map.get(2).size(); i++){
			lblNewLabel.setText(lblNewLabel.getText() + map.get(2).get(i).getName() + "<br>");
		}
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_1, BorderLayout.SOUTH);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		progressbar[3].setOrientation(SwingConstants.VERTICAL);
		panel_2.add(progressbar[3]);

		JLabel label_2 = new JLabel("<html><center>4<br>학생이름<br>");
		for(int i=0; i<map.get(3).size(); i++){
			lblNewLabel.setText(lblNewLabel.getText() + map.get(3).get(i).getName() + "<br>");
		}
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(label_2, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		progressbar[4].setOrientation(SwingConstants.VERTICAL);
		panel_2.add(progressbar[4]);

		JLabel label_3 = new JLabel("<html><center>5<br>학생이름<br>");
		for(int i=0; i<map.get(4).size(); i++){
			lblNewLabel.setText(lblNewLabel.getText() + map.get(4).get(i).getName() + "<br>");
		}
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(label_3, BorderLayout.SOUTH);

	}

}
