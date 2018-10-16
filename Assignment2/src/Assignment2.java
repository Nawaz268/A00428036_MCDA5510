import java.sql.Connection;
import java.util.Collection;
//import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assignment2 {

	public static MySQLAccess m;

	public static void main(String[] args) throws Exception {
		final long startTime = System.currentTimeMillis(); // START TIMER
		System.setProperty("java.util.logging.config.file", "./logging.properties");
		MySQLAccess dao = new MySQLAccess();

		try {

			Connection connection = dao.setupConnection();

			while (true) {
				System.out.println("Press\n(1) CREATE\n(2) VIEW\n(3) DELETE\n(4) UPDATE\n(5) SHOW ALL RECORDS\n(6) EXIT\n\n ");
				Scanner in = new Scanner(System.in);
				int select = in.nextInt();
				// dao.createTransaction(Transaction,connection,resultSet);

				if (select == 1) {

					dao.createTransaction(connection);
					Logger.getAnonymousLogger().log(Level.INFO, "CREATE FUNCTION CALLED");

				}
				if (select == 2) {

					System.out.println("ID :\n");
					int id = in.nextInt();
					Transaction res = dao.getTransaction(id, connection);
					Logger.getAnonymousLogger().log(Level.INFO, "VIEW FUNCTION CALLED");
					System.out.println(res.toString() + "\n");

				}
				if (select == 3) {
					do {
						System.out.println("ID : \n");
						int id = in.nextInt();
						dao.removeTransaction(id, connection);
						Logger.getAnonymousLogger().log(Level.INFO, "DELETE FUNCTION CALLED");
						System.out.println(
								"\n PRESS Y TO CONTINUE DELETING OR ANYOTHER KEY TO PROCEED TO MAIN MENU \n\n");
					} while ((in.next().equalsIgnoreCase("y")) || (in.next().equalsIgnoreCase("Y")));
				}
				if (select == 4) {
					System.out.println("ID :\n ");
					int id = in.nextInt();
					dao.updateTransaction(id, connection);
					Logger.getAnonymousLogger().log(Level.INFO, "UPDATE FUNCTION CALLED");
				}

				if (select == 5) {
					Logger.getAnonymousLogger().log(Level.INFO, "EXIT FUNCTION CALLED");
					Collection<Transaction> trxns = dao.getAllTransactions(connection);
					// dao.insertFunction(connection);
					for (Transaction trxn : trxns) {
						Logger.getAnonymousLogger().log(Level.INFO, trxn.toString());
						
						System.out.println(trxn.toString());
					}
				}

				if (select == 6) {
					
					Logger.getAnonymousLogger().log(Level.INFO, "EXIT FUNCTION CALLED");
					break;
				}
				
			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION IN MAIN OF ASSIGNMENT2.JAVA");
		}
		
		final long endTime = System.currentTimeMillis();
		Logger.getLogger("Main").log(Level.INFO, "Total execution time: " + (endTime - startTime) + " ms");
	}

}
