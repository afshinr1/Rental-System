package Model;

public class Manager extends Person{
	private String username;
	private String password;
	private ListingArrayListSubject listings;
	
	public Manager(String username, String password, ListingArrayListSubject theListings) {
		this.username = username;
		this.password = password;
		listings = theListings;
	}

}
