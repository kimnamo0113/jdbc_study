package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;
import jdbc_study.ui.content.PanelDepartment;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnClear;
	private PanelDepartment pContent;

	private DepartmentDao dao;
	
	private ErpManagementUI erpManagementUI;
	
	public DepartmentUI() {
//		dao = new DepartmentDaoImpl();
		initComponents();
	}
	
	public void setDao(DepartmentDao dao) {
		this.dao = dao;
	}

	private void initComponents() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pContent = new PanelDepartment();
		contentPane.add(pContent, BorderLayout.CENTER);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department newDept = pContent.getDepartment();
		int res;
		try {
			res = dao.updateDepartment(newDept);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 부서가 수정되었습니다.", newDept.getDeptName()));
				pContent.clearTextField();
				btnAdd.setText("추가");
			}
			erpManagementUI.refreshUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department newDept = pContent.getDepartment();
		int res;
		try {
			res = dao.insertDepartment(newDept);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 부서가 추가되었습니다.", newDept.getDeptName()));
				pContent.clearTextField();
			}
			erpManagementUI.refreshUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
	
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTextField();
	}
	
	public void setDepartment(Department dept) {
		pContent.setDepartment(dept);
		pContent.getTfDeptNo().setEditable(false);
		btnAdd.setText("수정");
	}

	public void setParent(ErpManagementUI erpManagementUI) {
		this.erpManagementUI = erpManagementUI;
	}
}
