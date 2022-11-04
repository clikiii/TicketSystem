package userInterface;

import java.util.ArrayList;

import ticketSystem.Flight;

public class PurchaseTicket {
	
	private ArrayList<ArrayList<Flight>> allFlights = new ArrayList<ArrayList<Flight>>();
	
	public PurchaseTicket(ArrayList<ArrayList<Flight>> aAllFlights) {
		this.allFlights = aAllFlights;
		new PurchaseTicket();
	}

	public PurchaseTicket() {
		
	}
}
