
/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAccess {
	Transaction trxn = new Transaction();

	public Connection setupConnection() throws Exception {

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("j" + "dbc:mysql://localhost:3306/JavaAssignment02?useUnicode=t"
					+ "rue&useJDBCCompliantTimezoneShift=" + "true&useLegacyDatetimeCode=false&server" + "Timezone=UTC",
					"root", "Arsenal20!");
			
			Logger.getAnonymousLogger().log(Level.INFO, "CONNECTED TO DATABASE");

		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT CONNECTION IN MYSQLACCESS.JAVA, COULD NOT CONNECT TO DB");
			throw e;
			
		} finally {

		}
		return connection;
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

	public Transaction getTransaction(int id, Connection connection) throws SQLException {
		Transaction trxn = new Transaction();

		Statement statement = null;
		ResultSet resultSet = null;
		

		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");
			Logger.getAnonymousLogger().log(Level.INFO, "SELECTED ALL COLUMNS FROM TRANSACTION");


			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (id == currentID) {
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

		return trxn;

	}

	public boolean updateTransaction(int id, Connection connection) throws SQLException {
		int count = 0;
		Statement statement = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		// Collection<Transaction> results = null;
		Scanner in = new Scanner(System.in);
		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (id == currentID) {
					count++;

				}
			}
			if (count == 0) {
				System.out.println("\nID NOT FOUND. ENTER Y TO CREATE RECORD WITH INPUT ID \n");
				char userInput = in.next().charAt(0);
				if (userInput == 'y') {
					createTransaction(connection);
				}
			} else {
				System.out.println(
				"SELECT FROM FOLLOWING: \n(1) NEW ID\n(2) NEW NAME\n(3) NEW CARD NUMBER\n(4) NEW PRICE\n(5) NEW QUANTITY\n(6) EXPRIY DATE \n\n ");
				System.out.println("\n(1-6)\n");
				int userInput = in.nextInt();
				if (userInput == 1) {

					System.out.println("TYPE NEW ID:\n");
					int userValue = in.nextInt();
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set ID=? WHERE ID=?");
					Logger.getAnonymousLogger().log(Level.INFO, "UPDATED ID");
					preparestatement.setInt(1, userValue);
					preparestatement.setInt(2, id);
					preparestatement.execute();
				} else if (userInput == 2) {

					System.out.println("TYPE NEW NAME: \n");
					String userValue = in.next();
					if (userValue.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("SORRY, INVALID NAME INPUTTED \n");
						Logger.getAnonymousLogger().log(Level.INFO, "INVALID NAME INPUTTED");
						return false;
					}
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set NameOnCard=? WHERE ID=?");
					preparestatement.setString(1, userValue);
					preparestatement.setInt(2, id);
					preparestatement.execute();
				} else if (userInput == 3) {
					String CardType;
					System.out.println("ENTER NEW CARD NUMBER \n");
					String userValue = in.next();
					if (userValue.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("SORRY, INVALID NUMBER INPUTTED");
						return false;
					}
					if (userValue.matches("^[5][1-5].*") && userValue.length() == 16) {
						CardType = "MasterCard";
					} else if (userValue.matches("^[4].*") && userValue.length() == 16) {
						CardType = "Visa";
					} else if (userValue.matches("^[3][4|7].*") && userValue.length() == 15) {
						CardType = "American Express";
					} else {
						CardType = "Other";
					}
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set CardNumber=?, CardType=? WHERE ID=?");
					preparestatement.setString(1, userValue);
					preparestatement.setString(2, CardType);
					preparestatement.setInt(3, id);
					preparestatement.execute();
				} else if (userInput == 4) {
					resultSet1 = statement.executeQuery("select * from transaction where ID=" + id);
					while (resultSet1.next()) {
						System.out.println("Enter new Price");
						float user_value = in.nextFloat();

						float total_price = resultSet1.getFloat(5) * user_value;

						PreparedStatement preparestatement = connection
								.prepareStatement("UPDATE transaction Set UnitPrice=?, TotalPrice=? WHERE ID=?");

						preparestatement.setFloat(1, user_value);
						preparestatement.setFloat(2, total_price);
						preparestatement.setInt(3, id);
						preparestatement.execute();
					}

				} else if (userInput == 5) {
					resultSet1 = statement.executeQuery("select * from transaction where ID=" + id);
					while (resultSet1.next()) {
						System.out.println("TYPE NEW QUANTITY \n");
						int userValue = in.nextInt();
						float totalPrice = resultSet1.getFloat(4) * userValue;
						PreparedStatement preparestatement = connection
								.prepareStatement("UPDATE transaction Set Quantity=?, TotalPrice=? WHERE ID=?");
						preparestatement.setInt(1, userValue);
						preparestatement.setFloat(2, totalPrice);
						preparestatement.setInt(3, id);
						preparestatement.execute();
					}
				} else if (userInput == 6) {
					System.out.println("TYPE EXPIRY DATE AS FOLLOWS"
							+ ": \nMONTH(MM): \n");

					

					int month = in.nextInt();
					if (month > 12 || month < 0) {
						System.out.println("Enter correct month");
						return false;
					}
					System.out.println("\nYEAR(YYYY): \n");
					int year = in.nextInt();
					if (year < 2015 || year > 2032) {
						System.out.println("INCORRECT YEAR ENTERRED");
						return false;
					}
					
					String expiryDate = month+"/"+year;
					if (expiryDate.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("SORRY INVALID EXPIRY DATE");
						return false;
					}
					
					
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set ExpDate=? WHERE ID=?");
					preparestatement.setString(1, expiryDate);

					preparestatement.setInt(2, id);
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			e.printStackTrace();
		}
		return true;
	}

	public boolean removeTransaction(int id, Connection connection) throws SQLException {
		try {
			
			Statement statement = null;
			ResultSet resultSet = null;
			int count = 0;
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (id == currentID) {
					count++;

				}
			}
			if (count == 0) {

				System.out.println("\nWrong ID Inputted \n");

			} else {
				
				PreparedStatement preparestatement = connection.prepareStatement("Delete from transaction where ID=?");
				preparestatement.setInt(1, id);
				preparestatement.execute();
				System.out.println("\n DELETE SUCCESS \n");
			}

		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean createTransaction(Connection connection) throws SQLException {

		int count = 0;
		String CardType = "";
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			String username = System.getProperty("user.name");
			Scanner in = new Scanner(System.in);
			System.out.println("Enter your ID: ");
			int id = in.nextInt();
			while (resultSet.next()) {
				int currentID = resultSet.getInt("id");
				if (id == currentID) {
					count++;

				}
			}
			if (count == 0) {
				
					System.out.println("\nENTER YOUR NAME: \n");
					String nameOnCard = in.next();
					if (nameOnCard.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("\nINVALID NAME\n");
						return false;
					}
					System.out.println("ENTER CARD NUMBER: \n");
					String cardNumber = in.next();
					if (cardNumber.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("\nINVALID NUMBER \n");
						return false;
					}
					if (cardNumber.matches("^[5][1-5].*") && cardNumber.length() == 16) {
						CardType = "MasterCard";
					} else if (cardNumber.matches("^[4].*") && cardNumber.length() == 16) {
						CardType = "Visa";
					} else if (cardNumber.matches("^[3][4|7].*") && cardNumber.length() == 15) {
						CardType = "American Express";
					} else {
						CardType = "Other";
					}
					System.out.println("\nTYPE NEW UNIT PRICE \n");
					float price = in.nextFloat();
					System.out.println("\n TYPE NEW QUANTITY \n");
					int quantity = in.nextInt();
					System.out.println("TYPE EXPIRY DATE AS FOLLOWS"
							+ ": \nMONTH(MM): \n");
					int month = in.nextInt();
					if (month > 12 || month < 0) {
						System.out.println("Enter correct month");
						return false;
					}
					System.out.println("\nYEAR(YYYY): \n");
					int year = in.nextInt();
					if (year < 2015 || year > 2032) {
						System.out.println("INCORRECT YEAR ENTERRED");
						return false;
					}
					String expiryDate = month+"/"+year;
					
					if (expiryDate.matches(".*[;:!@#$%^*+?<>].*")) {
						System.out.println("\nINVALID EXPIRY DATE ENTERED \n");
						return false;
					} 
					
					float totalPrice = price * quantity;
					
					PreparedStatement preparedStatement = connection.prepareStatement(
							"insert into  transaction values (?, ?, ?, ? , ?, ?,?,now(),'" + username + "',?)");
					
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, nameOnCard);
					preparedStatement.setString(3, cardNumber);
					preparedStatement.setFloat(4, price);
					preparedStatement.setInt(5, quantity);
					preparedStatement.setFloat(6, totalPrice);
					preparedStatement.setString(7, expiryDate);
					preparedStatement.setString(8, CardType);
					preparedStatement.executeUpdate();
					System.out.println("SUCCESS \n");

				

			} else {
				System.out.println("ID ALREADY EXISTS. PRESS Y TO UPDATE \n\n");
				char option = in.next().charAt(0);
				if (option == 'y') {
					updateTransaction(id, connection);
				}
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}
			}

		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}

		return true;
	}
}

/*
 * public void createFunction (Connection connection, String id_Input) throws
 * SQLException { Statement statement = null; String username =
 * System.getProperty("user.name"); statement = connection.createStatement();
 * statement.executeUpdate("INSERT INTO Transaction " +
 * "VALUES ("+id_Input+", 'Simpson', '3434567', '5', '5','12','15', NOW(), '"
 * +username+"','Mastercard' )"); connection.close(); }
 */
