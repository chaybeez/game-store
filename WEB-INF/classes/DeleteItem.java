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

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	
	private String error_msg = "Whoops!";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>GameSpeed Tablet Inventory</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if(!helper.isLoggedin()){
			response.sendRedirect("Login");
			return;
		}
		if (!helper.usertype().equals("manager")){
			pw.print("<table  class='gridtable'>");
				pw.print("<h2 style='color:black'>" + "You're not authorized to access this page." + "</h2>");
				pw.print("<table></div></div></div>");		
				helper.printHtml("site_footer.html");
		} else {
		
			String type = request.getParameter("type").toString().toLowerCase();
			String name = request.getParameter("name").toString().toLowerCase();
			String condition = request.getParameter("condition").toString().toLowerCase();
			String retailer = request.getParameter("retailer").toString().toLowerCase();
			
			//pw.print("<h2 style='color:black'>"+type+name+condition+retailer+"</h2>");
			
			if (type.equals("console")){
				if (retailer.equals("microsoft")){
					HashMap<String, Console> hm = new HashMap<String, Console>();
					HashMap<String, Console> copyhm = new HashMap<String, Console>();
					hm.putAll(ConsoleHashMap.microsoft);
					copyhm.putAll(ConsoleHashMap.microsoft);
					for (Console c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							ConsoleHashMap.microsoft.clear();
							ConsoleHashMap.microsoft.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("nintendo")){
					HashMap<String, Console> hm = new HashMap<String, Console>();
					HashMap<String, Console> copyhm = new HashMap<String, Console>();
					hm.putAll(ConsoleHashMap.nintendo);
					copyhm.putAll(ConsoleHashMap.nintendo);
					for (Console c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							ConsoleHashMap.nintendo.clear();
							ConsoleHashMap.nintendo.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("sony")){
					HashMap<String, Console> hm = new HashMap<String, Console>();
					HashMap<String, Console> copyhm = new HashMap<String, Console>();
					hm.putAll(ConsoleHashMap.sony);
					copyhm.putAll(ConsoleHashMap.sony);
					for (Console c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							ConsoleHashMap.sony.clear();
							ConsoleHashMap.sony.putAll(copyhm);
						}
					}
				}
			}
			if (type.equals("game")){
				if (retailer.equals("electronic arts")){
					HashMap<String, Game> hm = new HashMap<String, Game>();
					HashMap<String, Game> copyhm = new HashMap<String, Game>();
					hm.putAll(GameHashMap.electronicArts);
					copyhm.putAll(GameHashMap.electronicArts);
					for (Game c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals("electronic arts") && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							GameHashMap.electronicArts.clear();
							GameHashMap.electronicArts.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("activision")){
					HashMap<String, Game> hm = new HashMap<String, Game>();
					HashMap<String, Game> copyhm = new HashMap<String, Game>();
					hm.putAll(GameHashMap.activision);
					copyhm.putAll(GameHashMap.activision);
					for (Game c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals("activision") && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							GameHashMap.activision.clear();
							GameHashMap.activision.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("take-two interactive")){
					HashMap<String, Game> hm = new HashMap<String, Game>();
					HashMap<String, Game> copyhm = new HashMap<String, Game>();
					hm.putAll(GameHashMap.takeTwoInteractive);
					copyhm.putAll(GameHashMap.takeTwoInteractive);
					for (Game c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals("take-two interactive") && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							GameHashMap.takeTwoInteractive.clear();
							GameHashMap.takeTwoInteractive.putAll(copyhm);
						}
					}
				}
			}
			if (type.equals("tablet")){
				if (retailer.equals("apple")){
					HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
					HashMap<String, Tablet> copyhm = new HashMap<String, Tablet>();
					hm.putAll(TabletHashMap.apple);
					copyhm.putAll(TabletHashMap.apple);
					for (Tablet c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							TabletHashMap.apple.clear();
							TabletHashMap.apple.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("microsoft")){
					HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
					HashMap<String, Tablet> copyhm = new HashMap<String, Tablet>();
					hm.putAll(TabletHashMap.microsoft);
					copyhm.putAll(TabletHashMap.microsoft);
					for (Tablet c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							TabletHashMap.microsoft.clear();
							TabletHashMap.microsoft.putAll(copyhm);
						}
					}
				}
				if (retailer.equals("samsung")){
					HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
					HashMap<String, Tablet> copyhm = new HashMap<String, Tablet>();
					hm.putAll(TabletHashMap.samsung);
					copyhm.putAll(TabletHashMap.samsung);
					for (Tablet c : hm.values()){
						if (c.getName().toLowerCase().equals(name) && c.getRetailer().toLowerCase().equals(retailer) && c.getCondition().toLowerCase().equals(condition)){
							copyhm.values().remove(c);
							TabletHashMap.samsung.clear();
							TabletHashMap.samsung.putAll(copyhm);
						}
					}
				}
			}
		}	
	response.sendRedirect("Home"); return;
	}
}