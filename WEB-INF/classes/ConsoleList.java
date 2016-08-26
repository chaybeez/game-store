import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConsoleList")
public class ConsoleList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		String name = null;
		Console res = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Console> hm = new HashMap<String, Console>();
		
		// To display individual search results...
		if (request.getParameter("key") != null){
			String key = request.getParameter("key");
			String retailer = request.getParameter("retailer");
			
			if(retailer.equals("Microsoft")){
				hm.putAll(ConsoleHashMap.microsoft);
				res = hm.get(key);
			}
			else if(retailer.equals("Sony")){
				hm.putAll(ConsoleHashMap.sony);
				res = hm.get(key);
			}
			else if(retailer.equals("Nintendo")){
				hm.putAll(ConsoleHashMap.nintendo);
				res = hm.get(key);
			}
			helper.printHtml("site_header.html");
			helper.printHtml("site_sidebar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>"+res.getRetailer()+" Consoles</a>");
			pw.print("</h2><div class='entry'><table id='bestseller'>");
			pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+res.getName()+"</h3>");
			pw.print("<strong>$"+res.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/consoles/"+res.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+key+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+retailer+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><a class='btnbuy' href='AccessoryList?maker="+retailer+"&console="+key+"'>View Accessories</a></li>");
			pw.print("<li><a class='btnreview' href='Review?name="+key+"&type=consoles&maker="+retailer+"&access='>Reviews</a></li>");
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			pw.print("</table></div></div></div>");		
			helper.printHtml("site_footer.html");
			return;
		}
		
		//To display all results...
		if(CategoryName==null){
			hm.putAll(ConsoleHashMap.microsoft);
			hm.putAll(ConsoleHashMap.sony);
			hm.putAll(ConsoleHashMap.nintendo);
			name = "";
		}else{
			if(CategoryName.equals("microsoft")){
				hm.putAll(ConsoleHashMap.microsoft);
				name = ConsoleHashMap.string_microsoft;
			}
			else if(CategoryName.equals("sony")){
				hm.putAll(ConsoleHashMap.sony);
				name = ConsoleHashMap.string_sony;
			}
			else if(CategoryName.equals("nintendo")){
				hm.putAll(ConsoleHashMap.nintendo);
				name = ConsoleHashMap.string_nintendo;
			}
		}
		
		
		helper.printHtml("site_header.html");
		helper.printHtml("site_sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Console> entry : hm.entrySet()){
			Console console = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+console.getName()+"</h3>");
			pw.print("<strong>$"+console.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/consoles/"+console.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><a class='btnbuy' href='AccessoryList?maker="+CategoryName+"&console="+entry.getKey().toString()+"'>View Accessories</a></li>");
			pw.print("<li><a class='btnreview' href='Review?name="+entry.getKey()+"&type=consoles&maker="+CategoryName+"&access='>Reviews</a></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		helper.printHtml("site_footer.html");
		
	}
}
