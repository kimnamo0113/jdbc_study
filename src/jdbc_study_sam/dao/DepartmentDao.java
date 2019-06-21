package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByNo(Department dept) throws SQLException;
	int insertDepartment(Department dept) throws SQLException ;
	int deleteDepartment(Department dept) throws SQLException;
	int updateDepartment(Department dept) throws SQLException;
}
