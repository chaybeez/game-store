import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

@WebServlet("/autocomplete")
public class AutoCompleteServlet extends HttpServlet {
	
	private ServletContext context;
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {

		String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();
		boolean namesAdded = false;
		
		HashMap<String, Console> chm = new HashMap<String, Console>();
		chm.putAll(ConsoleHashMap.microsoft);
		chm.putAll(ConsoleHashMap.sony);
		chm.putAll(ConsoleHashMap.nintendo);	
				
		HashMap<String, Game> ghm = new HashMap<String, Game>();
		ghm.putAll(GameHashMap.electronicArts);
		ghm.putAll(GameHashMap.activision);
		ghm.putAll(GameHashMap.takeTwoInteractive);
				
		HashMap<String, Tablet> thm = new HashMap<String, Tablet>();
		thm.putAll(TabletHashMap.apple);
		thm.putAll(TabletHashMap.microsoft);
		thm.putAll(TabletHashMap.samsung);
				
				
        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }
		
		if (action.equals("complete")) {
		
			if (!targetId.equals("")) {
			
				for (Map.Entry<String, Console> entry : chm.entrySet())
					if (entry.getValue().getRetailer().toLowerCase().startsWith(targetId) || entry.getValue().getName().toLowerCase().startsWith(targetId)){
						sb.append("<item>");
						sb.append("<retailer>" + entry.getValue().getRetailer() + "</retailer>");
						sb.append("<name>" + entry.getValue().getName() + "</name>");
						sb.append("<key>" + entry.getKey() + "</key>");
						sb.append("<type>console</type>");
						sb.append("</item>");
						namesAdded = true;
					}
					
				for (Map.Entry<String, Game> entry : ghm.entrySet())
					if (entry.getValue().getRetailer().toLowerCase().startsWith(targetId) || entry.getValue().getName().toLowerCase().startsWith(targetId)){
						sb.append("<item>");
						sb.append("<retailer>" + entry.getValue().getRetailer() + "</retailer>");
						sb.append("<name>" + entry.getValue().getName() + "</name>");
						sb.append("<key>" + entry.getKey() + "</key>");
						sb.append("<type>game</type>");
						sb.append("</item>");
						namesAdded = true;
					}
				
				for (Map.Entry<String, Tablet> entry : thm.entrySet())
					if (entry.getValue().getRetailer().toLowerCase().startsWith(targetId) || entry.getValue().getName().toLowerCase().startsWith(targetId)){
						sb.append("<item>");
						sb.append("<retailer>" + entry.getValue().getRetailer() + "</retailer>");
						sb.append("<name>" + entry.getValue().getName() + "</name>");
						sb.append("<key>" + entry.getKey() + "</key>");
						sb.append("<type>tablet</type>");
						sb.append("</item>");
						namesAdded = true;
					}	
				}
			}
			if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<items>" + sb.toString() + "</items>");
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
			
			
			
			
			
			if (action.equals("lookup")) {
			String type = request.getParameter("type");
			String key = request.getParameter("key");
			String retailer = request.getParameter("retailer");
						
			if ((type.equals("console")) && (targetId != null)) {
                request.setAttribute("console", chm.get(key));
				request.setAttribute("category", type);
				request.setAttribute("key", key);
                response.sendRedirect("ConsoleList?action=lookup&type=" + type + "&key=" + key+ "&retailer="+retailer);
			}
			
			if ((type.equals("game")) && (targetId != null)) {
                request.setAttribute("game", chm.get(key));
				request.setAttribute("category", type);
				request.setAttribute("key", key);
                response.sendRedirect("GamesList?action=lookup&type=" + type + "&key=" + key+ "&retailer="+retailer);
			}
			
			if ((type.equals("tablet")) && (targetId != null)) {
                request.setAttribute("tablet", chm.get(key));
				request.setAttribute("category", type);
				request.setAttribute("key", key);
                response.sendRedirect("TabletList?action=lookup&type=" + type + "&key=" + key+ "&retailer="+retailer);
			}
			
		}
	}
}

