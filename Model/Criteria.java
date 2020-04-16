package Model;


public class Criteria {
	
	private String houseType;
	private int bed;
	private int bath;
	private int furnished;	//1 for not furnished, 2 for furnished
	private String quadrant;
	
	public Criteria(String h, int bed, int bath, int f, String q) {
		
		houseType = h;
		this.bed = bed;
		this.bath = bath;
		furnished = f;
		quadrant = q;
	}
	
	public String getType() {
		return houseType;
	}
	
	public int getBed() {
		return bed;
	}
	
	public int getBath() {
		return bath;
	}
	
	public int getFurnished() {
		return furnished;
	}
	
	public String getQuad() {
		return quadrant;
	}
	public String toString() {
		String furnish;
		if(furnished == 1)
			furnish = "No";
		else furnish = "Yes";
		return  ("House type: "+houseType+ ", # of Beds: " + bed + ", # of bathrooms:" + bath + ", Furnished:" + furnish + ", Quadrant: " + quadrant);
	} 
}
