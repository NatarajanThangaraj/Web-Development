package com.natarajanthangaraj.login.repository;

import com.natarajanthangaraj.login.jdbcconntection.JDBCConnection;
import com.natarajanthangaraj.login.model.User;

public class Repository {
	static Repository repository;

	private Repository() {
	};

	

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}
	
	// to store new user details and get the created userID for cookie purpose
	public boolean addNewUser(User user) {
		String query="INSERT INTO login(Email,password)Values("+user.getEmail()+","+user.getPassword()+");";
		int userID=JDBCConnection.getInstance().executeInsert(query);
		if(userID>0)
			return true;
		
		return false;
	}

}
