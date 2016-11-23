package test.ui;

import test.DB;
import test.model.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class StudentManager extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tvStudentName;
	private JLabel lbStdentName;
	private JLabel lbStudentID;
	private JTextField tvStudentID;
	private JLabel lbRoomCode;
	private JTextField textField_2;
	private JButton btnAddStudent;
	private JButton btnDeleteStudent;

	/**
	 * Create the frame.
	 */
	public StudentManager() {
		setBounds(100, 100, 740, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		DefaultTableModel model=new DefaultTableModel();
		
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Room Code");
		model.addColumn("Grade");

		List<Student> list = new ArrayList<>(DB.getInstance().getStudentMap().values());
		for(int i=0; i<list.size();i++){
			Object[] row = {list.get(i).getId(), list.get(i).getName(), list.get(i).getRoomCode(), list.get(i).getGrade() };
			model.addRow(row);
		}

		table = new JTable(model);
		panel.add(new JScrollPane(table));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		lbStdentName = new JLabel("이름");
		panel_1.add(lbStdentName);
		
		tvStudentName = new JTextField();
		panel_1.add(tvStudentName);
		tvStudentName.setColumns(10);
		
		lbStudentID = new JLabel("학번");
		panel_1.add(lbStudentID);
		
		tvStudentID = new JTextField();
		panel_1.add(tvStudentID);
		tvStudentID.setColumns(10);
		
		lbRoomCode = new JLabel("roomCode");
		panel_1.add(lbRoomCode);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		btnAddStudent = new JButton("추가");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tvStudentName.getText();
				String id = tvStudentID.getText();
				String roomCode = textField_2.getText();

				DB.getInstance().getStudentMap().put(id, new Student(id, name, roomCode));

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object[] row = {id, name, roomCode, 0};
				model.addRow(row);
			}
		});
		panel_1.add(btnAddStudent);
		
		btnDeleteStudent = new JButton("삭제");
		
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					DB.getInstance().getStudentMap().remove(model.getValueAt(row, 2));
					model.removeRow(row);
				}
			}
		});
		panel_1.add(btnDeleteStudent);
		
		
	}

}
