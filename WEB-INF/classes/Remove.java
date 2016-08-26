import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Remove")
public class Remove extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Helper helper = new Helper(request, null);
		ArrayList<OrderItem> ois = helper.getCustomerOrders();
		ArrayList<OrderItem> copy = new ArrayList<OrderItem>(ois);
		String rem = request.getParameter("Remove");
		if (ois != null){
		for (OrderItem oi : ois) {
			if (oi.getName().equals(rem)) {copy.remove(oi);}
				}
			}
		OrdersHashMap.orders.put(helper.username(), copy);
		response.sendRedirect("Cart");
	}
}