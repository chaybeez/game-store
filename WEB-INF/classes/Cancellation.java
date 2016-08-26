import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/Cancellation")
public class Cancellation extends HttpServlet {
	private String error_msg = "Sorry, try again later.";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date now = new Date();
		Connection connection = null;
		String un = helper.username();
		String toCancel = request.getParameter("oid").toString();
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Username: " + un + "'s " + "cancellation Request</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		pw.print("<h3 style='color:black'>" + un + "'s Account Page - Cancelling OrderID: " + toCancel + "<br><br></h3>");
		if (toCancel != null){
			//New logic for SQL goes here...
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String dbURL = "jdbc:mysql://localhost:3306/gamespeed";
			String usn = "root";
			String pwd = "";
			connection = DriverManager.getConnection(dbURL, usn, pwd);
			
			String query = "DELETE FROM CompletedOrders " +
			"WHERE OrderID = '" + toCancel + "'";
			Statement statement = connection.createStatement();
			int rowCount = statement.executeUpdate(query);	
		
		
		} catch(SQLException e) {
			for (Throwable t : e)
				t.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		}
		pw.print("</div></div></div>");		
		helper.printHtml("site_footer.html");
		response.sendRedirect("Account"); return;
	}
}