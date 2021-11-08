package studentProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewstudent")
public class viewstudent extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
		
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from student");
			
			out.println("<table cellspacing='0' width='750px' border='2'>");
			out.println("<tr>");
			out.println("<td>Student ID</td>");
			out.println("<td>Student Name</td>");
			out.println("<td>Student Mail</td>");
			out.println("<td>Student Phone No.</td>");
			out.println("<td>Batch Time</td>");
			out.println("<td>Edit</td>");
			out.println("<td>Delete</td>");
			out.println("</tr>");
			
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>"+ rs.getString("id")+"</td>");
				out.println("<td>"+ rs.getString("sname")+"</td>");
				out.println("<td>"+ rs.getString("smail")+"</td>");
				out.println("<td>"+ rs.getString("sphno")+"</td>");
				out.println("<td>"+ rs.getString("stime")+"</td>");
				out.println("<td>"+ "<a href='Editreturn?id=" + rs.getString("id") +"'> Edit </a>"+"</td>");
				out.println("<td>"+ "<a href='Delete?id=" + rs.getString("id") +"'> Delete </a>"+"</td>");
				out.println("</tr>");
			}
			
			con.close();
		}
		catch (Exception e) {
			out.print(e.getMessage());
		}
	

	}
}
