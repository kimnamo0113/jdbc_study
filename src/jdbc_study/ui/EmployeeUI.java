package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Employee;
import jdbc_study.ui.content.PanelEmployee;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmployeeUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnAddButton;
	private PanelEmployee pContent;
	private EmployeeDao dao;
	private EmpManagermentUI parent;
	
	public void setParent(EmpManagermentUI parent) {
		this.parent=parent;
	}
	
	
	public EmployeeUI() {
		dao=new EmployeeDaoImpl();
		initComponents();
	}
	
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pContent = new PanelEmployee();
		contentPane.add(pContent, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnAddButton = new JButton("추가");
		btnAddButton.addActionListener(this);
		panel_1.add(btnAddButton);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);
	}

	
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddButton) {
			if(e.getActionCommand().equals("추가"))
				do_btnAddButton_actionPerformed(e);
			if(e.getActionCommand().equals("수정"))
				do_btnUpdateButton_actionPerformed(e);
		}
		if (e.getSource() == btnCancel) {
			do_btnCancel_actionPerformed(e);
		}
	}
	private void do_btnUpdateButton_actionPerformed(ActionEvent e) {
		Employee updateEmp=pContent.getEmp();
		int res;
		try {
			res=dao.updateEmployee(updateEmp);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, String.format("%s 가 수정되었습니다.", updateEmp.getEmpName()));
				pContent.clearTf();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		parent.refreshUI();
	}


	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		pContent.clearTf();
	}
	protected void do_btnAddButton_actionPerformed(ActionEvent e) {
		Employee newEmp = pContent.getEmp();
		int res;
		
		try {
			res=dao.insertEmployee(newEmp);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, String.format("<%s>님이 추가되었습니다", newEmp.getEmpName()));
				pContent.clearTf();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		parent.refreshUI();	
	}
	
	public void setText(Employee emp) {
		pContent.setText(emp);
	}


	public void setBtnAdd(String string) {
		btnAddButton.setText("수정");
		pContent.setDnoEidtable(false);
	}
	
}
