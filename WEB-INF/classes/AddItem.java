import java.util.Random;
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

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	
	private String error_msg = "Sorry, try again later.";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		
		String type = request.getParameter("type").toString();
		String name = request.getParameter("name").toString();
		Double price = Double.parseDouble(request.getParameter("price"));
		String retailer = request.getParameter("retailer").toString();
		String condition = request.getParameter("condition").toString();
		Double discount = Double.parseDouble(request.getParameter("discount"));
		
		if (name!=null || price!=null || retailer!=null || condition!=null || discount!=null){
			if (type.equals("tablet")){
				if (retailer.equals("apple")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Tablet val = new Tablet(name, price, "ics.png", retailer, condition, discount);
					TabletHashMap.apple.put(key, val);					
				}
				if (retailer.equals("microsoft")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Tablet val = new Tablet(name, price, "ics.png", retailer, condition, discount);
					TabletHashMap.microsoft.put(key, val);
				}
				if (retailer.equals("samsung")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Tablet val = new Tablet(name, price, "ics.png", retailer, condition, discount);
					TabletHashMap.samsung.put(key, val);
				}
			}
			if (type.equals("game")){
				if (retailer.equals("electronicArts")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Game val = new Game(name, price, "ics.png", retailer, condition, discount);
					GameHashMap.electronicArts.put(key, val);					
				}
				if (retailer.equals("activision")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Game val = new Game(name, price, "ics.png", retailer, condition, discount);
					GameHashMap.activision.put(key, val);					
				}
				if (retailer.equals("takeTwoInteractive")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					Game val = new Game(name, price, "ics.png", retailer, condition, discount);
					GameHashMap.takeTwoInteractive.put(key, val);					
				}
			}
			if (type.equals("console")){
					if (retailer.equals("microsoft")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
					Console val = new Console(name, price, "ics.png", retailer, condition, discount, accessories);
					ConsoleHashMap.microsoft.put(key, val);					
				}
				if (retailer.equals("nintendo")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
					Console val = new Console(name, price, "ics.png", retailer, condition, discount, accessories);
					ConsoleHashMap.nintendo.put(key, val);					
				}
				if (retailer.equals("sony")){
					Random rand = new Random(); 
					String key = String.valueOf(rand.nextInt(10000) + 5000);
					HashMap<String,Accessory> accessories = new HashMap<String,Accessory>();
					Console val = new Console(name, price, "ics.png", retailer, condition, discount, accessories);
					ConsoleHashMap.sony.put(key, val);					
				}
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		
		/*
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'> parameters: " + name + price + retailer + condition + discount + "</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		
		pw.print("</div>");		
		*/
		response.sendRedirect("Home"); return;
	}
}