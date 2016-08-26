import java.util.HashMap;

public class GameHashMap {

	public static HashMap<String, Game> electronicArts = new HashMap<String, Game>();
	public static HashMap<String, Game> activision = new HashMap<String, Game>();
	public static HashMap<String, Game> takeTwoInteractive = new HashMap<String, Game>();
	
	public static String string_electronicArts = "Electronic Arts";
	public static String string_activision = "Activision";
	public static String string_takeTwoInteractive = "Take-Two Interactive";
	
	public GameHashMap() {
		if(electronicArts.isEmpty()){
			Game ea_fifa = new Game("FIFA 2016",59.99,"ea_fifa.jpg","Electronic Arts","New",10);
			Game ea_nfs = new Game("Need for Speed",59.99,"ea_nfs.jpg","Electronic Arts","New",10);
			Game ea_pvz2 = new Game("Plants vs. Zombies: Garden Warfare 2",59.99,"PvZ_G_W_2.jpg","Electronic Arts","New",10);
			Game ea_swbf = new Game("Star Wars Battlefront",59.99,"SWBF.jpg","Electronic Arts","New",10);

			electronicArts.put("ea_nfs", ea_nfs);
			electronicArts.put("ea_fifa", ea_fifa);
			electronicArts.put("ea_pvz2", ea_pvz2);
			electronicArts.put("ea_swbf", ea_swbf);
			
		}
		if(activision.isEmpty()){
			Game activision_cod = new Game("Call Of Duty",54.99,"activision_cod.jpg","Activision","New",10);
			Game activision_dest = new Game("Destiny",54.99,"Destiny.jpg","Activision","New",10);
			Game activision_wolf = new Game("Wolfenstein",54.99,"wolfen.jpg","Activision","New",10);
			Game activision_sky = new Game("Skylanders: Trap Team",54.99,"skylanders.jpg","Activision","New",10);
			
			activision.put("activision_cod", activision_cod);
			activision.put("activision_dest", activision_dest);
			activision.put("activision_wolf", activision_wolf);
			activision.put("activision_sky", activision_sky);
			
		}
		if(takeTwoInteractive.isEmpty()){

			Game tti_evolve = new Game("Evolve",49.99,"tti_evolve.jpg","Take-Two Interactive","New",10);
			Game tti_gta5 = new Game("Grand Theft Auto 5",49.99,"gta5.jpg","Take-Two Interactive","New",10);
			Game tti_bd2 = new Game("Borderlands 2",49.99,"bd2.jpg","Take-Two Interactive","New",10);
			Game tti_xcom = new Game("XCOM",49.99,"xcom.jpg","Take-Two Interactive","New",10);
			
			takeTwoInteractive.put("tti_evolve", tti_evolve);
			takeTwoInteractive.put("tti_gta5", tti_gta5);
			takeTwoInteractive.put("tti_bd2", tti_bd2);
			takeTwoInteractive.put("tti_xcom", tti_xcom);
		}
	}
}
