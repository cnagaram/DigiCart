import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
	MySqlDataStoreUtilities class consists of utility functions for fetching data from the database.

*/
public class MySqlDataStoreUtilities
{
public static SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
public static SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicart","root","root");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

/**
	insertProducts method parses the ProductData.csv file and inserts the product details into Products table.
*/
public static void insertProducts()
{
	try{
			
			getConnection();
			String TOMCAT_HOME = System.getProperty("catalina.home");
		String truncatetableacc = "delete from Products;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		

			BufferedReader bufferReader = null;
			bufferReader = new BufferedReader(new FileReader(TOMCAT_HOME+"\\webapps\\DigiCart\\ProductData.csv"));
			String line="";
			bufferReader.readLine();
			while((line=bufferReader.readLine())!=null)
			{
				
				String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				Date dateAdded = inputDateFormat.parse(tokens[5]);
				Date dateUpdated = inputDateFormat.parse(tokens[6]);

				Product product = new Product(
					tokens[0],
					tokens[1],
					tokens[2],
					tokens[3],
					tokens[4],
					outputDateFormat.format(dateAdded),
					outputDateFormat.format(dateUpdated),
					tokens[7],
					tokens[8],
					tokens[9],
					tokens[10],
					tokens[11],
					tokens[12],
					tokens[13],
					tokens[14]);

				String insertProductQuery = 
				"INSERT INTO  Products(id,asin,brand,categories,colors,dateAdded,dateUpdated,dimensions,ean,imageURLs,manufacturer,manufacturerNumber,productName,primaryCategories,weight)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					PreparedStatement pst = conn.prepareStatement(insertProductQuery);
				pst.setString(1,product.getId());
				pst.setString(2,product.getAsin());
				pst.setString(3,product.getBrand());
				pst.setString(4,product.getCategories());
				pst.setString(5,product.getColors());
				pst.setString(6,product.getDateAdded());
				pst.setString(7,product.getDateUpdated());
				pst.setString(8,product.getDimension());
				pst.setString(9,product.getEan());
				pst.setString(10,product.getImageURLs());
				pst.setString(11,product.getManufacturer());
				pst.setString(12,product.getManufacturerNumber());
				pst.setString(13,product.getName());
				pst.setString(14,product.getPrimaryCategories());
				pst.setString(15,product.getWeight());

			
				pst.executeUpdate();
			}
		
		}
		
	catch(Exception e)
	{
  		e.printStackTrace();
	}
}

/**
	insertProductReviews method parses the ProductReviews.csv file and 
	inserts the product review details into Products table.
*/
public static void insertProductReviews()
{
	try{
		
			getConnection();
			String TOMCAT_HOME = System.getProperty("catalina.home");
		String truncatetableprod = "delete from  ProductReviews;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();

			BufferedReader bufferReader = null;
			bufferReader = new BufferedReader(new FileReader(TOMCAT_HOME+"\\webapps\\DigiCart\\ProductReviews.csv"));
			String line="";
			bufferReader.readLine();
			while((line=bufferReader.readLine())!=null)
			{
				
				String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				String insertProductReviewQuery = 
				"INSERT INTO  ProductReviews(id,manufacturer,productName,reviews_doRecommend,reviews_numHelpful,reviews_rating,reviews_text,reviews_title,reviews_username)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";
					PreparedStatement pst = conn.prepareStatement(insertProductReviewQuery);
				pst.setString(1,tokens[0]);
				pst.setString(2,tokens[1]);
				pst.setString(3,tokens[2]);
				pst.setString(4,tokens[3]);
				pst.setString(5,tokens[4]);
				pst.setString(6,tokens[5]);
				pst.setString(7,tokens[6]);
				pst.setString(8,tokens[7]);
				pst.setString(9,tokens[8]);


			
				pst.executeUpdate();
			}
		
		}
		
	catch(Exception e)
	{
  		e.printStackTrace();
	}
}

/**
	getAllElectronics method fetches all the electronic product details from products table.
	@return List of products
*/
public static List<Product> getAllElectronics()
{	
	List<Product> products = new ArrayList<Product>();
	try 
	{
		getConnection();
		
		String selectProducts="select * from  Products";
		PreparedStatement pst = conn.prepareStatement(selectProducts);
		// pst.setString(1,"headphones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Product product = new Product(
			rs.getString("id"),
			rs.getString("asin"),
			rs.getString("brand"),
			rs.getString("categories"),
			rs.getString("colors"),
			rs.getString("dateAdded"),
			rs.getString("dateUpdated"),
			rs.getString("dimensions"),
			rs.getString("ean"), 
			rs.getString("imageURLs"),
			rs.getString("manufacturer"),
			rs.getString("manufacturerNumber"),
			rs.getString("productName"),
			rs.getString("primaryCategories"),
			rs.getString("weight"));
			
			products.add(product);
		}
	}
	catch(Exception e)
	{
	}
	return products;			
}

