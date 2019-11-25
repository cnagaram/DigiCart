import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	
import java.util.*;


/**
	ProductRecommendDisplay class is used to display the recommended products for the user.

*/
@WebServlet("/ProductRecommendDisplay")
			
public class ProductRecommendDisplay extends HttpServlet {
			
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utils utils = new Utils(request, pw);
		utils.printHtml("single.html");
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		prodRecmMap = prodRecUtility.readOutputFile();

	for(String user: prodRecmMap.keySet())
	{
			if(user.equals(utils.username()))
			{
				String products = prodRecmMap.get(user);
				products=products.replace("[","");
				products=products.replace("]","");
				products=products.replace("\"", " ");
				ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
				int k = 0;
		pw.print("<div id='products' class='ads-grid py-sm-5 py-4'>");
		pw.print("<li><a><span class='glyphicon'>Hello,"+utils.username()+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>");
		pw.print("<div class='container py-xl-4 py-lg-2'><div class='row'><div class='agileinfo-ads-display col-lg-9><div class='wrapper'><div class='product-sec1 px-sm-4 px-3 py-sm-5  py-3 mb-4'>");
		pw.print("<h3 class='tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3'><span>R</span>ecommended&nbsp;<span>P</span>roducts</h3>");
		pw.print("</h2><table>");
			for(String prod : productsList)
			{
					prod= prod.replace("'", "");
					Product product = new Product();
					product = ProductRecommenderUtility.getProduct(prod.trim());

					
				pw.print("<tr>");
			pw.print("<td>");
			pw.print("<p>" + product.getName() + "</p>");
			pw.print("<strong>" + product.getDimension() + "</strong><ul>");
			pw.print("<img height='200px' width='200px' src='"
					+ product.getImageURLs() + "' alt='' />");
			pw.print("<form method='post' action='SinglePage'>"+"<input type='hidden' name='id' value='"+product.getId()+"'>"+
					"<input type='hidden' name='maker' value='"+product.getName()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input name='Quick View' type='submit' value='Quick View' class='link-product-add-cart'></form>");
			pw.print("</ul></td>");

				pw.print("</tr>");

		
		
			}
			pw.print("</table></div></div>");
			pw.print("</div></div></div>");
		}
	}
		
	}

	}
	