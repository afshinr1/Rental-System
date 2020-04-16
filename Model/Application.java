package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Application {

	ListingArrayListSubject theListings;
	Landlord theLandlord;
	Manager theManager;
	RegularRenter regRenter;
	ArrayList<RegisteredRenter> registeredRenters;
	RegisteredRenter theRRenter;
	ArrayList<String> usernames;
	ArrayList<String> managerUsernames;
	Connection myConn;
	ResultSet myRs;
	public Application() {
		 try {
			 
				usernames = new ArrayList<String>();
				managerUsernames = new ArrayList<String>();
				theListings = new ListingArrayListSubject();
				regRenter = new RegularRenter();
				registeredRenters = new ArrayList<RegisteredRenter>();


			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf480", "root",
						"kuraikami");
			Statement myStatement = myConn.createStatement();
			 myRs = myStatement.executeQuery("SELECT * from listing");

			 
				while (myRs.next()) {
					int ID = myRs.getInt("ID");
					int fee = myRs.getInt("fee");
					int period = myRs.getInt("period");
					String address = myRs.getString("address");
					
					Criteria crit = new Criteria(myRs.getString("type"), myRs.getInt("bed"),myRs.getInt("bath"), myRs.getInt("furnished")
							, myRs.getString("quadrant"));
					
					String state = myRs.getString("state");
					Landlord newLandlord = new Landlord(new Name(myRs.getString("fname"), myRs.getString("lname")), myRs.getString("email"), new ArrayList<Property>());
					
					Property newProperty = new Property(ID,fee,period,address, crit);
					newProperty.changeState(state);
					
					Listing newListing = new Listing(newLandlord, newProperty);
					theListings.addListing(newListing);
				}
				
				 myRs = myStatement.executeQuery("SELECT * from registeredrenters");
				 while (myRs.next()) {
					 String username = myRs.getString("Username");
					 String password = myRs.getString("Password");
					 usernames.add(username);
				 }
				 
				 myRs = myStatement.executeQuery("SELECT * from managers");
				 while (myRs.next()) {
					 String Musername = myRs.getString("Username");
					 String Mpassword = myRs.getString("Password");
					 managerUsernames.add(Musername);
			 }
				 
				 System.out.println(managerUsernames.size());
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	

		

	}

	public boolean checkManagerExist(String username, String password) {

		try {

			String query = "SELECT * FROM managers WHERE Username = ? AND Password = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			ResultSet rs = pStat.executeQuery();

			if (!rs.next())
				return false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		theManager = new Manager(username, password, theListings);
		return true;

	}

	public boolean addNewManager(String username, String password) {
		try {
			for(int i=0; i<managerUsernames.size(); i++) {
				if (managerUsernames.get(i).compareTo((username)) == 0)
						return false;
			}
			
			String query = "INSERT INTO managers (Username, Password) VALUES (?, ?)";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.executeUpdate();

			System.out.printf("Creating new Manager w/ user: %s and pass: %s\n", username, password);
			theManager = new Manager(username, password,theListings);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean addNewRegRenter(String username, String password) {

		try {
			for(int i=0; i<usernames.size(); i++) {
				if (usernames.get(i).compareTo((username)) == 0)
						return false;
			}
			
			String query = "INSERT INTO registeredrenters (Username, Password) VALUES (?, ?)";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.executeUpdate();

			System.out.printf("Creating new regRenter w/ user: %s and pass: %s\n", username, password);
			theRRenter = new RegisteredRenter(username, password, new ArrayList<Criteria>(), theListings);
			registeredRenters.add(theRRenter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean checkRegRenterList(String username, String password) {

		try {
	

			String query = "SELECT * FROM registeredrenters WHERE Username = ? AND Password = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			ResultSet rs = pStat.executeQuery();

			if (!rs.next())
				return false;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		theRRenter = new RegisteredRenter(username, password, new ArrayList<Criteria>(), theListings);
		return true;

	}

	public String searchProperty(String info) {
		String[] split = info.split(" ");
		System.out.println(info);
		Criteria cr = getCriteria(split);
		ArrayList<Listing> filteredListings = regRenter.search(theListings.getListings(), cr);

		String list = "";
		for (int i = 0; i < filteredListings.size(); i++) {
			System.out.println(filteredListings.get(i).getProperty().getID());
			list += filteredListings.get(i).getProperty().toString();
		}
		return list;
	}

	private Criteria getCriteria(String[] split) {
		String h = null;
		int bed = 0;
		int bath = 0;
		int furn = 0;
		String quad = null;

		for (int i = 0; i < split.length; i++) {
			if (i == 0) {
				if (!split[0].matches("[a-zA-Z0-9]+"))
					h = null;
				else
					h = split[0].trim();
			}

			if (i == 1) {
				if (!split[1].matches("[a-zA-Z0-9]+"))
					bed = 0;
				else
					bed = Integer.parseInt(split[1]);

			}
			if (i == 2) {
				if (!split[2].matches("[a-zA-Z0-9]+"))
					bath = 0;
				else
					bath = Integer.parseInt(split[2]);

			}
			if (i == 3) {
				if (!split[3].matches("[a-zA-Z0-9]+"))
					furn = 0;
				else if (split[3].compareTo("Y") == 0)
					furn = 2;
				else if (split[3].compareTo("N") == 0)
					furn = 1;

			}
			if (i == 4) {
				if (!split[4].matches("[a-zA-Z0-9]+"))
					quad = null;
				else
					quad = split[4].trim();

			}
		}

		Criteria newCrit = new Criteria(h, bed, bath, furn, quad);
		return newCrit;

	}

	public String getAllListings() {
		String list = "";
		for (int i = 0; i < theListings.getListings().size(); i++) {
			list += theListings.getListings().get(i).getProperty().toString();
		}
		return list;
	}

	public boolean checkID(String id) {
		if (!id.matches("-?\\d+(\\.\\d+)?"))
			return false;
		int theID = Integer.parseInt(id);
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theID == theListings.getListings().get(i).getProperty().getID())
				return true;
		}
		return false;
	}

	public void sendMessage(int id, String message) {
		try {
		System.out.println(id + message);
		for(int i=0; i<theListings.getListings().size(); i++) {
			if(theListings.getListings().get(i).getProperty().getID() == id) {
				String email = theListings.getListings().get(i).getLandlord().getEmail();
			
				String query = "INSERT INTO emails (emailadd, emailinfo) VALUES (?, ?)";
				PreparedStatement pStat = myConn.prepareStatement(query);
				pStat.setString(1, email);
				pStat.setString(2, message);
				pStat.executeUpdate();

				
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public boolean rent(String id) {
		try {
			String query = "UPDATE listing SET state = ? WHERE ID = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);

		int theID = Integer.parseInt(id);
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theID == theListings.getListings().get(i).getProperty().getID()
					&& theListings.getListings().get(i).getProperty().getState().compareTo("Active") == 0) {
				theListings.getListings().get(i).getProperty().changeState("Rented");
				pStat.setString(1, "Rented");
				pStat.setInt(2, theID);
				pStat.executeUpdate();

				return true;
			}
		}
		return false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void newLandlord(String fname, String lname, String email) {
		theLandlord = new Landlord(new Name(fname, lname), email, new ArrayList<Property>());
		System.out.println(fname + " " + lname + " " + email);
	}

	public int registerNewProperty(String id, String address, Criteria c) {

		try {

			if (!id.matches("-?\\d+(\\.\\d+)?"))
				return 0;


			String query = "INSERT INTO listing (ID, fee, period, address, type, bed, bath, furnished, quadrant, fname, lname, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
			Random r = new Random();
			int fee = r.nextInt((1000 - 100) + 1) + 100;
			r = new Random();
			int period = r.nextInt((24 - 1) + 1) + 1;

			boolean check = checkID(id);
			int ID = Integer.parseInt(id);
			if (check == true)
				return 1;

			Property newProperty = new Property(ID, fee, period, address, c);

			theLandlord.registerProperty(newProperty);

			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setInt(1, ID);
			pStat.setInt(2, fee);
			pStat.setInt(3, period);
			pStat.setString(4, address);
			pStat.setString(5, c.getType());
			pStat.setInt(6, c.getBed());
			pStat.setInt(7, c.getBath());
			pStat.setInt(8, c.getFurnished());
			pStat.setString(9, c.getQuad());
			pStat.setString(10, theLandlord.getName().getFname());
			pStat.setString(11, theLandlord.getName().getLname());
			pStat.setString(12, theLandlord.getEmail());
			pStat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

	public String getLandlordListings() {
		String list = "";
		System.out.println(theLandlord.getPropertyList().size());
		for (int i = 0; i < theLandlord.getPropertyList().size(); i++) {
			list += theLandlord.getPropertyList().get(i).getInfo();
		}
		System.out.println(list);
		return list;
	}

	public boolean pay(String id) {

		try {

			if (!id.matches("-?\\d+(\\.\\d+)?"))
				return false;


			String query = "UPDATE listing SET state = ? WHERE ID = ?";

			PreparedStatement pStat = myConn.prepareStatement(query);

			int ID = Integer.parseInt(id);
			for (int i = 0; i < theLandlord.getPropertyList().size(); i++) {
				if (theLandlord.getPropertyList().get(i).getID() == ID
						&& theLandlord.getPropertyList().get(i).getPaid() != true) {
					theLandlord.payFees(theLandlord.getPropertyList().get(i), theListings);
					
					pStat.setInt(2, ID);
					pStat.setString(1, "Active");
				
					pStat.executeUpdate();

					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int changeStateLandlord(String id, String newState) {
		try {
		
		String query = "UPDATE listing SET state = ? WHERE ID = ?";
		PreparedStatement pStat = myConn.prepareStatement(query);

		
		if (!id.matches("-?\\d+(\\.\\d+)?") || !newState.matches("-?\\d+(\\.\\d+)?"))
			return 0;
		int ID = Integer.parseInt(id);
		int state = Integer.parseInt(newState);
		if(state < 0 || state > 3)
			return 1;
	String state2 = "";	
		if(state == 0)
			state2 = "Active";
		if(state == 1)
			state2 = "Rented";
		if(state == 2)
			state2 = "Cancelled";
		if(state == 3)
			state2 = "Suspended";
		
		
		for (int i = 0; i < theLandlord.getPropertyList().size(); i++) {
			if (theLandlord.getPropertyList().get(i).getID() == ID
					&& theLandlord.getPropertyList().get(i).getState() != "Inactive") {
				theLandlord.getPropertyList().get(i).changeState(state2);
				
				pStat.setString(1, state2);
				pStat.setInt(2,  ID);
				pStat.executeUpdate();
				return 3;
			}
		}
		
		return 2;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void unsubscribe() {
	
		try {
			
			String username = theRRenter.getUsername();
			System.out.println(username);
			String query  = "DELETE FROM registeredrenters WHERE Username = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getAllListingsManagers() {
		String list = "";
		for (int i = 0; i < theListings.getListings().size(); i++) {
			list += theListings.getListings().get(i).getLandlordInfo();
			list += theListings.getListings().get(i).getProperty().getInfo();
		}
		return list;
	}

	public int changeStateManager(String id, String newState) {
		try {
			
		String query = "UPDATE listing SET state = ? WHERE ID = ?";
		PreparedStatement pStat = myConn.prepareStatement(query);

		
		if (!id.matches("-?\\d+(\\.\\d+)?") || !newState.matches("-?\\d+(\\.\\d+)?"))
			return 0;
		int ID = Integer.parseInt(id);
		int state = Integer.parseInt(newState);
		if(state < 0 || state > 3)
			return 1;
	String state2 = "";	
		if(state == 0)
			state2 = "Active";
		if(state == 1)
			state2 = "Rented";
		if(state == 2)
			state2 = "Cancelled";
		if(state == 3)
			state2 = "Suspended";
		
		
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theListings.getListings().get(i).getProperty().getID() == ID) {
				theListings.getListings().get(i).getProperty().changeState(state2);
				
				pStat.setString(1, state2);
				pStat.setInt(2,  ID);
				pStat.executeUpdate();

				return 3;
			}
		}
		
		return 2;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int changeFeeManager(String id, String newFee) {
		try {
		String query = "UPDATE listing SET fee = ? WHERE ID = ?";
		PreparedStatement pStat = myConn.prepareStatement(query);

		
		if (!id.matches("-?\\d+(\\.\\d+)?") || !newFee.matches("-?\\d+(\\.\\d+)?"))
			return 0;	
		int ID = Integer.parseInt(id);
		int fee = Integer.parseInt(newFee);
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theListings.getListings().get(i).getProperty().getID() == ID) {
				theListings.getListings().get(i).getProperty().setFee(fee);
				
				pStat.setInt(1, fee);
				pStat.setInt(2,  ID);
				pStat.executeUpdate();

				return 3;
			}
		}
		
		return 2;
		} catch(Exception e) {
		e.getStackTrace();
		}
		return 0;
	}

	public int changePeriodManager(String id, String newPeriod) {
		try {
			
		String query = "UPDATE listing SET period = ? WHERE ID = ?";
		PreparedStatement pStat = myConn.prepareStatement(query);

		
		if (!id.matches("-?\\d+(\\.\\d+)?") || !newPeriod.matches("-?\\d+(\\.\\d+)?"))
			return 0;
		int ID = Integer.parseInt(id);
		int period = Integer.parseInt(newPeriod);
		if(period < 1 || period > 24)
			return 1;
		
		
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if (theListings.getListings().get(i).getProperty().getID() == ID) {
				theListings.getListings().get(i).getProperty().changePeriod(period);
				
				pStat.setInt(1, period);
				pStat.setInt(2,  ID);
				pStat.executeUpdate();

				return 3;
			}
		}
		
		return 2;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getSummary() {
		Random r = new Random();
		int period = r.nextInt((24 - 1) + 1) + 1;
		int housesRented = 0;
		int activeListings = 0;
		String rentedList = "";
		String list = "";
		for (int i = 0; i < theListings.getListings().size(); i++) {
			if(theListings.getListings().get(i).getProperty().getPeriod() <= period) {
			list += theListings.getListings().get(i).getLandlordInfo();
			list += theListings.getListings().get(i).getProperty().getInfo();
			if(theListings.getListings().get(i).getProperty().getState().compareTo("Rented") == 0) {
				housesRented++;
				rentedList += "Name: " + theListings.getListings().get(i).getLandlord().getName().getName() + ", ID: " + theListings.getListings().get(i).getProperty().getID() + ", Address: " + theListings.getListings().get(i).getProperty().getAddress() + "*";
			}
			if(theListings.getListings().get(i).getProperty().getState().compareTo("Active") == 0) {
				activeListings++;
			}
			
			}
		}
		
		String hRented = "The total number of houses rented in this period: " + housesRented + "*";
		String hActive = "The total number of houses Active in this period: " + activeListings + "*";
		list += hRented;
		list+= hActive;
		list+= rentedList;
		return list;
		
	}

	public String getLandlordEmails() {
		String emails  = "";
		try {
			String query = "SELECT emailinfo FROM emails WHERE emailadd = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setString(1, theLandlord.getEmail());
			ResultSet rs = pStat.executeQuery();
			 while (rs.next()) {
				emails += rs.getString("emailinfo") + "*";
				
			 }
			 		 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return emails;

	}

}
