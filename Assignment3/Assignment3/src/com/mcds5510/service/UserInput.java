package com.mcds5510.service;

import java.sql.SQLException;
import com.mcds5510.dao.*;
import com.mcds5510.entity.*;

public class UserInput {

	public String createEntry(int ID, String NameOnCard, String CardNumber, double UnitPrice, int Quantity, int Month,
			int Year) throws Exception {

		int count = 0;
		MySQLAccess MS = new MySQLAccess();

		Transaction trxn = new Transaction();

		trxn.setiD(ID);

		String output = "";
		try {
			if (NameOnCard.matches(".*[;:!@#$%^*+?<>].*")) {
				count++;
			}
			trxn.setNameOnCard(NameOnCard);

			if (CardNumber.matches(".*[;:!@#$%^*+?<>].*")) {
				count++;
			}
			trxn.setCardNumber(CardNumber);

			if (count == 0) {
				String CardType = "";
				if (CardNumber.matches("^[5][1-5].*") && CardNumber.length() == 16) {
					CardType = "MasterCard";
					trxn.setCardType(CardType);

				} else if (CardNumber.matches("^[4].*") && CardNumber.length() == 16) {
					CardType = "Visa";
					trxn.setCardType(CardType);

				} else if (CardNumber.matches("^[3][4|7].*") && CardNumber.length() == 15) {
					CardType = "American Express";
					trxn.setCardType(CardType);

				} else {
					CardType = "Other";
					trxn.setCardType(CardType);
				}
			}

			trxn.setUnitPrice(UnitPrice);

			trxn.setQuantity(Quantity);

			double totalPrice = UnitPrice * Quantity;

			trxn.setTotalPrice(totalPrice);

			if (Month > 12 || Month < 0) {
				count++;
			}

			if (Year < 2015 || Year > 2032) {
				count++;
			}

			String expiryDate = Month + "/" + Year;

			if (expiryDate.matches(".*[;:!@#$%^*+?<>].*")) {
				count++;

			}

			trxn.setExpDate(expiryDate);

			String username = System.getProperty("user.name");
			trxn.setCreatedBy(username);

			if (count == 0)

			{

				output = MS.createTransaction(trxn);
				System.out.println(output);
				return output;
			}

			else {
				output = "Failure User Creation, Wrong Entry's Detected.";
				System.out.println(output);
				return output;

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}

	public String getEntry(int ID) throws SQLException {
		MySQLAccess MS = new MySQLAccess();
		String result = MS.getTransaction(ID);
		System.out.println(result);
		return result;
	}

	public String deleteEntry(int ID) throws SQLException {
		MySQLAccess MS = new MySQLAccess();
		String result = MS.removeTransaction(ID);
		System.out.println(result);
		return result;
	}

	public String updateEntry(String ID, String NameOnCard, String CardNumber, String UnitPrice, String Quantity,
			String Month, String Year) {

		Boolean quantity = false;
		Boolean unitPrice = false;
		int count = 0;
		String output = "";

		MySQLAccess MS = new MySQLAccess();
		Transaction trxn = new Transaction();

		int tempID = 0;
		double tempUnitPrice = 0.0;
		int tempQuantity = 0;
		int tempMonth = 0;
		int tempYear = 0;
		boolean month = false;
		boolean year = false;

		try {

			if (ID != null) {

				tempID = Integer.parseInt(ID);
				trxn.setiD(tempID);
			}

			else {
				count++;

			}

			if (NameOnCard != null) {
				if (NameOnCard.matches(".*[;:!@#$%^*+?<>].*")) {
					count++;

				}

				else {
					trxn.setNameOnCard(NameOnCard);
				}
			}

			if (CardNumber != null) {

				if (CardNumber.matches(".*[;:!@#$%^*+?<>].*")) {

					count++;

				} else {

					trxn.setCardNumber(CardNumber);

					String CardType = "";
					if (CardNumber.matches("^[5][1-5].*") && CardNumber.length() == 16) {
						CardType = "MasterCard";
						trxn.setCardType(CardType);

					} else if (CardNumber.matches("^[4].*") && CardNumber.length() == 16) {
						CardType = "Visa";
						trxn.setCardType(CardType);

					} else if (CardNumber.matches("^[3][4|7].*") && CardNumber.length() == 15) {
						CardType = "American Express";
						trxn.setCardType(CardType);

					} else {
						CardType = "Other";
						trxn.setCardType(CardType);
					}
				}
			}

			if (UnitPrice != null)

			{

				unitPrice = true;
				tempUnitPrice = Double.parseDouble(UnitPrice);
				trxn.setUnitPrice(tempUnitPrice);

			}

			if (Quantity != null)

			{
				quantity = true;
				tempQuantity = Integer.parseInt(Quantity);
				trxn.setQuantity(tempQuantity);
			}

			if ((quantity == true) || (unitPrice == true)) {
				double total = trxn.getUnitPrice() * trxn.getQuantity();
				trxn.setTotalPrice(total);
			}

			if (Month != null) {
				tempMonth = Integer.parseInt(Month);
				if (tempMonth > 12 || tempMonth < 1) {
					count++;

				} else {
					month = true;
				}
			}

			if (Year != null) {
				tempYear = Integer.parseInt(Year);
				if (tempYear < 2015 || tempYear > 2032) {
					count++;
				} else {
					year = true;
				}
			}

			String expDate = tempMonth + "/" + tempYear;
			if (expDate.matches(".*[;:!@#$%^*+?<>].*")) {
				count++;
			}

			else

			if ((month == true) || (year == true)) {

				{
					trxn.setExpDate(expDate);
				}
			}

			if (count == 0) {
				output = MS.updateTransaction(trxn);
			} else {
				output = "Sorry, Input Values for update were incorrect.";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;

	}

}