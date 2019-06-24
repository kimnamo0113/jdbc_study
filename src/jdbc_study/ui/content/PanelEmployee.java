package jdbc_study.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.ui.EmployeeListUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.FocusEvent;

public class PanelEmployee extends JPanel implements FocusListener {
	private JTextField tfEmpno;
	private JTextField tfName;
	private JTextField tfTitle;
	private JTextField tfManager;
	private JTextField tfSalary;
	private JTextField tfDno;
	private EmployeeListUI frame;

	public PanelEmployee() {

		initComponents();
	}
	
	
	
	public void setDnoEidtable(Boolean bool) {
		tfEmpno.setEditable(bool);
	}


	private void initComponents() {
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblEmpno = new JLabel("사원 번호");
		add(lblEmpno);

		tfEmpno = new JTextField();
		add(tfEmpno);
		tfEmpno.setColumns(10);

		JLabel lblName = new JLabel("사원 이름");
		add(lblName);

		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);

		JLabel lblTitle = new JLabel("직급");
		add(lblTitle);

		tfTitle = new JTextField();
		add(tfTitle);
		tfTitle.setColumns(10);

		JLabel lblManager = new JLabel("직속 상사");
		add(lblManager);

		tfManager = new JTextField();
		tfManager.addFocusListener(this);
		add(tfManager);
		tfManager.setColumns(10);

		JLabel lblSalary = new JLabel("급여");
		add(lblSalary);

		tfSalary = new JTextField();
		add(tfSalary);
		tfSalary.setColumns(10);

		JLabel lblDno = new JLabel("부서 번호");
		add(lblDno);

		tfDno = new JTextField();
		add(tfDno);
		tfDno.setColumns(10);
	}

	public Employee getEmp() {
		return new Employee(Integer.parseInt(tfEmpno.getText()), tfName.getText(), tfTitle.getText(), new Employee(Integer.parseInt(tfManager.getText())), Integer.parseInt(tfSalary.getText()), new Department(Integer.parseInt(tfDno.getText())));  
	}
	public void setText(Employee emp) {
		tfEmpno.setText(emp.getEmpNo()+"");
		tfName.setText(emp.getEmpName());
		tfTitle.setText(emp.getTitle());
		
		tfManager.setText(emp.getManager().getEmpNo()+"");
		tfSalary.setText(emp.getSalary()+"");
		tfDno.setText(emp.getDno().getDeptNo()+"");
	}
	public void clearTf() {
		tfEmpno.setText("");
		tfName.setText("");
		tfTitle.setText("");
		tfManager.setText("");
		tfSalary.setText("");
		tfDno.setText("");
	}
	public void setTfAllEditable(boolean isEditable) {
		tfEmpno.setEditable(isEditable);
		tfName.setEditable(isEditable);
		tfTitle.setEditable(isEditable);
		tfManager.setEditable(isEditable);
		tfSalary.setEditable(isEditable);
		tfDno.setEditable(isEditable);
	}
	
	
	public void focusGained(FocusEvent e) {
		if (e.getSource() == tfManager) {
			if(tfManager.getText().trim().length()==0)
				focusGainedTfManager(e);
		}
	}
	public void focusLost(FocusEvent e) {
		
	}
	protected void focusGainedTfManager(FocusEvent e) {
		EmployeeDao dao=new EmployeeDaoImpl();
		try {
				List<Employee> empList=dao.selectEmployeeByAll();
				frame = new EmployeeListUI();
				frame.setEmpList(empList);
				frame.setPcontent(this);
				frame.reloadData();
				frame.setVisible(true);
		} catch (SQLException e1) {
			
		}
	}
	public void focuseManagerChange(String value) {
		tfManager.setText(value);
		tfSalary.requestFocus();
	}
	


}
