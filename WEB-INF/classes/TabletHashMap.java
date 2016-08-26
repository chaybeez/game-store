import java.util.HashMap;

public class TabletHashMap {
	public static HashMap<String, Tablet> apple = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> microsoft = new HashMap<String, Tablet>();
	public static HashMap<String, Tablet> samsung = new HashMap<String, Tablet>();
	
	public static String string_apple = "Apple";
	public static String string_microsoft = "Microsoft";
	public static String string_samsung = "Samsung";
	
	public TabletHashMap() {
		
		if(apple.isEmpty()){
			Tablet ipad = new Tablet("Apple iPad", 399.99, "apple_ipad.jpg", "Apple", "New", 10);
			apple.put("ipad", ipad);
		}
		
		if(microsoft.isEmpty()){
			Tablet surface = new Tablet("Microsoft Surface", 399.99, "microsoft_surface.jpg", "Microsoft", "New", 10);
			microsoft.put("surface", surface);
		}
		
		if(samsung.isEmpty()){
			Tablet galaxy = new Tablet("Samsung Galaxy", 299.99, "samsung_galaxy.jpg", "Samsung", "New", 10);
			samsung.put("galaxy", galaxy);
		}
	}
}
