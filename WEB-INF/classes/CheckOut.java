import java.sql.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private String error_msg;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		displayCheckOut(request, response, pw, false);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		if (helper.getCustomerOrders() == null){response.sendRedirect("Home");}
		String username = helper.username();
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String ccnumber = request.getParameter("ccnumber");
		String ccexp = request.getParameter("ccexp");

		String orderdate = helper.currentDate();
		Random rand = new Random(); 
		String orderid = String.valueOf(rand.nextInt(10000) + 5000);
		Connection connection = null;
		
		ArrayList<OrderItem> cartConts = helper.getCustomerOrders();
				
		//The old HashMap method from HW2...
		/*
		CompletedOrder co = new CompletedOrder(username, orderdate, deldate, orderid, helper.getCustomerOrders());
			if (CompletedOrdersHashMap.orders.get(username) == null){
				ArrayList<CompletedOrder> cohm = new ArrayList<CompletedOrder>();
				cohm.add(co);
				CompletedOrdersHashMap.orders.put(username, cohm);
			} else {
				ArrayList<CompletedOrder> cohm = CompletedOrdersHashMap.orders.get(username);
				cohm.add(co);
				CompletedOrdersHashMap.orders.put(username, cohm);
			}
		*/
		// New MySQL storage for completed orders...
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			String dbURL = "jdbc:mysql://localhost:3306/gamespeed";
			String usn = "root";
			String pwd = "";
			connection = DriverManager.getConnection(dbURL, usn, pwd);
			
			for (OrderItem oi : cartConts){
				String preparedQuery = "INSERT INTO CompletedOrders "
				+ "(Username, OrderDate, DelDate, OrderID, OrderName, OrderPrice, OrderImage, OrderRetailer)"
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = connection.prepareStatement(preparedQuery);
				ps.setString(1, username);
				ps.setString(2, orderdate);
				ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now().plusWeeks(2)));
				ps.setString(4, orderid);
				ps.setString(5, oi.getName());
				ps.setDouble(6, oi.getPrice());
				ps.setString(7, oi.getImage());
				ps.setString(8, oi.getRetailer());
				ps.executeUpdate();
			}
		} catch(SQLException e) {
			for (Throwable t : e)
				t.printStackTrace();
		}
				
		helper.clearCustomerOrders();
		pw.print("<a style='font-size: 24px;'>Checkout Complete - Thank-You!</a>");			
		response.sendRedirect("Account");
		HttpSession session = request.getSession(true);	
		session.setAttribute("ord_msg", "Order Complete");
	}
	
	protected void displayCheckOut(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error) throws ServletException, IOException {
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Checkout</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		String un = helper.username();
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Check-Out");
			response.sendRedirect("Login");
			return;
		}
				
		if(helper.CartCount()>0){
			int i = 1;
			double total = 0;
			for (OrderItem oi : helper.getCustomerOrders()) {
				total = total +oi.getPrice();
				i++;
		}
		//pw.print("<tr><th></th><th>Order Total: $</th><th>" + total + "</th>");
		pw.print("<h3 style='color:black'>Order Total: $" + total + "<br><br></h3>");
		//pw.print("<tr><td></td><td></td><td><a href='CheckOut' class='btnbuy'>Check Out</a></td>");
		//pw.print("</table>");
		}else{
			pw.print("<h4 style='color:black'>Your Cart is empty!</h4>");
		}
		pw.print("<form method='post' action='CheckOut'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Full Name</h3></td><td><input type='text' name='fullname' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Address</h3></td><td><input type='text' name='address' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>City</h3></td><td><input type='text' name='city' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>State</h3></td><td><input type='text' name='state' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>ZIP</h3></td><td><input type='text' name='zip' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>CCNumber</h3></td><td><input type='text' name='ccnumber' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>CCExp</h3></td><td><input type='text' name='ccexp' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"				
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='CheckOut' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
				helper.printHtml("site_footer.html");
			
	}	
}