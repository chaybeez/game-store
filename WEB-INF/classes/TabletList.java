import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TabletList")

public class TabletList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
		
		// To display individual search results...
		if (request.getParameter("key") != null){
			String key = request.getParameter("key");
			String retailer = request.getParameter("retailer");
			Tablet res = null;
			if(retailer.equals("Apple")){
				hm.putAll(TabletHashMap.apple);
				res = hm.get(key);
			}
			else if(retailer.equals("Samsung")){
				hm.putAll(TabletHashMap.samsung);
				res = hm.get(key);
			}
			else if(retailer.equals("Microsoft")){
				hm.putAll(TabletHashMap.microsoft);
				res = hm.get(key);
			}
			helper.printHtml("site_header.html");
			helper.printHtml("site_sidebar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + retailer + " Tablets</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		pw.print("<tr>");
		pw.print("<td><div id='shop_item'>");
		pw.print("<h3>" + res.getName() + "</h3>");
		pw.print("<strong> $" + res.getPrice() + "</strong><ul>");
		pw.print("<li id='item'><img src='images/tablets/" + res.getImage() + "' alt='' /></li>");
		pw.print("<li><form method='post' action='Cart'>" +
			"<input type='hidden' name='name' value='"+key+"'>"+
			"<input type='hidden' name='type' value='tablets'>"+
			"<input type='hidden' name='maker' value='"+retailer+"'>"+
			"<input type='hidden' name='access' value=''>"+
			"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
		pw.print("<li><a class='btnreview' href='Review?name="+key+"&type=tablets&maker="+retailer+"&access='>Reviews</a></li>");
		pw.print("</ul></div></td>");
		pw.print("</tr>");
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		return;
		}
		
		//To display all results...
		if (CategoryName == null) {
			hm.putAll(TabletHashMap.apple);
			hm.putAll(TabletHashMap.microsoft);
			hm.putAll(TabletHashMap.samsung);
			name = "";
		} else {
			if (CategoryName.equals("apple")) {
				hm.putAll(TabletHashMap.apple);
				name = TabletHashMap.string_apple;
			} else if (CategoryName.equals("microsoft")) {
				hm.putAll(TabletHashMap.microsoft);
				name = TabletHashMap.string_microsoft;
			} else if (CategoryName.equals("samsung")) {
				hm.putAll(TabletHashMap.samsung);
				name = TabletHashMap.string_samsung;
			}
		}

		
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " Tablets</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Tablet> entry : hm.entrySet()) {
			Tablet Tablet = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + Tablet.getName() + "</h3>");
			pw.print("<strong> $" + Tablet.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/tablets/"
					+ Tablet.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tablets'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><a class='btnreview' href='Review?name="+entry.getKey()+"&type=tablets&maker="+CategoryName+"&access='>Reviews</a></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		helper.printHtml("site_footer.html");
	}
}
