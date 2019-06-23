package jdbc_study.dto;

public class Employee {
	private int empNo;
	private String empName;
	private String title;
	private Employee manager;
	private int salary;
	private Department dno;
	private byte[] pic;

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, String title, Employee manager, int salary, Department dno) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
	}
	
	
	
	public Employee(int empNo, String empName, String title, Employee manager, int salary, Department dno, byte[] pic) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
		this.pic = pic;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s, %s]", empNo, empName, title, manager.getEmpNo(), salary, dno.getDeptNo());
	}

	public Object[] toArray() {
		return new Object[] { empNo, empName, title, manager.getEmpNo(), salary, dno.getDeptNo() };
	}

}
