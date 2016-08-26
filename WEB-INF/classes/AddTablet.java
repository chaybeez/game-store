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

@WebServlet("/AddTablet")
public class AddTablet extends HttpServlet {
	
	private String error_msg = "Whoops!";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		if(!helper.isLoggedin()){
			response.sendRedirect("Login");
			return;
		}
		else {
			HttpSession session = request.getSession();				
			session.setAttribute("login_msg", error_msg);
			displayTablets(request, response, pw, true);
		}	
	}
	
	protected void displayTablets(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error) throws ServletException, IOException {
		Helper helper = new Helper(request, pw);
		String un = helper.username();
		String usertype = helper.usertype();
		response.setContentType("text/html");
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>GameSpeed Tablet Inventory</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (!usertype.equals("manager")){
			pw.print("<table  class='gridtable'>");
				pw.print("<h2 style='color:black'>" + "You're not authorized to access this page." + "</h2>");
				pw.print("<table></div></div></div>");		
				helper.printHtml("site_footer.html");
		} else {
			pw.print("<table  class='gridtable'>");
				for (Tablet t : TabletHashMap.apple.values()){
					pw.print("<h5 style='color:black'>Name: " + t.getName() + "</h5>" +
					"<h5 style='color:black'>Price: " + t.getPrice() + "</h5>" +
					"<h5 style='color:black'>Condition: " + t.getCondition() + "</h5>" +
					"<h5 style='color:black'>Discount: " + t.getDiscount() + "</h5>" +
					"<h5 style='color:black'>Retailer: " + t.getRetailer() + "</h5>" +
					"</div></div></div>");
					pw.print("<form method='post' action='DeleteItem'>" +
					"<input type='hidden' name='type' value='tablet'>" +
					"<input type='hidden' name='name' value='"+t.getName()+"'>" +
					"<input type='hidden' name='condition' value='"+t.getCondition()+"'>" +
					"<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>" +
					"<input type='submit' class='btnbuy' value='Delete Item' style='float: left;height: 20px margin: 20px; margin-right: 10px; '></input></form><br><br>");
				}
				for (Tablet t : TabletHashMap.microsoft.values()){
					pw.print("<h5 style='color:black'>Name: " + t.getName() + "</h5>" +
					"<h5 style='color:black'>Price: " + t.getPrice() + "</h5>" +
					"<h5 style='color:black'>Condition: " + t.getCondition() + "</h5>" +
					"<h5 style='color:black'>Discount: " + t.getDiscount() + "</h5>" +
					"<h5 style='color:black'>Retailer: " + t.getRetailer() + "</h5>" +
					"</div></div></div>");
					pw.print("<form method='post' action='DeleteItem'>" +
					"<input type='hidden' name='type' value='tablet'>" +
					"<input type='hidden' name='name' value='"+t.getName()+"'>" +
					"<input type='hidden' name='condition' value='"+t.getCondition()+"'>" +
					"<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>" +
					"<input type='submit' class='btnbuy' value='Delete Item' style='float: left;height: 20px margin: 20px; margin-right: 10px; '></input></form><br><br>");
				}
				for (Tablet t : TabletHashMap.samsung.values()){
					pw.print("<h5 style='color:black'>Name: " + t.getName() + "</h5>" +
					"<h5 style='color:black'>Price: " + t.getPrice() + "</h5>" +
					"<h5 style='color:black'>Condition: " + t.getCondition() + "</h5>" +
					"<h5 style='color:black'>Discount: " + t.getDiscount() + "</h5>" +
					"<h5 style='color:black'>Retailer: " + t.getRetailer() + "</h5>" +
					"</div></div></div>");
					pw.print("<form method='post' action='DeleteItem'>" +
					"<input type='hidden' name='type' value='tablet'>" +
					"<input type='hidden' name='name' value='"+t.getName()+"'>" +
					"<input type='hidden' name='condition' value='"+t.getCondition()+"'>" +
					"<input type='hidden' name='retailer' value='"+t.getRetailer()+"'>" +
					"<input type='submit' class='btnbuy' value='Delete Item' style='float: left;height: 20px margin: 20px; margin-right: 10px; '></input></form><br><br>");
				}
		pw.print("</table><br><br>");
		
		pw.print("<form method='post' action='AddItem'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<input type='hidden' name='type' value='tablet'>"
				+ "<h3>Name</h3></td><td><input type='text' name='name' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Price</h3></td><td><input type='text' name='price' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Manufacturer</h3></td><td>"
				+ "<select name='retailer'>"
				+ "<option value='apple' selected>Apple</option><option value='microsoft'>Microsoft</option><option value='samsung'>Samsung</option>"
				+ "<option value='electronicArts' selected>Electronic Arts</option><option value='activision'>Activision</option><option value='takeTwoInteractive'>Take Two Interactive</option>"
				+ "<option value='sony' selected>Sony</option><option value='nintendo'>Nintendo</option></select>"		
				+ "</td></tr><tr><td>"
				+ "<h3>Condition</h3></td><td>"
				+ "<select name='condition'>"
				+ "<option value='new' selected>New</option><option value='used'>Used</option><option value='refurbished'>Refurbished</option></select>"
				+ "</td></tr><tr><td>"
				+ "<h3>Discount</h3></td><td><input type='text' name='discount' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='AddItem' value='Add Item' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></form>");
				helper.printHtml("site_footer.html");
				
		
		}
	}
}	
		
