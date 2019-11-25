import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 NewArrivals class displays stored in tweet.txt and the twitter feed is fetched from @Gadgets 
*/
@WebServlet("/NewArrivals")
public class NewArrivals extends HttpServlet {
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter pw = response.getWriter();
		String line=null;

		final String TOMCAT_HOME = System.getProperty("catalina.home");

		final Utils utils = new Utils(request,pw);
		utils.printHtml("single.html");
		pw.print("<div id='products' class='ads-grid py-sm-5 py-4'>");

		final BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\DigiCart\\tweet.txt")));
			line=reader.readLine().toLowerCase();

		pw.print("<style>");
		pw.print(".box1 { width: 1200px;  margin: 50px auto;  border: 4px solid #00bfb6;  padding: 20px;  text-align: center;  font-weight: 900;  color: #00bfb6;  font-family: arial;  position: relative;}");
		pw.print(".sb5:before { content: ''; width: 0px; height: 0px; position: absolute; border-left: 10px solid #00bfb6; border-right: 10px solid transparent; border-top: 10px solid #00bfb6; border-bottom: 10px solid transparent; right: -21px; top: 6px;}");
		pw.print("</style>");
			if(line==null)
			{
				pw.print("<h2 align='center'>No Offers Found</h2>");
			}	
			else
			{	
			pw.print("<h2 align='center'>Twitter Feed for New Arrivals of Electronic Products</h2>");
			do {	
				
				if(line!=" ")
				{
					
				
					pw.print("<h4><div class='box1 sb5'>"+line+"</div></h4>");
				  
				}
			    }while((line = reader.readLine()) != null);
			
		
		pw.print("</div></div></div>");
		

	}
	}
}