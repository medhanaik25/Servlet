<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import= "java.io.IOException"%>
<%@ page import= "java.io.PrintWriter"%>
<%@ page import= "java.util.Iterator"%>
<%@ page import= "java.util.List"%>

<%@ page import= "javax.servlet.GenericServlet"%>
<%@ page import= "javax.servlet.ServletConfig"%>
<%@ page import= "javax.servlet.ServletException"%>
<%@ page import= "javax.servlet.ServletRequest"%>
<%@ page import= "javax.servlet.ServletResponse"%>

<%@ page import= "com.dept.dao.Department"%>
<%@ page import= "com.dept.dao.DepartmentDAOImpl"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%	DepartmentDAOImpl ddi = new DepartmentDAOImpl();	%>
		
	 <%	String buttonValue = request.getParameter("submit");	%>
	<% out.println("Button Value is " + buttonValue);%>
		
	 <%	if(buttonValue.equals("Add Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno"));
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
		
			Department deptObj = new Department();
			deptObj.setDepartmentNumber(dno);
			deptObj.setDepartmentName(dnm); 
			deptObj.setDepartmentLocation(dloc); 
			ddi.addDepartment(deptObj);
			out.println("<h4>Department is added to the database</h4>");
		}
		else if (buttonValue.equals("Find Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno"));
			Department deptObj = ddi.findDepartment(dno);	
			out.println("<table border=5 cellpadding=10 cellspacing=10>");
			out.println("<tr>");
			out.println("<td>"+deptObj.getDepartmentNumber()+"</td>");
			out.println("<td>"+deptObj.getDepartmentName()+"</td>");
			out.println("<td>"+deptObj.getDepartmentLocation()+"</td>");
			out.println("</tr>");
			out.println("</table>");			
		}
		else if(buttonValue.equals("Find All Depts")) 
		{
			out.println("<table border=1 cellspacing=10 cellpadding=10>");
			out.println("<th>");		out.println("DEPT NO");	out.println("</th>");
			out.println("<th>");		out.println("DEPT NAME");out.println("</th>");
			out.println("<th>");		out.println("LOCATION"); out.println("</th>");
			List<Department> deptList = ddi.findDepartments();
			Iterator<Department> deptIter = deptList.iterator();
			
				while(deptIter.hasNext()) {
					Department deptObj = deptIter.next();
					out.println("<tr>");
					
						out.println("<td>");
							out.println(deptObj.getDepartmentNumber());
						out.println("</td>");
					
						out.println("<td>");
							out.println(deptObj.getDepartmentName());
						out.println("</td>");

						out.println("<td>");
							out.println(deptObj.getDepartmentLocation());
						out.println("</td>");
							
					out.println("</tr>");
				}
			out.println("</table>");
		}
		else if (buttonValue.equals("Modify Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); //to read html variable name's values 
			String dnm = request.getParameter("v_dname");
			String dloc = request.getParameter("v_loc");
			Department deptObj = new Department(); //blank one
			deptObj.setDepartmentNumber(dno);
			deptObj.setDepartmentName(dnm);
			deptObj.setDepartmentLocation(dloc);
			out.println("DNO " + dno);

			ddi.modifyDepartment(deptObj);
			out.println("<h4> Department is modified</h4>");
		}
		else if (buttonValue.equals("Delete Dept"))
		{
			int dno = Integer.parseInt(request.getParameter("v_deptno")); 		
			Department deptObj = new Department();
			deptObj.setDepartmentNumber(dno);
			ddi.removeDepartment(deptObj);
			out.println("<h4> Department is deleted</h4>");	
		}
		
		%>
	<a href='http://localhost:8085/Deptartment.jsp/'> Back to Welcome </a>	
</body>
</html>