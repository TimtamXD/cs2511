import java.util.ArrayList;
/**
 * The Row class contains an ArrayList of Seats.
 * @author timluo1
 */
public class Row {
	
	/**
	 * These are the attributes of Row.
	 * rowName: The character of row. (A, B etc)
	 * Seating: An arrayList of all the seats in the row.
	 * numOfSeats: The number of seats in the row.
	 * startIndex: The starting index (or seat number) of a particular booking.
	 * endIndex: The end index (or seat number) of a particular booking.
	 * numberOfBookingsInThisRow: The number of bookings held in this row.
	 */
	public String rowName;
	public ArrayList<Seat> Seating = new ArrayList<Seat>();
	public int numOfSeats;
	public int startIndex = -1;
	public int endIndex = -1;
	public int numberOfBookingsInThisRow = 0;
	
	/**
	 * Class constructor.
	 * @param rowName Initialises the row name. Must be a string with no spaces.
	 * @param numOfSeats Initialises the number of seats in this row.
	 */
	public Row(String rowName, int numOfSeats) {
		super();
		this.rowName = rowName;
		this.numOfSeats = numOfSeats;
	}
	
	/**
	 * Obtains the row character.
	 * @return rowName the name of the row
	 */
	public String getRowNum() {
		return rowName;
	}
	
	/**
	 * Sets the row name.
	 * @param rowName the name of the row
	 */
	public void setRowNum(String rowName) {
		this.rowName = rowName;
	}
	
	/**
	 * Gets the ArrayList of seats.
	 * @return Seating the array list of seats
	 */
	public ArrayList<Seat> getSeating() {
		return Seating;
	}
	
	/**
	 * Gets the starting index of a booking.
	 * @return startIndex the starting index of a booking
	 */
	public int getStartIndex() {
		return startIndex;
	}
	
	/**
	 * Sets the starting index of a booking.
	 * @param startIndex the starting index of a booking
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	/**
	 * Gets the ending index of a booking.
	 * @return endIndex the ending index of a booking
	 */
	public int getEndIndex() {
		return endIndex;
	}
	
	/**
	 * Sets the ending index of a booking.
	 * @param endIndex the ending index of a booking
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	/**
	 * This method initialises the seating array list by appending seats and their 
	 * seat number (or index).
	 * @param numOfSeats the amount of seats in the Seating array list.
	 */
	public void setSeating(int numOfSeats) {
		int i = 0;
		for (i = 0; i < numOfSeats; i++) {
			// creating arrayList of seats for the row
			Seat newSeat = new Seat(i);
			Seating.add(newSeat);
		}
	}
	
	/**
	 * This method increments the start and end index for a particular booking to
	 * correctly reflect the printed values of seating. For example,
	 * if we did not increment the indexes, then seating can be from 0 - 14 for
	 * a 15 seat row. Whereas the correct printing is 1 - 15.
	 */
	public void incrementStartAndEndIndex () {
		this.startIndex++;
		this.endIndex++;
	}
	
	/**
	 * This method finds if there is seating available when a request / booking or change is made.
	 * @param bookingID The booking id of the request or change.
	 * @param numOfSeats The number of seats needed for a request or change.
	 * @return retVal retVal is 1 for "Yes, seats are available", 0 for "No seats available".
	 */
	public int findAndSetAvailabilityOfSeating(int bookingID, int numOfSeats) {
		int retVal = 0;
		int i = 0;
		int cumulative = 0;
		for (i = 0; i < Seating.size(); i++) {
			Seat seat = Seating.get(i);
			if (seat.taken == 0) {
				if (this.startIndex == -1 ) {
					this.startIndex = i;
				}
				cumulative++;
				// Seats are available consecutively
				if (cumulative == numOfSeats) {
					this.endIndex = i;
					retVal = 1;
					break;
				}
			} else if (seat.taken == 1) {
				cumulative = 0;
				this.startIndex = -1;
				this.endIndex = -1;
			}
		}
		/*
		 * If there is seating available, we must change the seats so that
		 * they are now taken and tied to the bookingID.
		 */
		if (retVal == 1) {
			for (i = this.startIndex; i < this.endIndex + 1; i++) {
				Seat seat = Seating.get(i);
				seat.taken = 1;
				seat.bookingID = bookingID;
			}
		}
		this.incrementStartAndEndIndex();
		return retVal;
	}
	
	/**
	 * This method resets the start and end index for different bookings.
	 */
	public void resetStartAndEndIndex () {
		this.startIndex = -1;
		this.endIndex = -1;
	}
	
	// DEBUG
	/**
	 * This method prints all the seating information of the current available seats
	 * in this row.
	 */
	public void printSeats() {
		int i = 0;
		for (i = 0; i < Seating.size(); i++) {
			Seat seat = Seating.get(i);
			if (seat.taken == 0) {
				System.out.printf("[%d]", seat.bookingID);
				System.out.printf("%d", seat.seatNum);
			}
		}
	}
}
