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
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.ui.content.PanelDepartment;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private PanelDepartment pContent;
	private JButton btnAdd;
	private JButton btnClear;
	private DepartmentDao dao;
	private ErpManagementUI parent;
	public DepartmentUI() {
		dao=new DepartmentDaoImpl();
		initComponents();
	}
	


	public JButton getBtnAdd() {
		return btnAdd;
	}



	public void setBtnAdd(String btnAdd) {
		
		this.btnAdd.setText(btnAdd);;
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
			if(e.getActionCommand().equals("추가"))
				actionPerformedBtnAdd(e);
			else if(e.getActionCommand().equals("수정"))
				actionPerformedUpdate(e);
			
		}
	}
	private void actionPerformedUpdate(ActionEvent e) {
		int res;
		Department updateDept=pContent.getDpartment();
		try {
			res=dao.updateDepartment(updateDept);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, String.format("%s 가 수정되었습니다.", updateDept.getDeptName()));
				pContent.clearTextField();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		parent.refreshUI();
	}



	protected void actionPerformedBtnAdd(ActionEvent e) {
		// 1. pContent getDepartement()호출
		// 2. dao에다 insertDepartment()호출
		// 3. 완료메세지 출력
		Department newDept=pContent.getDpartment();
		int res;
		try {
			res = dao.insertDepartment(newDept);
			if(res!=-1) {
				JOptionPane.showMessageDialog(null, String.format("%s 가 추가되었습니다.", newDept.getDeptName()));
				pContent.clearTextField();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		parent.refreshUI();
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTextField();
	}



	public void setTextField(Department serachDept) {
		pContent.setDepartment(serachDept);
	}



	public void setParent(ErpManagementUI erpManagementUI) {
		parent=erpManagementUI;
	}
	
	
}
