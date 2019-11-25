import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/Home")

/**
	This class is invoked when the Home tab is opened. 
	It fetches all the Top Rated products whose average rating is above 4.5 
	from the database and displays back on the html page 
*/
public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		List<HashMap<String,String>> products = new ArrayList<HashMap<String,String>>();

		try{
		     products = MySqlDataStoreUtilities.getTopRatedProducts();
		}
		catch(Exception e)
		{
			
		}
		
		Utils utils = new Utils(request,pw);
		utils.printHtml("customIndex.html");
		pw.print("<div id='products' class='ads-grid py-sm-5 py-4'>");
		pw.print("<div class='container py-xl-4 py-lg-2'><div class='row'><div class='agileinfo-ads-display col-lg-9><div class='wrapper'><div class='product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4'>");
		pw.print("<h3 class='tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3'><span>T</span>op<span>R</span>ated<span>P</span>roducts</h3>");
		pw.print("</h2><table>");
		int i = 1;
		int size = products.size();
		for (Map<String,String> product : products) {
			// Map<String,String> row = new HashMap<String,String>();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td>");
			pw.print("<p>" + product.get("pName") + "</p>");
			pw.print("<img height='200px' width='200px' src='"
					+ product.get("image") + "' alt='' />");
			pw.print("<form method='post' action='SinglePage'>"+"<input type='hidden' name='id' value='"+product.get("id")+"'>"+
					"<input type='hidden' name='maker' value='"+product.get("pName")+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input name='Quick View' type='submit' value='Quick View' class='link-product-add-cart'></form>");
			pw.print("</ul></td>");

			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div>");
		pw.print("</div></div></div>");
	}

}