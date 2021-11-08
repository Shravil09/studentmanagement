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

@WebServlet("/student")
public class student extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
			String stdid = req.getParameter("stdid");
			String stdname = req.getParameter("sname");
			String stdmail = req.getParameter("smail");
			String stdphno = req.getParameter("sphno");
			String stdtime = req.getParameter("stime");
			
			PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?,?)");
			ps.setString(1, stdid);
			ps.setString(2, stdname);
			ps.setString(3, stdmail);
			ps.setString(4, stdphno);
			ps.setString(5, stdtime);
			
			int k = ps.executeUpdate();
			out.println("<font color='green'> Record Inserted Successfully </font>");
			
			con.close();
		}
		catch (Exception e) {
			out.print(e.getMessage());
		}
	}

}
