package studentProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String sid = req.getParameter("id");
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
		
			String stdid = req.getParameter("stdid");
			String sname = req.getParameter("sname");			
			String smail = req.getParameter("smail");
			String sphno = req.getParameter("sphno");
			String stime = req.getParameter("stime");
			
			PreparedStatement ps = con.prepareStatement("update student set sname=?, smail=?, sphno=?, stime=? where id = ?");
			ps.setString(1, sname);
			ps.setString(2, smail);
			ps.setString(3, sphno);
			ps.setString(4, stdid);
			ps.setString(5, stime);
			
			
			int k = ps.executeUpdate();
			
			out.println("<font color='green'> Record Updated Successfully </font>");
			con.close();
		}
		catch (Exception e) {
			out.println("<font color='red'> Record Failed </font>");
		}
	}
}