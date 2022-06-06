package com.minimart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.minimart.bean.Item;

public class ItemDao {

		private String jdbcURL = "jdbc:mysql://localhost:3306/itemdb?useSSL=false";
		private String jdbcUsername = "root";
		private String jdbcPassword = "s$96515779J";
		private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		
		private static final String INSERT_ITEMS_SQL = "INSERT INTO items" + " (name, price) VALUES " + " (?, ?)";
		private static final String SELECT_ITEM_BY_ID = "select id,name,price from items where id =?";
		private static final String SELECT_ALL_ITEMS = "select * from items";
		private static final String DELETE_ITEMS_SQL = "delete from items where id = ?;";
		private static final String UPDATE_ITEMS_SQL = "update items set name = ?, price= ? where id = ?;";
		
		public ItemDao() {
						
		}
		protected Connection getConnection(){
			Connection connection = null;
			try {
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		// insert item
		public void insertItem(Item item) throws SQLException{
			System.out.println(INSERT_ITEMS_SQL);
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEMS_SQL)) {
				preparedStatement.setString(1, item.getName());
				preparedStatement.setDouble(2, item.getPrice());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			}
			catch (SQLException e){
				printSQLException(e);
			}
		}
		
		
		
		// select item by id
		public Item selectItem (int id){
			Item item = null;
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					String name = rs.getString("name");
					double price = rs.getDouble("price");
					item = new Item(id, name, price);
				}
			}
			catch (SQLException e){
				printSQLException(e);
			}
			return item;
		}
		
		// select all item
		public List<Item> selectAllItems(){
			List<Item> items = new ArrayList<>();
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					double price = rs.getDouble("price");
					items.add(new Item(id, name, price));
				}
			}
			catch (SQLException e) {
				printSQLException(e);
			}
			return items;
		}
		
		// update item
		public boolean updateItem(Item item) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_ITEMS_SQL);) {
				System.out.println("updated Item: " + statement);
				statement.setString(1, item.getName());
				statement.setDouble(2, item.getPrice());
				statement.setInt(3, item.getId());
				
				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		
		// delete item
		public boolean deleteItem(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_ITEMS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		private void printSQLException(SQLException ex){
			for (Throwable e : ex){
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}
