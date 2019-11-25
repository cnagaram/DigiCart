import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

/**
	ProductViews class contains details of number of times a product is viewed by user.
	class variables are id,userName,viewCount,productName, and reviewsRating 
*/
public class ProductViews implements Serializable{
	private String id;
	private String userName;
	private double viewCount;
	private String productName;
	private String reviewsRating;
	
	public ProductViews(String id,String userName,double viewCount,String productName,String reviewsRating) {
		this.id=id;
		this.userName = userName;
		this.viewCount=viewCount;
		this.productName=productName;
		this.reviewsRating=reviewsRating;
	}

	public ProductViews() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getViewCount() {
		return viewCount;
	}

	public void setViewCount(double viewCount) {
		this.viewCount = viewCount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReviewsRating() {
		return reviewsRating;
	}

	public void setReviewsRating(String reviewsRating) {
		this.reviewsRating = reviewsRating;
	}

}
