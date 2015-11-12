package backend;

import java.io.Serializable;
import java.util.ArrayList;
import backend.Flight;

public abstract class User implements Serializable{
	
	protected String username;
	protected String password;

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	

}
