package Model;
import java.util.ArrayList;

abstract class Renter extends Person {
	
	abstract ArrayList<Listing> search(ArrayList<Listing> theListings, Criteria c);
	abstract void rent(Property p, Landlord l);
}
