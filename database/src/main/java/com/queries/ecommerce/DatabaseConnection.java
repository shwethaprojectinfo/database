package com.queries.ecommerce;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class DatabaseConnection {
		private ResultSet resultSet = null;
		private Connection connection = null;
		private Statement statement = null;

		public ResultSet getConnection(String query) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "kruthi");
				statement = connection.createStatement();
				boolean result = statement.execute(query);
				resultSet = statement.getResultSet();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return resultSet;
		}
        public void closeConnection() {
        	
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

    