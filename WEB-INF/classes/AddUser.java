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

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	
	private String error_msg = "Sorry, try again later.";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		helper.printHtml("site_header.html");
		
		String name = request.getParameter("name").toString();
		String password = request.getParameter("password").toString();
		String usertype = request.getParameter("usertype").toString();
		
		if (name!=null && password!=null && usertype!=null){
			User user = new User(name, password, usertype);
			UserHashMap.customer.put(name, user);			
		}
		response.sendRedirect("Account"); return;
	}
}
