import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
	SinglePage class is used to display details of Product when user clicks on quick view button to view the product.
	Redirects to login page when the user is not logged in.
*/
@WebServlet("/SinglePage")
public class SinglePage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utils utils = new Utils(request,pw);

		if(!utils.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to view the items");
			response.sendRedirect("Login");
			return;
		}
		else {
			HttpSession session = request.getSession(true);
			String username = session.getAttribute("username").toString();
			request.setAttribute("username",username);
			displayProduct(request,response);
		}

	}


	public void displayProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter pw = response.getWriter();
		Utils utils = new Utils(request,pw);
		double viewCount=1;

		String productId = request.getParameter("id");
		utils.printHtml("single.html");
		Product product = MySqlDataStoreUtilities.getProductDetailsWithId(productId);
		List<Review> reviews = MySqlDataStoreUtilities.getReviewDetailsWithId(productId);
		int reviewSize = reviews.size();
		String userName = request.getAttribute("username").toString();
		Review firstReview = reviews.get(0);
		String reviewsRating = firstReview.getReviewsRating();

		ProductViews productView = MySqlDataStoreUtilities.getProductViews(productId,userName);

		if(productView.getUserName() == null) {
			MySqlDataStoreUtilities.insertProductViews(productId,userName,viewCount,product.getName(),reviewsRating);
		}
		else {
			ProductViews productview = MySqlDataStoreUtilities.getProductViews(productId,userName);
			viewCount = productview.getViewCount()+1;
			MySqlDataStoreUtilities.updateViewCount(productId,userName,viewCount);
			
		}

		pw.print("<li><a><span class='glyphicon'>Hello,"+request.getAttribute("username")+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>");
		pw.print("<li><a href='ProductRecommendDisplay'><span class='glyphicon'></span>Click here to view recommended products!!</a></li>");
		pw.print("<div class='row'>"
				+"<div class='col-lg-5 col-md-8 single-right-left '>"
					+"<div class='grid images_3_of_2'>"
					+"	<div class='flexslider'>"
					+"		<ul class='slides'>"
					+"			<li data-thumb='"+product.getImageURLs()+"'>"
					+"				<div class='thumb-image'>"
					+"					<img src='"+product.getImageURLs()+"' data-imagezoom='true' class='img-fluid' alt=''> </div>"
					+"			</li>"
					+"		</ul>"
					+"		<div class='clearfix'></div>"
					+"	</div>"
					+"</div>"
				+"</div>"
				+"<div class='col-lg-7 single-right-left simpleCart_shelfItem'>"
					+"<h3 class='mb-3'>"+product.getName()+"</h3>"
					+"<p class='mb-3'>"
						+"<span class='item_price'>Brand: "+product.getBrand()+"</span>"
					+"</p>"
					+"<div class='single-infoagile'>"
					+"	<ul>"
					+"		<li class='mb-3'>"
					+"		Asin: "+product.getAsin()+""
					+"		</li>"
					+"		<li class='mb-3'>"
					+"			Dimensions: "+product.getDimension()+""
					+"		</li>"
					+"		<li class='mb-3'>"
					+"			Manufacturer: "+product.getManufacturer()+""
					+"		</li>"
					+"		<li class='mb-3'>"
					+"			ManufacturerNumber: "+product.getManufacturerNumber()+""
					+"		</li>"
					+"	</ul>"
					+"</div>");

				for(Review review : reviews) {

					pw.print("<div class='product-single-w3l'>"
					+"	<p class='my-3'>"
					+"		<i class='far fa-hand-point-right mr-2'></i>"
					+"		<label>Reviews</label></p>"
					+"	<ul>"
					+"		<li class='mb-1'>"
					+"			"+review.getReviewsTitle()+""
					+"		</li>"
					+"		<li class='mb-1'>"
					+"			Reviewer:"+review.getReviewsUsername()+""
					+"		</li>"
					+"		<li class='mb-1'>"
					+"			Review Text:"+review.getReviewsText()+""
					+"		</li>"
					+"		<li class='mb-1'>"
					+"			Rating:"+review.getReviewsRating()+""
					+"		</li>"
					+"	</ul>"
					+"</div>"
				+"</div></div>");
			}
	}

}