package jdbc_study.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc_study.dto.Department;

@SuppressWarnings("serial")
public class DepartmentListUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Department> departList;

	public DepartmentListUI() {
		initComponents();
	}
	
	
	public void setStdList(List<Department> departList) {
		this.departList = departList;
	}
	
	private void initComponents() {
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
		Object[][] rows = new Object[departList.size()][];
		for(int i=0; i<departList.size(); i++) {
			rows[i] = departList.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] {"부서 번호", "부서 명", "층"};
	}
	
	
}
