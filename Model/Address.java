package Model;


public class Address {

	private String street;
	private String city;
	private String country;
	private String houseNum;
	private String quadrant;
	
	public Address(String h, String s, String q, String c, String co) {
		
		street = s;
		city = c;
		country = co;
		houseNum = h;
		quadrant = q;
	}
	
	public void display() {
		System.out.printf("Address: %s %s %s, %s, %s\n", houseNum, street, quadrant, city, country);
	}
}
