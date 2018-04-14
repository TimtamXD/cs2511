/**
 * The Booking class stores information about a booking and it's place within the row object.
 * @author timluo1
 */
public class Booking {
	
	/**
	 * These are the attributes of the Booking object.
	 * bookingID: The id of the booking.
	 * numberOfTickets: The number of tickets taken by the booking.
	 * seatIndexStart: The seat number or index of the first seat in the row object.
	 * seatIndexEnd: The last seat or last index of the seat in the row object.
	 */
	public int bookingID;
	public int numberOfTickets;
	public int seatIndexStart;
	public int seatIndexEnd;
	public String row;
	
	/**
	 * Class constructor.
	 * @param bookingID: The id of the booking.
	 * @param numberOfTickets: The number of tickets taken by the booking.
	 */
	public Booking(int bookingID, int numberOfTickets) {
		super();
		this.bookingID = bookingID;
		this.numberOfTickets = numberOfTickets;
	}
	
	/**
	 * Getter method to obtain booking id.
	 * @return bookingID the id of the booking.
	 */
	public int getBookingID() {
		return bookingID;
	}
	
	/**
	 * Sets the booking id.
	 * @param bookingID the id of the booking.
	 */
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	/**
	 * Gets the number of tickets in this booking.
	 * @return numberOfTickets the number of tickets in this booking
	 */
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	
	/**
	 * Sets the number of tickets in this booking.
	 * @param numberOfTickets the number of tickets in this booking
	 */
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	
	/**
	 * Gets the starting point of the allocated seats in the row with the booking.
	 * @return seatIndexStart the starting index of the seat in the row
	 */
	public int getSeatIndexStart() {
		return seatIndexStart;
	}
	
	/**
	 * Gets the starting point of the allocated seats in the row with the booking.
	 * @param seatIndexStart the starting index of the seat in the row
	 */
	public void setSeatIndexStart(int seatIndexStart) {
		this.seatIndexStart = seatIndexStart;
	}
	
	/**
	 * Gets the ending point of the allocated seats in the row with the booking.
	 * @return seatIndexEnd the ending index of the seat in the row
	 */
	public int getSeatIndexEnd() {
		return seatIndexEnd;
	}
	
	/**
	 * Gets the ending point of the allocated seats in the row with the booking.
	 * @param seatIndexEnd the ending index of the seat in the row
	 */
	public void setSeatIndexEnd(int seatIndexEnd) {
		this.seatIndexEnd = seatIndexEnd;
	}
	
	/**
	 * Gets the row string of the booking.
	 * @return row the row where the booking is stored.
	 */
	public String getRow() {
		return row;
	}
	
	/**
	 * Sets the row string of the booking
	 * @param row the row where the booking is stored.
	 */
	public void setRow(String row) {
		this.row = row;
	}
	
	/**
	 * Creates a deep clone of the booking
	 * @return clone a copy of the current booking
	 */
	public Booking deepClone() {
		Booking clone = new Booking(this.bookingID, this.numberOfTickets);
		clone.seatIndexStart = this.seatIndexStart;
		clone.seatIndexEnd = this.seatIndexEnd;
		clone.row = row;
		return clone;
	}
}