/**
	getProductDetailsWithId method fetches the electronic product details from products table based on productId.
	@param id identifier for the product
	@return List of products
*/
public static Product getProductDetailsWithId(String id)
{	
	Product product = new Product();
	try 
	{
		getConnection();
		String selectProducts="select * from  Products where id='"+id+"'";
		PreparedStatement pst = conn.prepareStatement(selectProducts);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{
			product = new Product(
				rs.getString("id"),
				rs.getString("asin"),
				rs.getString("brand"),
				rs.getString("categories"),
				rs.getString("colors"),
				rs.getString("dateAdded"),
				rs.getString("dateUpdated"), 
				rs.getString("dimensions"),
				rs.getString("ean"), 
				rs.getString("imageURLs"),
				rs.getString("manufacturer"),
				rs.getString("manufacturerNumber"),
				rs.getString("productName"),
				rs.getString("primaryCategories"),
				rs.getString("weight"));
		}
	}
	catch(Exception e)
	{
	}
	return product;			
}

/**
	getReviewDetailsWithId method fetches the electronic product review details from productreviews table based on productId.
	@param id identifier for the product
	@return List of product reviews
*/
public static List<Review> getReviewDetailsWithId(String id)
{	
	List<Review> reviews = new ArrayList<Review>();
	try 
	{
		getConnection();
		String selectReviews="select * from  ProductReviews where id='"+id+"'";
		PreparedStatement pst = conn.prepareStatement(selectReviews);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{
			Review review = new Review(
				rs.getString("id"),
				rs.getString("manufacturer"),
				rs.getString("productName"),
				rs.getString("reviews_doRecommend"),
				rs.getString("reviews_numHelpful"),
				rs.getString("reviews_rating"),
				rs.getString("reviews_text"), 
				rs.getString("reviews_title"),
				rs.getString("reviews_username"));
				
				reviews.add(review);
		}
	}
	catch(Exception e)
	{
	}
	return reviews;			
}

/**
	getTopRatedProducts method fetches the top rated products whose average rating > 4.5
	@return List of products
*/
public static List<HashMap<String,String>> getTopRatedProducts()
{	
	List<HashMap<String,String>> products = new ArrayList<HashMap<String,String>>();
	try 
	{
		getConnection();
		
		String selectProducts="select p.id id,avg(pr.reviews_rating) avg_rat, pr.productName pName, p.imageURLs image from productreviews pr, products p where p.id=pr.id group by pr.id;";
		PreparedStatement pst = conn.prepareStatement(selectProducts);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			HashMap<String,String> productMap = new HashMap<>();
			if(!productMap.containsKey(rs.getString("id"))){
				productMap.put("id", rs.getString("id"));
				productMap.put("avg_rat",rs.getString("avg_rat"));
				productMap.put("pName",rs.getString("pName"));
				productMap.put("image",rs.getString("image"));
			}
			Double avgRating = Double.parseDouble(productMap.get("avg_rat"));
			if(avgRating>4.5) {
				products.add(productMap);
			}
			
		}
	}
	catch(Exception e)
	{
	}
	return products;			
}

/**
	getUser method fetches the user details from registration table.
	@return usersMap
*/
public static HashMap<String,User> getUser()
{	
	HashMap<String,User> usersMap=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("email"),rs.getString("password"));
				usersMap.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return usersMap;			
}

/**
	getProductViews method fetches the products viewed by each user.
	@param id identifier for the product
	@param userName
	@return productview object
*/
public static ProductViews getProductViews(String id, String userName)
{	
	ProductViews productview = new ProductViews();
	try 
	{
		getConnection();
		String selectProductViews="select * from  SearchHistory where id='"+id+"' and userName='"+userName+"'";
		PreparedStatement pst = conn.prepareStatement(selectProductViews);
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{
			productview = new ProductViews(
				rs.getString("id"),
				rs.getString("userName"),
				rs.getDouble("viewCount"),
				rs.getString("productName"),
				rs.getString("reviews_rating"));
		}
	}
	catch(Exception e)
	{
	}
	return productview;			
}

/**
	insertProductViews method inserts the products viewed by each user.
*/
public static void insertProductViews(String id,String userName,double viewCount,String productName,String reviewsRating)
{
	try
	{	

		getConnection();
		String insertIntoCustomerViewQuery = "INSERT INTO SearchHistory(id,userName,viewCount,productName,reviews_rating) "
		+ "VALUES (?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerViewQuery);
		pst.setString(1,id);
		pst.setString(2,userName);
		pst.setDouble(3,viewCount);
		pst.setString(4,productName);
		pst.setString(5,reviewsRating);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}


/**
	updateViewCount method updates the viewCount of products if viewed multiple times by a user.
	@param id identifier for the product
	@param userName
	@param viewCount
*/
public static void updateViewCount(String id, String userName, double viewCount) {
	try
	{
		getConnection();
		String updateViewCountQuery = "update SearchHistory set viewCount = '"+viewCount+"' where id=? and userName=?";
		PreparedStatement pst=conn.prepareStatement(updateViewCountQuery);
		pst.setString(1,id);
		pst.setString(2,userName);
		pst.executeUpdate();

	}
	catch(Exception e)
	{

	}
}

/**
	insertUser method inserts the new users into registration.
*/
public static void insertUser(String username,String email,String password)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,email,password) "
		+ "VALUES (?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,email);
		pst.setString(3,password);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

}