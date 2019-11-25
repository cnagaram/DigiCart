import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;

/**
This is utility class used to get product data from database based on the searchId

*/

public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;

    /**
		This method connects to MySql database using jdbc driver.
		@return String message whether connection is successful or not.
     */
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
			 message="unsuccessful";
		     return message;
		}
	}
	

	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Product> data;
		data=getData();
		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Product p=(Product)pi.getValue();                   
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	  
	   return sb;
	}
	
	/** 
		This method is used to get product data from the database
		@return Hashmap with key as id and value as product object
	*/
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> idToProductMap =new HashMap<String,Product>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  Products";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Product product = new Product(
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
				
				idToProductMap.put(rs.getString("id"), product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return idToProductMap;			
	}
}
