package Model;
import java.util.ArrayList;

public class RegularRenter extends Renter {

	@Override
	ArrayList<Listing> search(ArrayList<Listing> theListings, Criteria c) {

		ArrayList<Listing> filteredListings = new ArrayList<Listing>();

		for (int i = 0; i < theListings.size(); i++) {
			if (checkMatch(c, theListings.get(i).getCriteria()) != false) {
				filteredListings.add(theListings.get(i));
			}
		}

		return filteredListings;
	}

	public boolean checkMatch(Criteria c1, Criteria c2) {


		if (c1.getBath() != 0 && c1.getBath() != c2.getBath())
			return false;

		if (c1.getBed() != 0 && c1.getBed() != c2.getBed())
			return false;

		if (c1.getFurnished() != 0 && c1.getFurnished() != c2.getFurnished())
			return false;

		if (c1.getType() != null && c1.getType().compareTo(c2.getType()) != 0)
			return false;
		
		if (c1.getQuad() != null && c1.getQuad().compareTo(c2.getQuad()) != 0) {
			return false;

		}

		return true;
	}

	public void Email(Listing list) {
		String message = " "; // gets inputed
	}

	@Override
	void rent(Property p, Landlord l) {
		l.changeState(p, "Rented");
	}




}
