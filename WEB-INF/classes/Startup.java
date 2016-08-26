import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Startup")
public class Startup extends HttpServlet{
	
	public void init() throws ServletException{
		Connection connection = null;
		new ConsoleHashMap();
		new GameHashMap();
		new UserHashMap();
		new TabletHashMap();
		new ReviewsHashMap();
		new OrdersHashMap();
		//new CompletedOrdersHashMap();
		
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
			
			//Initializing the database each time server is started - this can be changed later, but is here for testing purposes...
			String preparedQuery = "DROP TABLE IF EXISTS CompletedOrders;";
			PreparedStatement ps = connection.prepareStatement(preparedQuery);
			ps.executeUpdate();
			preparedQuery = "CREATE table CompletedOrders ("
			+ "ID INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,"
			+ "Username VARCHAR(50) NOT NULL,"
			+ "OrderDate VARCHAR(50) NOT NULL,"
			+ "DelDate DATE NOT NULL,"
			+ "OrderID VARCHAR(50) NOT NULL,"
			+ "OrderName VARCHAR(50) NOT NULL,"
			+ "OrderPrice DOUBLE NOT NULL,"
			+ "OrderImage VARCHAR(50) NOT NULL,"
			+ "OrderRetailer VARCHAR(50) NOT NULL"
			+ ");";
			ps = connection.prepareStatement(preparedQuery);
			ps.executeUpdate();
			
		} catch(SQLException e) {
			for (Throwable t : e)
				t.printStackTrace();
		}
		
    }
}
