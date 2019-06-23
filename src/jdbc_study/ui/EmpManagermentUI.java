package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.ui.content.PanelEmployee;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmpManagermentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnList;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnSearch;
	private EmployeeListUI listUI;
	private EmployeeDao dao;
	
	
	public EmpManagermentUI() {
		dao=new EmployeeDaoImpl();
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 102);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 0));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);
		
		btnList = new JButton("목록");
		btnList.addActionListener(this);
		contentPane.add(btnList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
		if (e.getSource() == btnList) {
			actionPerformedBtnList(e);
		}
	}
	protected void actionPerformedBtnList(ActionEvent e) {
		listUI = new EmployeeListUI();
		try {
			listUI.setEmpList(dao.selectEmployeeByAll());
			listUI.reloadData();
			listUI.setVisible(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		int empno=Integer.parseInt(JOptionPane.showInputDialog("수정할 부서 번호").trim());
		Employee updateEmp=new Employee(empno);
		Employee res;
		try {
			res=dao.selectEmployeeByNo(updateEmp);
			if(res!=null) {
				EmployeeUI frame = new EmployeeUI();
				frame.setText(updateEmp);
				frame.setParent(this);
				frame.setBtnAdd("수정");
				frame.setVisible(true);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "없는 사원입니다.");
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		EmployeeUI frame = new EmployeeUI();
		frame.setParent(this);
		frame.setVisible(true);
	}
	protected void actionPerformedBtnDelete(ActionEvent e) {
		int delEmpNo=Integer.parseInt(JOptionPane.showInputDialog("삭제할 사원 번호를 입력하세요").trim());
		Employee delEmp=new Employee(delEmpNo);
		try {
			int res=dao.deleteEmployee(delEmp);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, "시원~하게 삭제되었습니다");
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "삭제 실패");
		}
		refreshUI();
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		int dmpNo=Integer.parseInt(JOptionPane.showInputDialog("검색할 사원 번호를 입력하세요").trim());
		Employee searchEmp=new Employee(dmpNo);
		Employee res;
		try {
			res=dao.selectEmployeeByNo(searchEmp);
			if(res==null) {
				JOptionPane.showMessageDialog(null, "없는 사원입니다.");
			}
			PanelEmployee pEmp=new PanelEmployee();
			pEmp.setText(res);
			pEmp.setTfAllEditable(false);
			JOptionPane.showMessageDialog(null, pEmp,"사원 정보",JOptionPane.INFORMATION_MESSAGE);
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "없는 사원입니다.");
		}
	}
	public void refreshUI(){
		if(listUI.isVisible()) {
			try {
				listUI.setEmpList(dao.selectEmployeeByAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listUI.reloadData();
		}
	}
}
