package com.mydept;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dept.dao.Department;
import com.dept.dao.DepartmentDAOImpl;
import com.dept.dao.exceptions.DepartmentAlreadyExistsException;

public class TestingDAO {
DepartmentDAOImpl daoObj = new DepartmentDAOImpl();
Department dept =new Department();
@Test
public void createTest() {
	dept.setDepartmentNumber(55);
	dept.setDepartmentName("CODING");
	dept.setDepartmentLocation("New Delhi");
	try {
		daoObj.addDepartment(dept);
	}
	catch(DepartmentAlreadyExistsException e) {
		System.out.println(e.getMessage());
		
	}
	dept=daoObj.findDepartment(55);
	assertEquals("CODING",dept.getDepartmentName());
	assertEquals("New Delhi",dept.getDepartmentLocation());
	assertEquals(55,dept.getDepartmentNumber());
}@Test
public void findTest() {
	dept=daoObj.findDepartment(55);
	assertEquals("CODING",dept.getDepartmentName());
	assertEquals("New Delhi",dept.getDepartmentLocation());
	assertEquals(55,dept.getDepartmentNumber());	
}
}