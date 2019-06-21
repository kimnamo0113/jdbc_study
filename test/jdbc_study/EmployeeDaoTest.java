package jdbc_study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static final Logger log = LogManager.getLogger();
	static EmployeeDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.trace("setUpBeforeClass()");
		dao=new EmployeeDaoImpl();
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException, FileNotFoundException, IOException {
		log.trace("testSelectEmployeeByAll()");
		List<Employee> list=dao.selectEmployeeByAll();
		Assert.assertNotEquals(0, list.size());
		File imgFile = null;
		for(Employee e:list) {
			if(e.getPic() != null) {
				imgFile = getPicFile(e);
				log.trace(imgFile.getAbsolutePath());
			}
		}
		
	}

	private File getPicFile(Employee e) throws FileNotFoundException, IOException {
		File file = null;
		file = new File(System.getProperty("user.dir")+"\\pics\\"+e.getEmpName()+".jpg");
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write(e.getPic());
		}
		return file;
	}

	@Test
	public void test02InsertEmployee() throws SQLException {
		log.trace("testInsertEmployee");
		
		Employee newEmp = new Employee(1004, "김태리", "사원", new Employee(1003), 
				1500000, new Department(1), getImage());
		
		int res = dao.insertEmployee(newEmp);
		log.trace("res = "+res);
		Assert.assertEquals(1, res);
		
	}

	@Test
	public void test03UpdateEmployee() throws SQLException {
		log.trace("testUpdateEmployee()");
		
		Employee updateEmp=new Employee(1004,"김태리2", "사원", new Employee(1003), 
				1500000, new Department(1), getImage());
		int res = dao.updateEmployee(updateEmp);
		log.trace("res="+res);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test04SelectEmployeeByNo() throws SQLException, FileNotFoundException, IOException {
		log.trace("testSelectEmployeeByNo()");
		Employee selEmp=new Employee(1004);
		selEmp=dao.selectEmployeeByNo(selEmp);
		Assert.assertNotEquals(null, selEmp.getEmpName());
		if (selEmp.getPic() != null) {
			File imgFile = getPicFile(selEmp);
			log.trace(imgFile.getAbsolutePath());
		}
	}
	
	@Test
	public void test05DeleteEmployee() throws SQLException {
		log.trace("testDeleteEmployee()");
		Employee delEmp=new Employee(1004);
		int res=dao.deleteEmployee(delEmp);
		
		Assert.assertEquals(1, res);
	}
	
	
	
	
	private byte[] getImage() {
		byte[] pic = null;
		//D:\namo\workspace_java\jdbc_study
		String imgPath=System.getProperty("user.dir")+System.getProperty("file.separator")
			+"images"+System.getProperty("file.separator")+"terry3.jpg";
		File imgFile = new File(imgPath);
		try(InputStream is = new FileInputStream(imgFile);){
			pic=new byte[is.available()] ;
			is.read(pic);
		}catch(FileNotFoundException e) {
			System.out.println("해당 파일을 찾을수 없음");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return pic;
	}
	
	
	
	

}
