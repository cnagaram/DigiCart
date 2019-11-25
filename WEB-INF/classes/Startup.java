import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Startup")

/*  
startup servlet initializes the application and adds intial products to database
*/

public class Startup extends HttpServlet
{

	public void init() throws ServletException
    {
		MySqlDataStoreUtilities.insertProducts();
		MySqlDataStoreUtilities.insertProductReviews();
    }
}