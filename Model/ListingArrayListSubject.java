package Model;

import java.util.ArrayList;

public class ListingArrayListSubject implements Subject {

	public ArrayList<Listing> listings;
	public ArrayList<Observer> observers;
	
	public ListingArrayListSubject() {
		listings = new ArrayList<Listing>(0);
		observers = new ArrayList<Observer>(0);
	}
	
	public ArrayList<Listing> getListings() {
		return listings;
	}
	
	public void addListing(Listing newListing) {
		listings.add(newListing);
		notifyAllObservers(newListing);
	}
	
	public void setListing(Listing l, int i) {
		listings.set(i, l);
		notifyAllObservers(l);
	}
	
	public void populate(Listing [] arr) {
		for(int i =0; i < arr.length; i++)
			listings.add(arr[i]);
	}
	
	public void display() {
		for(int i = 0; i < listings.size(); i++) 
			listings.get(i).display();
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyAllObservers(Listing newListing) {
		for(int i = 0; i < observers.size(); i++) {
			Observer o = observers.get(i);
			o.update(newListing);
		}
	}
	
}
