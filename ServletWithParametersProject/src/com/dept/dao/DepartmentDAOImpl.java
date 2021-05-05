package com.dept.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dept.dao.exceptions.DepartmentAlreadyExistsException;

public class DepartmentDAOImpl implements DepartmentDAO
{
	Connection conn;
	ResultSet rs;
	Statement st;
	PreparedStatement pst;
	
	public void closeDBResources() {
		try {
			System.out.println("Closing All DB resources ...");
			
			if(rs!=null)
				rs.close();
			
			if(st!=null)
				st.close();
			
			if(pst!=null)
				pst.close();
			
			if(conn!=null)
				conn.close();
			
			System.out.println("All DB resources closed...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DepartmentDAOImpl() {
		
		try
		{
			//1st step : load the driver
			System.out.println("Trying to load the driver.......");
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver loaded....");
		
			//2nd step : connect to the database 
			System.out.println("Trying to connect to the database");
			//jdbc:oracle:thin:@localhost:1521:yourInstanceName XE/ORCL/OSE whaterver name found in tnsnames.ora file
			conn= DriverManager.getConnection("jdbc:oracle:thin:"+"@localhost:1521:ORCL","system","medha");

			System.out.println("Connected to the database");
		}
		catch(Exception e) {
			System.out.println("Some Problem : "+e);
		}
	}
	
	public void addDepartment(Department dRef) throws DepartmentAlreadyExistsException{ //insert query
		try {
			PreparedStatement pst = conn.prepareStatement("insert into dept values (?,?,?)"); // this is for DML
			pst.setInt(1, dRef.getDepartmentNumber());	
			pst.setString(2, dRef.getDepartmentName()); 
			pst.setString(3, dRef.getDepartmentLocation());
			System.out.println("PrepareStatement made....for DML");
			
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate(); //fire the pst associated insert query
			System.out.println("Record inserted..."+rows);
		} 
		catch(SQLIntegrityConstraintViolationException e)
		{
			DepartmentAlreadyExistsException daee= new DepartmentAlreadyExistsException("department already exists");
			throw daee;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Department findDepartment(int dno) {
		Department deptObj = null;
		
		try {
			st = conn.createStatement();
			System.out.println("Statment made .... ");
			
			rs = st.executeQuery("select * from dept where deptno="+dno); // type the query here
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); //5th step : process the result
			if(rs.next()) { // process the result set like a cursor program
				int x = rs.getInt(1); 	
				String y = rs.getString(2); // dname
				String z = rs.getString(3); 
				
				deptObj = new Department(); //empty single object
				deptObj.setDepartmentNumber(x);
				deptObj.setDepartmentName(y);
				deptObj.setDepartmentLocation(z);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptObj;
	}
	
	public 	List<Department> findDepartments() {
		List<Department> deptList = new ArrayList<Department>(); //empty
		
		try {
			Statement st = conn.createStatement();
			System.out.println("Statement made...");
			rs = st.executeQuery("select * from dept"); // type the query here
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); //5th step : process the result
			while(rs.next()) { // process the result set like a cursor program
				int x = rs.getInt(1); 	
				String y = rs.getString(2); // dname
				String z = rs.getString(3); 
				
				Department deptObj = new Department(); //empty single object
				deptObj.setDepartmentNumber(x);
				deptObj.setDepartmentName(y);
				deptObj.setDepartmentLocation(z);
				
				deptList.add(deptObj);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deptList;
	}
	
	public 	void modifyDepartment(Department dRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(update)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("update dept set dname=?, loc=? where deptno=?"); // this is for DML
			pst.setString(1, dRef.getDepartmentName()); 
			pst.setString(2, dRef.getDepartmentLocation());
			pst.setInt(3, dRef.getDepartmentNumber());
			
			System.out.println("PrepareStatement made....for DML update");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate();
			System.out.println("Record updated : "+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public 	void removeDepartment(Department dRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(delete)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("delete from dept where deptno=?"); // this is for DML
			pst.setInt(1, dRef.getDepartmentNumber()); //set the question mark with dno
			
			System.out.println("PrepareStatement made....for DML delete");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate(); //fire the pst associated insert query
			System.out.println("Record deleted..."+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}