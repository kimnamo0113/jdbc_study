package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Employee;

public interface EmployeeDao{
	List<Employee> selectEmployeeByAll() throws SQLException;
	Employee selectEmployeeByNo(Employee employee) throws SQLException;
	int insertEmployee(Employee employee) throws SQLException;
	int deleteEmployee(Employee employee) throws SQLException;
	int updateEmployee(Employee employee) throws SQLException;
}
