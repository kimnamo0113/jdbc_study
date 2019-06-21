package jdbc_study.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;
import jdbc_study.jdbc.MySQLjdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	static final Logger log = LogManager.getLogger();
	@Override
	public List<Department> selectDepartmentByAll() {
		List<Department> lists = new ArrayList<Department>();
		String sql = "select deptno, deptname, floor from department";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = MySQLjdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			log.trace(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lists.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return lists;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
	}

	@Override
	public int insertDepartment(Department dept) throws SQLException {
		String sql = "INSERT INTO department(deptno, deptname, floor) VALUES(?,?,?);";
		int res = -1;

		try (Connection conn = MySQLjdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());
			log.trace(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Department selectDepartmentByNo(Department dept) throws SQLException {
		String sql = "select deptno, deptname, floor from department where deptno=?";

		Department selDept = null;

		try (Connection conn = MySQLjdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, dept.getDeptNo());
			log.trace(pstmt);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					selDept = getDepartment(rs);
				}
			}
		}
		return selDept;
	}

	@Override
	public int deleteDepartment(Department dept) throws SQLException {
		String sql = "DELETE FROM department WHERE deptno=?;";
		int res = -1;

		try (Connection conn = MySQLjdbcUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, dept.getDeptNo());
			log.trace(pstmt);
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department dept) throws SQLException {
		String sql = "update department set deptname=?, floor=? where deptno=?";
		int res = -1;

		try (Connection conn = MySQLjdbcUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, dept.getDeptName());
			pstmt.setInt(2, dept.getFloor());
			pstmt.setInt(3, dept.getDeptNo());
			
			log.trace(pstmt);
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
