import java.util.*;
import java.util.Map;


/* 
	Review class contains class variables id,manufacturer,productname,reviews_doRecommend,reviews_numHelpful,
	reviews_rating,reviews_text,reviews_title,reviews_username
	Review class has a constructor with arguments id,manufacturer,productname,reviews_doRecommend,reviews_numHelpful,
	reviews_rating,reviews_text,reviews_title,reviews_username
	Review class contains getters and setters for class variables
*/
public class Review {
	private String id;
	private String manufacturer;
	private String productName;
	private String reviews_doRecommend;
	private String reviews_numHelpful;
	private String reviews_rating;
	private String reviews_text;
	private String reviews_title;
	private String reviews_username;


	
	public Review(String id,String manufacturer, String productName, String reviews_doRecommend, String reviews_numHelpful,String reviews_rating,String reviews_text,
		String reviews_title,String reviews_username){
		this.id=id;
		this.manufacturer = manufacturer;
		this.productName = productName;
		this.reviews_doRecommend = reviews_doRecommend;
		this.reviews_numHelpful = reviews_numHelpful;
		this.reviews_rating = reviews_rating;
		this.reviews_text = reviews_text;
		this.reviews_title = reviews_title;
		this.reviews_username = reviews_username;
	}

	public Review(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getReviewsDoRecommend() {
		return reviews_doRecommend;
	}
	public void setReviewsDoRecommend(String reviews_doRecommend) {
		this.reviews_doRecommend = reviews_doRecommend;
	}


	public String getReviewsNumHelpful() {
		return reviews_numHelpful;
	}
	public void setReviewsNumHelpful(String reviews_numHelpful) {
		this.reviews_numHelpful = reviews_numHelpful;
	}
	public String getReviewsRating() {
		return reviews_rating;
	}
	public void setReviewsRating(String reviews_rating) {
		this.reviews_rating = reviews_rating;
	}
	public String getReviewsText() {
		return reviews_text;
	}
	public void setCategories(String reviews_text) {
		this.reviews_text = reviews_text;
	}
	
	public String getReviewsTitle() {
		return reviews_title;
	}

	public void setReviewsTitle(String reviews_title) {
		this.reviews_title = reviews_title;
	}

	public String getReviewsUsername() {
		return reviews_username;
	}

	public void setReviewsUsername(String reviews_username) {
		this.reviews_username = reviews_username;
	}
}
