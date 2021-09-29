import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dist")
public class MyServlet extends HttpServlet {

	public static final double R = 6372.8; //km

	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		// TODO : show a HTML form with 4 input text (2 for each point)
		//        your input must be named 'p1lat', 'p1lng', 'p2lat', 'p2lng'
		resp.setContentType("text/html");

		Writer out = resp.getWriter();
		out.write( "<html><body>");
		out.write("Show form");
		out.write("<form action=\"dist\" method=\"post\">");
		out.write("<div><label>P1Latitude</label> <input name=\"p1lat\"></div>");
		out.write("<div><label>P1Longitude</label> <input name=\"p1lng\"></div>");
		out.write("<div><label>P2Latitude</label> <input name=\"p2lat\"></div>");
    	out.write("<div><label>P2Longitude</label> <input name=\"p2lng\"></div>");
		out.write("<div><input type=\"submit\" value=\"envoi\"></div>");
		out.write("</form>");
		out.write( "</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Double lat1 = Double.parseDouble(req.getParameter("p1lat"));
		Double lat2 = Double.parseDouble(req.getParameter("p2lat"));
		Double long1 = Double.parseDouble(req.getParameter("p1lng"));
		Double long2 = Double.parseDouble(req.getParameter("p2lng"));

		Writer out = resp.getWriter();
		out.write("Display result\n");
		out.write("Haversine distance equals : "+ Math.round(haversine(lat1,long1,lat2,long2) * 10.0) / 10.0 + " km");
	}

	private static Double haversine(Double lat1, Double lon1, Double lat2, Double lon2) {
		final int R = 6371; // Radious of the earth
		Double latDistance = toRad(lat2-lat1);
		Double lonDistance = toRad(lon2-lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
				Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
						Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R * c;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

}
