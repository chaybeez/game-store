import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Review")
public class Review extends HttpServlet {
	private String error_msg;
	String productmodelname = "TODO";
	String productcategory = "TODO";
	String productprice = "TODO";
	String productonsale = "TODO";
	String manufacturername = "TODO";
	String manufacturerrebate = "TODO";
	String retailername = "GameSpeed";
	String retailerzip = "60616";
	String retailercity = "Chicago";
	String retailerstate = "IL";	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productmodelname = request.getParameter("name");
	    productcategory = request.getParameter("type");
		manufacturername = request.getParameter("maker");
				
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayReview(request, response, pw, false);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String userid = request.getParameter("userid");
		String userage = request.getParameter("userage");
		String usergender = request.getParameter("usergender");
		String useroccupation = request.getParameter("useroccupation");
		String reviewrating = request.getParameter("reviewrating");
		String reviewdate = request.getParameter("reviewdate");
		String reviewtext = request.getParameter("reviewtext");
		
		String k = manufacturername + productmodelname + productcategory;
		String v = "<!DOCTYPE html> <html> <body>"		
				+ "<h1>" + "Product Reviews" + "</h1>"
				+ "<p style='color:black'>"
				+ "1. Product Model Name: " + productmodelname + "<br>"
				+ "2. Product Category: " + productcategory + "<br>"
				+ "3. Product Price: " + productprice + "<br>"
				+ "4. Retailer Name: " + retailername + "<br>"
				+ "5. Retailer Zip: " + retailerzip + "<br>"
				+ "6. Retailer City: " + retailercity + "<br>"
				+ "7. Retailer State: " + retailerstate + "<br>"
				+ "8. Product On Sale: " + productonsale + "<br>"
				+ "9. Manufacturer Name: " + manufacturername + "<br>"
				+ "10. Manufacturer Rebate: " + manufacturerrebate + "<br>"
				+ "11. User ID: " + userid + "<br>"
				+ "12. User Age: " + userage + "<br>"
				+ "13. User Gender: " + usergender + "<br>"
				+ "14. User Occupation: " + useroccupation + "<br>"
				+ "15. Review Rating: " + reviewrating + "<br>"
				+ "16. Review Date: " + reviewdate + "<br>"
				+ "17. Review Text: " + reviewtext + "<br>"
				+ "</p> </body> </html>";
	
		if (ReviewsHashMap.reviews.get(k) == null){
			ArrayList<String> revs = new ArrayList<String>();
			revs.add(v);
			ReviewsHashMap.reviews.put(k, revs);
		} else {
			ArrayList<String> revs = ReviewsHashMap.reviews.get(k);
			revs.add(v);
			ReviewsHashMap.reviews.put(k, revs);
		}
		
		displayReview(request, response, pw, false);
	}
		
	
	
	protected void displayReview(HttpServletRequest request, 
			HttpServletResponse response, PrintWriter pw, boolean error) 
			throws ServletException, IOException {

		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Product Review</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");

		if (ReviewsHashMap.reviews.get(manufacturername + productmodelname + productcategory) != null) {
			ArrayList<String> revs = ReviewsHashMap.reviews.get(manufacturername + productmodelname + productcategory);
			for (String r: revs){
				pw.print("<h2 style='color:black'>" + r + "</h2>");
			}
		} 
		else {pw.print("");}
				
				
				
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<form method='post' action='Review'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>UserID</h3></td><td><input type='text' name='userid' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>UserAge</h3></td><td><input type='text' name='userage' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>UserGender</h3></td><td><input type='text' name='usergender' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>UserOccupation</h3></td><td><input type='text' name='useroccupation' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>ReviewRating</h3></td><td><input type='text' name='reviewrating' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>ReviewDate</h3></td><td><input type='text' name='reviewdate' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>ReviewText</h3></td><td><input type='text' name='reviewtext' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Create Review' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
		helper.printHtml("site_footer.html");
	}
	
		
}

