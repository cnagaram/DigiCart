import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;


/**
	User class contains class variables id,name,password,usertype.
	User class has a constructor with Arguments name,  String usertype, String password
	User  class contains getters and setters for the class variables

*/
public class User implements Serializable{
	private int id;
	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password) {
		this.name=name;
		this.email = email;
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
