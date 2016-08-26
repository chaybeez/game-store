import java.util.HashMap;

public class ConsoleHashMap{
	public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	public static HashMap<String, Console> sony = new HashMap<String, Console>();
	public static HashMap<String, Console> nintendo = new HashMap<String, Console>();
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";
	
	public ConsoleHashMap() {
		HashMap<String, Accessory> accessories;
		if(microsoft.isEmpty()){
			Accessory xboxone_wc = new Accessory("Controller", 40.99, "XBOX controller.jpg", "Microsoft","New",10);
			Accessory xboxone_wr = new Accessory("XBOX ONE Warranty", 40.99, "ics.png", "Microsoft","New",10);
			Accessory xboxone_sh = new Accessory("Turtle Beach Headset", 50.99, "Turtle Beach Headset.jpg", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xboxone_wc", xboxone_wc);
			accessories.put("xboxone_wr", xboxone_wr);
			accessories.put("xboxone_sh", xboxone_sh);			
			Console xboxone = new Console("XBox One",399.99,"xbox1.jpg","Microsoft","New",10,accessories);
			microsoft.put("xboxone", xboxone);
			
			Accessory xbox360_mr = new Accessory("Speeding Wheel", 40.99, "XBOX360-SpeedWheel.jpg", "Microsoft","New",10);
			Accessory xbox360_wr = new Accessory("XBOX 360 Warranty", 40.99, "ics.png", "Microsoft","New",10);
			Accessory xbox360_wa = new Accessory("Wireless Adapter", 50.99, "xbox360_wa.png", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xbox360_mr", xbox360_mr);
			accessories.put("xbox360_wr", xbox360_wr);
			accessories.put("xbox360_wa", xbox360_wa);
			Console xbox360 = new Console("XBox 360",299.99,"xbox360.jpg","Microsoft","New",10,accessories);			
			microsoft.put("xbox360", xbox360);
		}

		if(sony.isEmpty()){			
			Accessory ps4_wc = new Accessory("Controller", 40.99, "PS4contr.jpg", "Sony","New",10);
			Accessory ps4_wr = new Accessory("PS4 Warranty", 40.99, "ics.png", "Sony","New",10);
			Accessory ps4_sh = new Accessory("Turtle Beach Headset", 50.99, "Turtle Beach Headset.jpg", "Sony","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("ps4_wc", ps4_wc);
			accessories.put("ps4_wr", ps4_wr);
			accessories.put("ps4_sh", ps4_sh);
			Console ps4 = new Console("PS4",349.99,"PS4-console-bundle.jpg","Sony","New",10,accessories);
			sony.put("ps4", ps4);
			
			Accessory ps3_wc = new Accessory("Controller", 30.99, "PS3contr.jpg", "Sony","New",10);
			Accessory ps3_wr = new Accessory("PS3 Warranty", 30.99, "ics.png", "Sony","New",10);
			Accessory ps3_sh = new Accessory("Turtle Beach Headset", 40.99, "Turtle Beach Headset.jpg", "Sony","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("ps3_wc", ps3_wc);
			accessories.put("ps3_wr", ps3_wr);
			accessories.put("ps3_sh", ps3_sh);
			Console ps3 = new Console("PS3",299.99,"PS3_Slim.jpg","Sony","New",10,accessories);		
			sony.put("ps3", ps3);			
		}

		if(nintendo.isEmpty()){
			Accessory wiiu_wc = new Accessory("Controller", 40.99, "wiiu_contr.jpg", "Nintendo","New",10);
			Accessory wiiu_wr = new Accessory("Wii U Warranty", 40.99, "ics.png", "Nintendo","New",10);
			Accessory wiiu_bg = new Accessory("Carrying Case", 20.99, "wiiu_case.png", "Nintendo","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("wiiu_wc", wiiu_wc);
			accessories.put("wiiu_wr", wiiu_wr);
			accessories.put("wiiu_bg", wiiu_bg);
			Console wiiu = new Console("Wii U",299.99,"wiiu.jpg","Nintendo","New",10,accessories);
			nintendo.put("wiiu", wiiu);
			
			Accessory wii_zp = new Accessory("Wii Zapper", 30.99, "wii_zapper.JPG", "Nintendo","New",10);
			Accessory wii_zr = new Accessory("Wii Warranty", 30.99, "ics.png", "Nintendo","New",10);
			Accessory wii_mp = new Accessory("Wii Motion Plus Controller", 30.99, "wii_motionplus.JPG", "Nintendo","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("wii_zp", wii_zp);
			accessories.put("wii_zr", wii_zr);
			accessories.put("wii_mp", wii_mp);
			Console wii = new Console("Wii",199.99,"wii.jpg","Nintendo","New",10,accessories);
			nintendo.put("wii", wii);
			
		}
	}
}
