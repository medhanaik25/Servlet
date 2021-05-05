package com.dept.dao;
import java.util.List;

import com.dept.dao.exceptions.DepartmentAlreadyExistsException;

public interface DepartmentDAO // POJO crud interface
{
	void addDepartment(Department dRef) throws DepartmentAlreadyExistsException;		//	C - add - insert
	Department findDepartment(int dno);			//  R - find - select
	List<Department> findDepartments();			//  R - find - select all
	void modifyDepartment(Department dRef);		//  U - modify - update
	void removeDepartment(Department dRef);     //  D - remove - delete
}