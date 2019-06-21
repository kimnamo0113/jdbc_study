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

@SuppressWarnings("serial")
public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnList;
	
	private DepartmentDao dao;
	
	private DepartmentUI frameDepartment;
	private DepartmentListUI frameDepartmentList;
	
	public ErpManagementUI() {
		dao = new DepartmentDaoImpl();
		initComponents();
	}

	private void initComponents() {
		setTitle("부서 관리 메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 91);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 0));

		btnAdd = new JButton("부서 추가");
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);

		btnUpdate = new JButton("부서 수정");
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("부서 삭제");
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);

		btnSearch = new JButton("부서 검색");
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);

		btnList = new JButton("부서 목록");
		btnList.addActionListener(this);
		contentPane.add(btnList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnList) {
			actionPerformedBtnList(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
		if (e.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(e);
		}
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
	}

	protected void actionPerformedBtnDelete(ActionEvent e) {
		String deptNo = JOptionPane.showInputDialog("삭제할 부서번호를 입력하세요");

		try {
			int res = dao.deleteDepartment(new Department(Integer.parseInt(deptNo)));
			if (frameDepartment == null) {
				frameDepartment = new DepartmentUI();
				frameDepartment.setDao(dao);
			}
			if (res != -1) JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			refreshUI();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnUpdate(ActionEvent e) {
		String deptNo = JOptionPane.showInputDialog("수정할 부서번호를 입력하세요");
		Department selDept;
		try {
			selDept = dao.selectDepartmentByNo(new Department(Integer.parseInt(deptNo)));
			if (selDept == null) {
				JOptionPane.showMessageDialog(null, "수정할 부서가 존재하지 않습니다.");
				return;
			}
			if (frameDepartment == null) {
				frameDepartment = new DepartmentUI();
				frameDepartment.setParent(this);
				frameDepartment.setDao(dao);
			}
			frameDepartment.setDepartment(selDept);
			frameDepartment.setVisible(true);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	protected void actionPerformedBtnSearch(ActionEvent e) {
		String deptNo = JOptionPane.showInputDialog("검색할 부서번호를 입력하세요");
		Department selDept;
		try {
			selDept = dao.selectDepartmentByNo(new Department(Integer.parseInt(deptNo)));
			if (selDept == null) {
				JOptionPane.showMessageDialog(null, "해당 부서가 존재하지 않습니다.");
				return;
			}
			PanelDepartment pdept = new PanelDepartment();
			pdept.setDepartment(selDept);
			pdept.setTfAllEditable(false);
			JOptionPane.showMessageDialog(null, pdept, "부서 정보", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		if (frameDepartment == null) {
			frameDepartment = new DepartmentUI();
			frameDepartment.setParent(ErpManagementUI.this);
			frameDepartment.setDao(dao);
		}
		frameDepartment.setVisible(true);
	}

	protected void actionPerformedBtnList(ActionEvent e) {
		if (frameDepartmentList == null) {
			frameDepartmentList = new DepartmentListUI();
		}
		frameDepartmentList.setDepartmentList(dao.selectDepartmentByAll());
		frameDepartmentList.reloadData();
		frameDepartmentList.setVisible(true);
	}
	
	public void refreshUI() {
		if (frameDepartmentList != null && frameDepartmentList.isVisible()) {
			frameDepartmentList.setDepartmentList(dao.selectDepartmentByAll());
			frameDepartmentList.reloadData();
		}
	}
}
