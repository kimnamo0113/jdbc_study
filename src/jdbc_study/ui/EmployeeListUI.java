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
import jdbc_study.ui.content.PanelDepartment;
import jdbc_study.ui.content.PanelEmployee;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseEvent;

public class EmployeeListUI extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTable table;
	private List<Employee> empList;
	private PanelEmployee pContent;
	
	public void setPcontent(PanelEmployee pContent) {
		this.pContent=pContent;
	}
	
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
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
            	pContent.focuseManagerChange("");
                dispose();
            }
    });

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
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTable(MouseEvent e) {
		int row=table.getSelectedRow();
		String value = String.valueOf(table.getValueAt(row, 0));
		pContent.focuseManagerChange(value);
		dispose();
		
	}



}
