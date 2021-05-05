package com.dept.dao.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

public class DepartmentAlreadyExistsException extends Exception {
	
	public DepartmentAlreadyExistsException(String e) {
		super(e);
	}
}