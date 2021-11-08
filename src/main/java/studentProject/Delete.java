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

@WebServlet("/Delete")
public class Delete extends HttpServlet
	{
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
		{
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
		
			String stdid = req.getParameter("id");
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
		
				PreparedStatement ps = con.prepareStatement("delete from student where id = ?");
				ps.setString(1, stdid);
			
				int k = ps.executeUpdate();
			
				out.println("<font color='orange'> Record Deleted Successfully </font>");
				con.close();
			}
				catch (Exception e) {
					out.println("<font color='red'> Record Failed </font>");
				}
		}
	}
