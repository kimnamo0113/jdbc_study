package jdbc_study.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dao.DeptEmpTransactionDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLjdbcUtil;

public class DeptEmpTransactionDaoImpl implements DeptEmpTransactionDao {
	static final Logger log = LogManager.getLogger();
	@Override
	public int trInsertEmpAndDep(Employee emp, Department dept) {
		String deptSql="INSERT INTO department(deptno, deptname, floor) VALUES(?,?,?);";
		String empSql="insert into employee(empno,empname,title,manager,salary,dno) values(?, ?, ?, ?, ?, ?)";
		int res=0;
		try(Connection conn=MySQLjdbcUtil.getConnection();){
				conn.setAutoCommit(false);
				
				try(PreparedStatement dPstmt = conn.prepareStatement(deptSql);
					PreparedStatement ePstmt = conn.prepareStatement(empSql);){
					dPstmt.setInt(1, dept.getDeptNo());
					dPstmt.setString(2, dept.getDeptName());
					dPstmt.setInt(3, dept.getFloor());
					log.trace(dPstmt);
					res+=dPstmt.executeUpdate();
					ePstmt.setInt(1, emp.getEmpNo());
					ePstmt.setString(2, emp.getEmpName());
					ePstmt.setString(3, emp.getTitle());
					ePstmt.setInt(4, emp.getManager().getEmpNo());
					ePstmt.setInt(5, emp.getSalary());
					ePstmt.setInt(6, emp.getDno().getDeptNo());
					log.trace(ePstmt);
					res+=ePstmt.executeUpdate();
					
					if(res==2) {
						conn.commit();
						conn.setAutoCommit(true);
						log.trace("res = " + res + " commit()");
					}else {
						conn.rollback();
						conn.setAutoCommit(true);
						log.trace("res = " + res + " rollback()");
					}
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	}

}
