package jdbc_study.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc_study.dto.Department;

@SuppressWarnings("serial")
public class DepartmentListUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Department> deptList;
	
	public DepartmentListUI() {
		initComponents();
	}
	
	public void setDepartmentList(List<Department> deptList) {
		this.deptList = deptList;
	}

	private void initComponents() {
		setTitle("부서 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "부서 목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(),getColumnNames()));
		
		// 부서번호, 부서명은 가운데 정렬
		tableCellAlignment(SwingConstants.CENTER, 0,1);
		// 위치(층)은 우측 정렬
		tableCellAlignment(SwingConstants.RIGHT, 2);	
		// 부서번호, 부서명, 위치 의 폭을 (100, 200, 70)으로 가능하면 설정 
		tableSetWidth(100, 200, 70);
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[deptList.size()][];
		for(int i=0; i<deptList.size(); i++) {
			rows[i] = deptList.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] {"부서번호", "부서명", "위치(층)"};
	}

	
	// 테이블 셀 내용의 정렬
	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	// 테이블 셀의 폭 설정
	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
}
