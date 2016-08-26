import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet("/Account")
public class Account extends HttpServlet {
	
	private String error_msg = "Whoops!";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		if(!helper.isLoggedin()){response.sendRedirect("Login"); return;}
		else{
			HttpSession session = request.getSession();				
			session.setAttribute("login_msg", error_msg);
			displayAccount(request, response, pw, true);
		}	
	}
	
	
	protected void displayAccount(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error) throws ServletException, IOException {
		Connection connection = null;
		Helper helper = new Helper(request, pw);
		String un = helper.username();
		String usertype = helper.usertype();
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>" + un + "'s " + "Account Page</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
			
		//	CUSTOMER	//
		if (usertype.equals("customer")){
			
			try {
			String dbURL = "jdbc:mysql://localhost:3306/gamespeed";
			String usn = "root";
			String pwd = "";
			connection = DriverManager.getConnection(dbURL, usn, pwd);
			
			String preparedSQL = "SELECT * "
			+ "FROM CompletedOrders WHERE Username = ? "
			+ "ORDER BY ID;";
			PreparedStatement ps = connection.prepareStatement(preparedSQL);
			ps.setString(1, un);
			ResultSet product = ps.executeQuery();
			double tprice = 0;
			String oid = null;
			boolean userIDExists = product.next();
			
			if (userIDExists == false){
				pw.print("<table  class='gridtable'>");
				pw.print("<h2 style='color:black'>" + "No Orders! Why don't you go buy something?!?!" + "</h2>");
				pw.print("</table></div></div></div>");		
				helper.printHtml("site_footer.html");
				return;

			} else {
				pw.print("<h8 style='color:black'>Your Orders:</h8>");
				oid = product.getString("OrderID");
				tprice = tprice + product.getDouble("OrderPrice");	
				pw.print("<table  class='gridtable'>");
				pw.print("<tr><td><h4 style='color:black'> Order ID Number: " + oid + "</td>" +
				"<td>Date of order: " + product.getString("OrderDate") + "</td></tr>" +
				"<tr><td>Estimated Delivery Date:</td><td>" + product.getDate("DelDate") + "</td></tr>" +
				"<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
					
				while (product.next()) {
					if (!oid.equals(product.getString("OrderID"))){
						pw.print("<tr><td><h4 style='color:black'> Order Total: $"+tprice+"</td></h4>");
						pw.print("<td><form method='post' action='Cancellation'>" +
						"<input type='hidden' name='oid' value='"+oid+"'>" +
						"<input type='submit' class='btnbuy' value='Cancel Order' style='float: left;height: 10px margin: 10px; margin-right: 10px; '></input></form></td></tr><br><br>");
						tprice = product.getDouble("OrderPrice");
						oid = product.getString("OrderID");
						pw.print("<table  class='gridtable'>");
						pw.print("<tr><td><h4 style='color:black'> Order ID Number: " + oid + "</td>" +
						"<td>Date of order: " + product.getString("OrderDate") + "</td></tr>" +
						"<tr><td>Estimated Delivery Date:</td><td>" + product.getDate("DelDate") + "</td></tr>" +
						"<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
	
					} else {
					pw.print("<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
					tprice = tprice + product.getDouble("OrderPrice");
					}
				}
					
				pw.print("<tr><td>Order Total: $"+tprice+"</td><br><br>");
				pw.print("<td><form method='post' action='Cancellation'>" +
						"<input type='hidden' name='oid' value='"+oid+"'>" +
						"<input type='submit' class='btnbuy' value='Cancel Order' style='float: left;height: 10px margin: 10px; margin-right: 10px; '></input></form></td></tr><br><br>");
				pw.print("</table></div></div></div>");
				helper.printHtml("site_footer.html");
				}
			
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
		
		//	SALESMAN	//
		if (usertype.equals("salesman")){
			
			pw.print("<h2 style='color:black'>" + "Create Customer User Account:" + "</h2>");
			pw.print("<form method='post' action='AddUser'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Name</h3></td><td><input type='text' name='name' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='text' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><input type='text' name='usertype' value='customer' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='AddUser' value='Add User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></form><br><br><br><br><br><br><br><br>");
						
		try {
			String dbURL = "jdbc:mysql://localhost:3306/gamespeed";
			String usn = "root";
			String pwd = "";
			connection = DriverManager.getConnection(dbURL, usn, pwd);
			
			String preparedSQL = "SELECT * "
			+ "FROM CompletedOrders "
			+ "ORDER BY ID;";
			PreparedStatement ps = connection.prepareStatement(preparedSQL);
			ResultSet product = ps.executeQuery();
			double tprice = 0;
			String oid = null;
			boolean userIDExists = product.next();
			
			if (userIDExists == false){
				pw.print("<table  class='gridtable'>");
				pw.print("<h2 style='color:black'>" + "No Customer Orders!" + "</h2>");
				pw.print("</table></div></div></div>");		
				helper.printHtml("site_footer.html");
				return;

			} else {
				pw.print("<h2 style='color:black'>" + "Customer Orders:" + "</h2>");
				oid = product.getString("OrderID");
				tprice = tprice + product.getDouble("OrderPrice");	
				pw.print("<table  class='gridtable'>");
				pw.print("<tr><td><h4 style='color:black'> Order ID Number: " + oid + "</td>" +
				"<td>Date of order: " + product.getString("OrderDate") + "</td></tr>" +
				"<tr><td>User Name:</td><td>" + product.getString("Username") +"</td></tr>" +
				"<tr><td>Estimated Delivery Date:</td><td>" + product.getDate("DelDate") + "</td></tr>" +
				"<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
					
				while (product.next()) {
					if (!oid.equals(product.getString("OrderID"))){
						pw.print("<tr><td><h4 style='color:black'> Order Total: $"+tprice+"</td></h4>");
						pw.print("<td><form method='post' action='Cancellation'>" +
						"<input type='hidden' name='oid' value='"+oid+"'>" +
						"<input type='submit' class='btnbuy' value='Cancel Order' style='float: left;height: 10px margin: 10px; margin-right: 10px; '></input></form></td></tr><br><br>");
						tprice = product.getDouble("OrderPrice");
						oid = product.getString("OrderID");
						pw.print("<table  class='gridtable'>");
						pw.print("<tr><td><h4 style='color:black'> Order ID Number: " + oid + "</td>" +
						"<td>Date of order: " + product.getString("OrderDate") + "</td></tr>" +
						"<tr><td>User Name:</td><td>" + product.getString("Username") +"</td></tr>" +
						"<tr><td>Estimated Delivery Date:</td><td>" + product.getDate("DelDate") + "</td></tr>" +
						"<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
	
					} else {
					pw.print("<tr><td>"+product.getString("OrderName")+"</td><td>Price: $"+product.getDouble("OrderPrice")+"</td></tr>");
					tprice = tprice + product.getDouble("OrderPrice");
					}
				}
					
				pw.print("<tr><td>Order Total: $"+tprice+"</td><br><br>");
				pw.print("<td><form method='post' action='Cancellation'>" +
						"<input type='hidden' name='oid' value='"+oid+"'>" +
						"<input type='submit' class='btnbuy' value='Cancel Order' style='float: left;height: 10px margin: 10px; margin-right: 10px; '></input></form></td></tr><br><br>");
				pw.print("</table></div></div></div><br><br>");
				helper.printHtml("site_footer.html");
				}
			
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
		//	MANAGER	//
		else if (usertype.equals("manager")){
			pw.print("<form method='get' action='AddConsole'>" +
			"<input type='submit' class='btnbuy' name='addconsole' value='Add or Delete Consoles'></input></form>");
			
			pw.print("<form method='get' action='AddGame'>" +
			"<input type='submit' class='btnbuy' name='addgame' value='Add or Delete Games'></input></form>");
			
			pw.print("<form method='get' action='AddTablet'>" +
			"<input type='submit' class='btnbuy' name='addtablet' value='Add or Delete Tablets'></input></form>");
			
			
			helper.printHtml("site_footer.html");
	}	
	}
}