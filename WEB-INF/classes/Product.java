import java.util.*;
import java.util.Map;


/**
	Product class consists of the product details
*/
public class Product {
	private String id;
	private String asin;
	private String brand;
	private String categories;
	private String colors;
	private String dateAdded;
	private String dateUpdated;
	private String dimension;
	private String ean;
	private String imageURLs;
	private String manufacturer;
	private String manufacturerNumber;
	private String name;
	private String primaryCategories;
	private String weight;


	
	public Product(String id,String asin, String brand, String categories, String colors,String dateAdded,String dateUpdated,String dimension,
					String ean, String imageURLs,String manufacturer,String manufacturerNumber, String name, 
					String primaryCategories, String weight){
		this.id=id;
		this.asin = asin;
		this.brand = brand;
		this.categories = categories;
		this.colors = colors;
		this.dateAdded = dateAdded;
		this.dateUpdated = dateUpdated;
		this.dimension = dimension;
		this.ean = ean;
		this.imageURLs = imageURLs;
		this.manufacturer = manufacturer;
		this.manufacturerNumber = manufacturerNumber;
		this.name = name;
		this.primaryCategories = primaryCategories;
		this.weight = weight;
	}

	public Product(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}


	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImageURLs() {
		return imageURLs;
	}
	public void setImageURLs(String imageURLs) {
		this.imageURLs = imageURLs;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}


	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturerNumber() {
		return manufacturerNumber;
	}
	public void setManufacturerNumber(String manufacturerNumber) {
		this.manufacturerNumber = manufacturerNumber;
	}

	public String getPrimaryCategories() {
		return primaryCategories;
	}
	public void setPrimaryCategories(String primaryCategories) {
		this.primaryCategories = primaryCategories;
	}


	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
