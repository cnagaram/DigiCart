import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


/**
    MostViewedReport class fetches the searchhistory of products for a specific users and sends response in json format.
*/
@WebServlet("/MostViewedReport")
public class MostViewedReport extends HttpServlet {

static Connection conn = null;

public static void getConnection()
{

try
{
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicart","root","root");
}
catch(Exception e)
{
      e.printStackTrace();
}
}

protected void doGet(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
PrintWriter pw = response.getWriter();


Utils utils = new Utils(request, pw);
utils.printHtml("single.html");
pw.print("<button id='clickhere'  class='link-product-add-cart' style='color:black;'>View Chart for Most Viewed Products</button></a>");
pw.println("<div id='chart_div'></div>");
pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
 pw.println("<script type='text/javascript' src='MostViewedVisualisation.js'></script>");

}

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            try{
                getConnection();
                Statement stmt = conn.createStatement();
                String query = "Select * from searchHistory order by viewCount asc";
                ResultSet rs = stmt.executeQuery(query);

                JSONArray json = new JSONArray();
                ResultSetMetaData rsmd = rs.getMetaData();

                while(rs.next()){
                    System.out.print(rs.getString("productName"));
                    System.out.print(rs.getString("viewCount"));                    
                    JSONObject obj = new JSONObject();
                    obj.put("productName",rs.getString("productName"));
                    obj.put("viewCount",rs.getString("viewCount"));
                    json.put(obj);
                }       

                String reportJson = new Gson().toJson(json);
                response.setContentType("application/JSON");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(reportJson);
            }catch(Exception e){
                e.printStackTrace();
                System.out.print(e);
            }

    } 

}