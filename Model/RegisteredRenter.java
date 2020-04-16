package Model;

import java.util.ArrayList;


public class RegisteredRenter extends Renter implements Observer {

	private String password;
	private String username;
	private ArrayList<Criteria> prevSearches;
	private Subject subject;
	
	public RegisteredRenter(String u, String p, ArrayList<Criteria> prev, Subject s) {
		username = u;
		password = p;
		prevSearches = prev;
		subject = s;
		s.registerObserver(this);
	}
	
	
	
	public void update(Listing l) {
		
		for(int j = 0; j < prevSearches.size(); j++) {
			if(checkMatch(prevSearches.get(j), l.getCriteria())) {
				System.out.println("New listing added that matches your search criteria.");
				l.display();
			}
		}
	}

	@Override
	ArrayList<Listing> search(ArrayList<Listing> theListings, Criteria c) {

		addSearch(c);
		
		ArrayList<Listing> filteredListings = new ArrayList<Listing>();

		for (int i = 0; i < theListings.size(); i++) {
			if (checkMatch(c, theListings.get(i).getCriteria())) {
				filteredListings.add(theListings.get(i));
			}
		}

		return filteredListings;
	}
	
	public void addSearch(Criteria c) {
		prevSearches.add(c);
	}

	public boolean checkMatch(Criteria c1, Criteria c2) {

		if (c1.getBath() != 0 && c1.getBath() != c2.getBath())
			return false;

		if (c1.getBed() != 0 && c1.getBed() != c2.getBed())
			return false;

		if (c1.getFurnished() != 0 && c1.getFurnished() != c2.getFurnished())
			return false;

		if (c1.getType() != null && c1.getType() != c2.getType())
			return false;
		
		if (c1.getQuad() != null && c1.getQuad() != c2.getQuad())
			return false;

		return true;
	}

	public void Email(Listing list) {
		String message = " "; // gets inputed
	}

	@Override
	void rent(Property p, Landlord l) {
		l.changeState(p, "rented");
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	

}
