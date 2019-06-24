package jdbc_study.ui.content;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class test extends JFrame {
	private final String[] colNames = { "이름", "나이", "성별" };
	private String[][] datas = { { "홍길동", "20", "남" }, { "김말자", "18", "여" } };

	public test() {
		super("JTable Tester");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		DefaultTableModel dtm = new DefaultTableModel(datas, colNames);
		JTable table = new JTable(dtm);
		table.addMouseListener(new MyMouseListener()); // MouseListener등록
		JScrollPane jsp = new JScrollPane(table);
		panel.add(jsp, "Center");
		setContentPane(panel);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new test();
	}

	// 다이얼로그띄우기
	private void getDialog(String str, int index) {
		JDialog dialog = new JDialog(this, str);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // x눌렀을때 창 닫기
		JPanel pan = new JPanel(new GridLayout(colNames.length, 0));
		JLabel[] label = new JLabel[colNames.length];
		for (int i = 0; i < colNames.length; i++) {
			label[i] = new JLabel(colNames[i] + " : " + datas[index][i]);
			pan.add(label[i]);
		}
		dialog.add(pan);
		dialog.pack();
		dialog.setVisible(true);
	}

	// MouseListener Class
	private class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) { // 마우스클릭이벤트발생시
			if (e.getButton() == 3) { // 우클릭시
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow(); // 선택되어진 row구하기
				if (row != -1) { // 셀이 선택되어진 상태인경우
					getDialog((String) table.getValueAt(row, 0), row); // 새 다이얼로그 띄우기
				}
			}
		}
	}
}
