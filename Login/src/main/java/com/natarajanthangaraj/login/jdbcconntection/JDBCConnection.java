package com.natarajanthangaraj.login.jdbcconntection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCConnection {
	static JDBCConnection connection;
	private Connection con;

	public static JDBCConnection getInstance() {
		if (connection == null) {
			connection = new JDBCConnection();
		}
		return connection;

	}

	// to open the mysql connection
	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/Login";
			String user = "root";
			String password = "Nattu@27";
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println(" ERROR : 500 [ exception in database connection ]");
		}
	}

	// to close the mysql connection
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized int executeInsert(String query) {
		openConnection();
		int affectedRows = 0;
		try {
			PreparedStatement statement = con.prepareStatement(query);
			affectedRows = statement.executeUpdate();

			// if insert is success
			if (affectedRows > 0) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					return resultSet.getInt(1);

			}
		} catch (SQLException e) {
			System.err.println(" ERROR : 500 [ exception in executeInsertOrUpdate ]");
			closeConnection();
		}

		return 0;

	}

}
