package Model;
import java.util.ArrayList;

public class Landlord extends Person {
private ArrayList<Property> properties;	
	
public Landlord(Name n, String email, ArrayList<Property> props){
	
	this.name = n;
	this.email = email;
	properties = props;
	
}

public Name getName() {
	return name;
}

public String getEmail() {
	return email;
}

public void registerProperty(Property property) {
	properties.add(property);	
}


public void changeState(Property property, String state) {
	for(int i=0; i< properties.size(); i++) {
		if (properties.get(i).getID() == property.getID()) {
			properties.get(i).changeState(state);
			
		}
	}
	
}

public void payFees(Property property, ListingArrayListSubject theListings) {
	for(int i=0; i<properties.size(); i++) {
		if(properties.get(i).getID() == property.getID()) {
			properties.get(i).setPaid(true);
			properties.get(i).changeState("Active");
			Listing newListing = new Listing(this, properties.get(i));
			theListings.addListing(newListing);
			System.out.println("Paid fees for property:" + property.getID());
		}
	}
	
	
}

public ArrayList<Property> getPropertyList() {
	return properties;
}

public String toString() {
	return name.getName();
}


}


