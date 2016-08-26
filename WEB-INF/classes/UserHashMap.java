import java.util.HashMap;


public class UserHashMap {
	public static HashMap<String, User> customer = new HashMap<String, User>();
	public static HashMap<String, User> salesman = new HashMap<String, User>();
	public static HashMap<String, User> manager = new HashMap<String, User>();
	
	public UserHashMap(){
		if(customer.isEmpty()){
			User user = new User("customer","customer","customer");
			customer.put("customer",user);
		}
		if(salesman.isEmpty()){
			User user = new User("salesman","salesman","salesman");
			salesman.put("salesman",user);		
		}
		if(manager.isEmpty()){
			User user = new User("manager","manager","manager");
			manager.put("manager",user);
		}
	}

}
