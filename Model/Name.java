package Model;


public class Name {
	
	private String fname;
	private String lname;
	
	public Name(String f, String l) {
		fname = f;
		lname = l;
	}
	
	
	public void display() {
		System.out.printf("Name: %s %s\n", fname, lname);
	}
	
	public String getName() {
		return fname +" " + lname;
	}
	
	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}
}
