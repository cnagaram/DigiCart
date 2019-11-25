import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Registration")

/**
	Registration class helps the new user to register.
*/
public class Registration extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utils utils = new Utils(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

			HashMap<String, User> usersMap=new HashMap<String, User>();
			String message=MySqlDataStoreUtilities.getConnection();
			
			if(message.equals("Successfull"))
			{
				usersMap=MySqlDataStoreUtilities.getUser();


			if(usersMap.containsKey(username))
				error_msg = "Username already exists ";
			else
			{

				User user = new User(username,email,password);
				usersMap.put(username, user);
				MySqlDataStoreUtilities.insertUser(username,email,password);					

				HttpSession session = request.getSession(true);				
				if(!utils.isLoggedin()){
					session.setAttribute("login_msg", "Your "+username+" account has been created. Please login");
					response.sendRedirect("Login"); return;
				} else {
					response.sendRedirect("SinglePage"); return;
				}
			}
		}

		else 
		{
			error_msg="MySql server is not up and running";
		}

		displayRegistration(request, response, pw, true);
		
	}
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utils utils = new Utils(request, pw);
		utils.printHtml("single.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Registration</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<form method='post' action='Registration'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Email</h3></td><td><input type='text' name='email' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Create User' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
	}
}