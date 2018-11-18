package com.mcds5510.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mcds5510.conn.*;
import com.mcds5510.entity.*;

public class MySQLAccess {

	public String createTransaction(Transaction trxn) throws Exception {

		Connection connection = DBConnection.getDBConnection();

		int count = 0;
		String output = "";

		Statement statement = null;
		ResultSet resultSet = null;

		// Statements allow to issue SQL queries to the database
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from transaction");

		while (resultSet.next()) {
			int currentID = resultSet.getInt(1);
			if (trxn.getiD() == currentID) {
				count++;

			}
		}

		try {

			if (count == 0) {

				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into  transaction values (?, ?, ?, ? , ?, ?,?,now(),?,?)");

				preparedStatement.setInt(1, trxn.getiD());
				preparedStatement.setString(2, trxn.getNameOnCard());
				preparedStatement.setString(3, trxn.getCardNumber());
				preparedStatement.setDouble(4, trxn.getUnitPrice());
				preparedStatement.setInt(5, trxn.getQuantity());
				preparedStatement.setDouble(6, trxn.getTotalPrice());
				preparedStatement.setString(7, trxn.getExpDate());
				preparedStatement.setString(8, trxn.getCreatedBy());
				preparedStatement.setString(9, trxn.getCardType());
				preparedStatement.executeUpdate();
				output = "Succesful Entry Creation";
			}

			else {

				output = "Unsuccesful Entry Creation.";

			}

		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}

		return output;
	}

	public Collection<Transaction> getAllTransactions(Connection connection) {
		Statement statement = null;
		ResultSet resultSet = null;
		Collection<Transaction> results = null;
		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");
			Logger.getAnonymousLogger().log(Level.INFO, "SELECTED ALL FIELDS FROM DATABASE");

			results = createTrxns(resultSet);

			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT GETALLTRANSACTIONS() IN MYSQLACCESS");
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}
		return results;

	}

	private Collection<Transaction> createTrxns(ResultSet resultSet) throws SQLException {
		Collection<Transaction> results = new ArrayList<Transaction>();

		// ResultSet is initially before the first data set
		while (resultSet.next()) {

			Transaction trxn = new Transaction();
			trxn.setiD(resultSet.getInt(1));
			trxn.setNameOnCard(resultSet.getString(2));
			trxn.setCardNumber(resultSet.getString(3));
			trxn.setUnitPrice(resultSet.getFloat(4));
			trxn.setQuantity(resultSet.getInt(5));
			trxn.setTotalPrice(resultSet.getFloat(6));
			trxn.setExpDate(resultSet.getString(7));
			trxn.setCreatedOn(resultSet.getString(8));
			trxn.setCreatedBy(resultSet.getString(9));
			trxn.setCardType(resultSet.getString(10));

			results.add(trxn);

		}

		return results;

	}

	public String getTransaction(int ID) throws SQLException {

		Connection connection = DBConnection.getDBConnection();
		Transaction trxn = new Transaction();
		trxn.setiD(ID);
		Statement statement = null;
		ResultSet resultSet = null;
		String result = " - ";
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from JavaAssignment02.transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (trxn.getiD() == currentID) {
					statement = connection.createStatement();
					trxn.setiD(resultSet.getInt(1));
					trxn.setNameOnCard(resultSet.getString(2));
					trxn.setCardNumber(resultSet.getString(3));
					trxn.setUnitPrice(resultSet.getFloat(4));
					trxn.setQuantity(resultSet.getInt(5));
					trxn.setTotalPrice(resultSet.getFloat(6));
					trxn.setExpDate(resultSet.getString(7));
					trxn.setCreatedOn(resultSet.getString(8));
					trxn.setCreatedBy(resultSet.getString(9));
					trxn.setCardType(resultSet.getString(10));
					result = trxn.toString();
					break;
				}

				else {
					result = "ID Not Found";
				}

			}
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public String updateTransaction(Transaction trxn) throws SQLException {
		Connection connection = DBConnection.getDBConnection();

		int count = 0;
		Statement statement = null;
		ResultSet resultSet = null;

		String output = "";

		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (trxn.getiD() == currentID) {
					count++;

				}
			}

			if (count == 0) {
				output = "Updation Failed. ID Does Not Exists. Please Use Create.";
				return output;

			} else {

				if (trxn.getNameOnCard() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set NameOnCard=? WHERE ID=?");
					preparestatement.setString(1, trxn.getNameOnCard());

					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Name On Card Updated";
					System.out.println(output);

				}

				if (trxn.getCardNumber() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set CardNumber=? WHERE ID=?");
					preparestatement.setString(1, trxn.getCardNumber());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Card Number Updated";
					System.out.println(output);

				}

				if (trxn.getUnitPrice() != 0.0) {
					System.out.println(trxn.getUnitPrice());
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set UnitPrice=? WHERE ID=?");
					preparestatement.setDouble(1, trxn.getUnitPrice());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Unit Price Updated";

				}

				if (trxn.getQuantity() != 0) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set Quantity=? WHERE ID=?");
					preparestatement.setInt(1, trxn.getQuantity());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Quantity Updated";

				}

				if (trxn.getCardType() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set CardType=? WHERE ID=?");
					preparestatement.setString(1, trxn.getCardType());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Card Type Updated";

				}

				if (trxn.getTotalPrice() != 0.0) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set TotalPrice=? WHERE ID=?");
					preparestatement.setDouble(1, trxn.getTotalPrice());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Total Price Updated";

				}

				if (trxn.getExpDate() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set ExpDate=? WHERE ID=?");
					preparestatement.setString(1, trxn.getExpDate());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					output = "Expiry Month Updated";

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			e.printStackTrace();
		}
		output = "Update Success.";
		return output;
	}

	public String removeTransaction(int ID) throws SQLException {

		Connection connection = DBConnection.getDBConnection();
		String result = "";
		Statement statement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (ID == currentID) {
					count++;

				}
			}
			if (count == 0) {

				result = "ID Not Found";

			} else {

				PreparedStatement preparestatement = connection.prepareStatement("Delete from transaction where ID=?");
				preparestatement.setInt(1, ID);
				preparestatement.execute();
				result = "Delete Success";
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

}
