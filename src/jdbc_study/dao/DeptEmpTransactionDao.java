package jdbc_study.dao;

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

public interface DeptEmpTransactionDao {
	int trInsertEmpAndDep(Employee emp, Department dept);
}
