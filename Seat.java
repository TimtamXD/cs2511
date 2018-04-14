/**
 * The Seat class is representative of the seat object that lies within the row object.
 * @author timluo1
 */
public class Seat {
	
	/**
	 * These are the attributes of the Seat.
	 * seatNum: The number of the seat. Also the index of the position of 
	 * the seat in the row arrayList.
	 * bookingID: The booking ID - the seat is tied to the booking request.
	 * taken: If the seat is booked, taken is 1, otherwise 0.
	 */
	public int seatNum;
	public int bookingID = -1;
	public int taken = 0;
	
	/**
	 * Class constructor. Sets the seat number / position of seat within row arrayList.
	 * @param seatNum location of seat in arrayList
	 * @invariable seatNum must be >= 0
	 */
	public Seat(int seatNum) {
		super();
		this.seatNum = seatNum;
	}
	
	/**
	 * Gets the seat number
	 * @return seatNum Returns the seat number
	 */
	public int getSeatNum() {
		return seatNum;
	}
	
	/**
	 * Sets the seat number
	 * @param seatNum Sets the seat number
	 */
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	/**
	 * Gets the booking ID
	 * @return bookingID Returns the booking ID
	 */
	public int getBookingID() {
		return bookingID;
	}
	
	/**
	 * Sets the booking ID
	 * @param bookingID Sets the booking ID
	 */
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	/**
	 * Function tells whether or not the seat has been taken by a booking
	 * @return taken Gets the value for taken
	 */
	public int isTaken() {
		return taken;
	}
	
	/**
	 * Changes the taken value
	 * @param taken Inserts the value for taken
	 */
	public void setTaken(int taken) {
		this.taken = taken;
	}
	
}
