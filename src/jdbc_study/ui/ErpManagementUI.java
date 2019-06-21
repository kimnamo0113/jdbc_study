package jdbc_study.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.ui.content.PanelDepartment;

public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDelete;
	private JButton btnList;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnAdd;
	private DepartmentDao dao;
	private DepartmentListUI list;
	
	
	public ErpManagementUI() {
		dao=new DepartmentDaoImpl();
		initComponents();
	}
	private void initComponents() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
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
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(e);
		}
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnList) {
			actionPerformedBtnList(e);
		}
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
	}
	protected void actionPerformedBtnDelete(ActionEvent e) {
		int deptno=Integer.parseInt(JOptionPane.showInputDialog("삭제할 부서 번호").trim());
		Department delDept=new Department(deptno);
		int res;
		try {
			res=dao.deleteDepartment(delDept);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, "삭제 성공");
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "없는 부서입니다.");
		}
		refreshUI();
	}
	protected void actionPerformedBtnList(ActionEvent e) {
		list = new DepartmentListUI();
		list.setStdList(dao.selectDepartmentByAll());
		list.reloadData();
		list.setVisible(true);
	}
	protected void actionPerformedBtnSearch(ActionEvent e) {
		int deptno=Integer.parseInt(JOptionPane.showInputDialog("검색할 부서 번호").trim());
		Department serachDept=new Department(deptno);
		Department res;
		try {
			res=dao.selectDepartmentByNo(serachDept);
			if(res==null) {
				JOptionPane.showMessageDialog(null, "해당부서가 존재않슴돠");
				
			}
			PanelDepartment pdept = new PanelDepartment();
			pdept.setDepartment(res);
			pdept.setTfAllEditable(false);
			JOptionPane.showMessageDialog(null, pdept, "부서 정보", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "없는 부서입니다.");
		}
	}
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		int deptno=Integer.parseInt(JOptionPane.showInputDialog("수정할 부서 번호").trim());
		Department updateDept=new Department(deptno);
		Department res;
		try {
			res=dao.selectDepartmentByNo(updateDept);
			if(res!=null) {
				DepartmentUI frame = new DepartmentUI();
				frame.setTextField(res);
				frame.setParent(this);
				frame.setBtnAdd("수정");
				frame.setVisible(true);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "없는 부서입니다.");
		}
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		DepartmentUI frame = new DepartmentUI();
		frame.setParent(this);
		frame.setVisible(true);
		
	}
	
	public void refreshUI() {
		if (list.isVisible()) {
			list.setStdList(dao.selectDepartmentByAll());
			list.reloadData();
		}
	}
}
