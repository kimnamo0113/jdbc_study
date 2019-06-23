package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc_study.dto.Employee;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmployeeListUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Employee> empList;
	
	public void setEmpList(List<Employee> empList) {
		this.empList=empList;
	}
	
	public EmployeeListUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(),getColumnNames()));
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[empList.size()][];
		for(int i=0; i<empList.size(); i++) {
			rows[i]=empList.get(i).toArray();
		}
		
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] {"사원 번호","사원 명","직급","직속 상사","급여","부서번호"};
	}
}
