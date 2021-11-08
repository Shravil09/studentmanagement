package studentProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Editreturn")
public class Editreturn extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String sid = req.getParameter("id");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
		
			PreparedStatement ps = con.prepareStatement("select * from student where id = ?");
			ps.setString(1, sid);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				out.println("<form action = 'EditServlet' method='POST'");
				out.println("<table>");
				
				out.println("<tr> <td>Student ID :</td> <td> <input type='text' name='stdid' id='stdid' value= '" + rs.getString("id")+ "'/> <br></br></td> </tr>");
				out.println("<tr> <td>Student Name :</td> <td> <input type='text' name='sname' id='sname' value= '" + rs.getString("sname")+ "'/><br></br> </td> </tr>");
				out.println("<tr> <td>Student Mail :</td> <td> <input type='text' name='smail'  id='smail' value= '" + rs.getString("smail")+ "'/><br></br> </td> </tr>");
				out.println("<tr> <td>Student Phone No. :</td> <td> <input type='number' name='sphno'  id='sphno' value= '" + rs.getString("sphno")+ "'/><br></br> </td> </tr>");
				out.println("<tr> <td>Student Batch Time :</td> <td> <input type='number' name='stime'  id='stime' value= '" + rs.getString("stime")+ "'/><br></br> </td> </tr>");
				out.println("<tr> <td></td> <td><input type='submit' value= 'Edit' /> </td> </tr>");
				
				out.println("</table>");
				out.println("</form>");
			}
			
			con.close();
		}
		catch (Exception e) {
			out.print(e.getMessage());
		}
	

	}
}