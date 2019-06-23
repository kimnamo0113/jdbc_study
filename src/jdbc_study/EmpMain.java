package jdbc_study;

import java.awt.EventQueue;

import jdbc_study.ui.EmpManagermentUI;

public class EmpMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpManagermentUI frame = new EmpManagermentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
