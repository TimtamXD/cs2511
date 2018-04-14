import java.util.ArrayList;
/**
 * The Session class contains the cinema class, the booking, row and seat classes.
 * It is responsible for sessions, requests, changes, cancels and prints.
 * @author timluo1
 */
public class Session {
	
	/**
	 * These are the attributes of the Session class.
	 * movieName: the name of the movie in this session
	 * movieTime: the time of the movie
	 * Cinema: the associated cinema to this session
	 * Bookings: an array list of all the bookings associated with this session
	 */
	public String movieName;
	public String movieTime;
	public Cinema cinema;
	public ArrayList<Booking> Bookings = new ArrayList<Booking>();
	
	/**
	 * Class constructor
	 * @param movieName initialises the name of the movie in this session
	 * @param movieTime initialises the time of the movie in this session
	 * @param cinema initialises a cinema for this session
	 */
	public Session(String movieName, String movieTime, Cinema cinema) {
		super();
		this.movieName = movieName.substring(1);
		this.movieTime = movieTime;
		this.cinema = cinema;
	}
	
	/**
	 * Gets the movie name in this session
	 * @return movieName the movie name in this session
	 */
	public String getMovieName() {
		return movieName;
	}
	
	/**
	 * Sets the movie name in this session
	 * @param movieName the movie name in this session
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName.substring(1);
	}
	
	/**
	 * Gets the movie time in this session
	 * @return movieTime the movie name in this session
	 */
	public String getMovieTime() {
		return movieTime;
	}
	
	/**
	 * Sets the movie time in this session
	 * @param movieTime the movie name in this session
	 */
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}
	
	/**
	 * Gets the cinema object in this session
	 * @return cinema the cinema in this session
	 */
	public Cinema getCinema() {
		return cinema;
	}
	
	/**
	 * Sets the cinema object in this session
	 * @param cinema the cinema in this sesion
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	/**
	 * This method prints all the information about the bookings associated with this session.
	 * This is for the Print <cinema> <time> command
	 */
	public void printBookingInfo() {
		this.bubbleSortBookingsInSeatOrder();
		System.out.println(this.movieName);
		int i = 0;
		int j = 0;
		int k = 0;
		for (j = 0; j < this.cinema.Rows.size(); j++) {
			Row currentRow = this.cinema.Rows.get(j);
			int bookingExists = 0;
			for (k = 0; k < currentRow.Seating.size(); k++) {
				Seat currentSeat = currentRow.Seating.get(k);
				if (currentSeat.taken == 1) {
					bookingExists = 1;
				}
			}
			if (bookingExists == 1) {
				System.out.printf("%s: ", currentRow.rowName);
				int l = 0;
				for (i = 0; i < Bookings.size(); i++) {
					Booking currentBooking = Bookings.get(i);
					if (currentBooking.row.equals(currentRow.rowName)) {
						if (currentBooking.seatIndexEnd == currentBooking.seatIndexStart) {
							System.out.printf("%d", currentBooking.seatIndexStart);
						} else {
							System.out.printf("%d-%d", currentBooking.seatIndexStart, currentBooking.seatIndexEnd);
						}
						if (l < currentRow.numberOfBookingsInThisRow - 1) {		
							System.out.printf(",");
							l++;
						}
					}
				}
				System.out.printf("\n");
			}
		}
	}
	
	/**
	 * This method inserts the a booking into the Booking ArrayList,
	 * while also checking if it's a valid booking and if there is space.
	 * The method involved is:
	 * Request booking <id> is for <cinema> at <time> for <tickets> tickets
	 * @param bookingID the id of the booking to be added. Used to initialise the Booking object
	 * and other details in the row and seat objects.
	 * @param numOfTickets the number of tickets required for the booking.
	 * @param changeBooking if 1, then the change command was executed. if 0, then the request
	 * command was executed.
	 */
	public void insertBooking(int bookingID, int numOfTickets, int changeBooking) {
		Booking newBooking = new Booking(bookingID, numOfTickets);
		// Iterate through rows
		int i = 0;
		int numOfRows = cinema.Rows.size();
		int rowAvailability = 0;
		for (i = 0; i < numOfRows; i++) {
			Row currentRow = cinema.Rows.get(i);
			// Iterate through seats to find availability
			int availability = currentRow.findAndSetAvailabilityOfSeating(bookingID, numOfTickets);
			if (availability == 1) {
				// If seats are available
				rowAvailability = 1;
				if (changeBooking == 1) {
					// Print differently for either change or request command
					if (currentRow.startIndex == currentRow.endIndex) {
						System.out.printf("Change %d %s%d\n", bookingID, currentRow.rowName, currentRow.startIndex);
					} else {
						System.out.printf("Change %d %s%d-%s%d\n", bookingID, currentRow.rowName, currentRow.startIndex, currentRow.rowName, currentRow.endIndex);
					}
				} else {
					if (currentRow.startIndex == currentRow.endIndex) {
						System.out.printf("Booking %d %s%d\n", bookingID, currentRow.rowName, currentRow.startIndex);
					} else {
						System.out.printf("Booking %d %s%d-%s%d\n", bookingID, currentRow.rowName, currentRow.startIndex, currentRow.rowName, currentRow.endIndex);
					}
				}
				newBooking.row = currentRow.rowName;
				newBooking.seatIndexStart = currentRow.startIndex;
				newBooking.seatIndexEnd = currentRow.endIndex;
				currentRow.numberOfBookingsInThisRow++;
				Bookings.add(newBooking);
				currentRow.resetStartAndEndIndex();
				break;
			}
		}
		// different responding print statements for either a change or a request command
		if (i == numOfRows && rowAvailability == 0 && changeBooking == 0) {
			System.out.println("Booking rejected");
		} else if (i == numOfRows && rowAvailability == 0 && changeBooking == 1) {
			System.out.println("Change rejected");
		}
	}
	
	/**
	 * This method cancels a booking if it exists and accommodates for either a request or a change
	 * or a cancel command.
	 * @param bookingID the id of the booking to be cancelled
	 * @param changeBooking if 1, then the change command was executed. if 0, then the request
	 * command was executed.
	 */
	public void cancelBooking(int bookingID, int changeBooking) {
		int i = 0;
		int j = 0;
		int indexOfRemovedRow = -1;
		for (i = 0; i < Bookings.size(); i++) {
			Booking currentBooking = Bookings.get(i);
			if (currentBooking.bookingID == bookingID && changeBooking == 0) {
				Bookings.remove(i);
				System.out.printf("Cancel %d\n", bookingID);
				break;
			} else if (currentBooking.bookingID == bookingID && changeBooking == 1) {
				Bookings.remove(i);
				break;
			}
		}
		for (i = 0; i < cinema.Rows.size(); i++) {
			Row currentRow = cinema.Rows.get(i);
			for (j = 0; j < currentRow.Seating.size(); j++) {
				Seat currentSeat = currentRow.Seating.get(j);
				if (currentSeat.bookingID == bookingID) {
					indexOfRemovedRow = i;
					currentSeat.bookingID = -1;
					currentSeat.taken = 0;
				}
			}
		}
		/*
		 *  If row where booking was removed exists, then the number of bookings in that row
		 *  is decremented
		 */
		if (indexOfRemovedRow != -1) {
			Row removedRow = cinema.Rows.get(indexOfRemovedRow);
			removedRow.numberOfBookingsInThisRow--;
		}
	}
	
	/**
	 * If a change command is executed, this method checks if there is enough space
	 * for the change to accomodate extra seats.
	 * @param bookingID the id of the booking
	 * @param newNumOfSeats the new amount of seats to be allocated
	 * @return retVal if 1, then there is space for a change, if 0, then there is no space
	 * for a change
	 */
	public int checkChangeAvailability (int bookingID, int newNumOfSeats) {
		// go to booking and corresponding row info
		int retVal = 0;
		int i = 0;
		int matchingBookingIndex = -1;
		int matchingRowIndex = -1;
		int cumulative = 0;
		for (i = 0; i < this.Bookings.size(); i++) {
			Booking currentBooking = this.Bookings.get(i);
			if (currentBooking.bookingID == bookingID) {
				matchingBookingIndex = i; 
			}
		}
		Booking matchingBooking = this.Bookings.get(matchingBookingIndex);
		int extraSeatsRequired = newNumOfSeats - matchingBooking.numberOfTickets;
		if (extraSeatsRequired <= 0) {
			return 1;
		}
		for (i = 0; i < this.cinema.Rows.size(); i++) {
			Row currentRow = this.cinema.Rows.get(i);
			if (currentRow.rowName == matchingBooking.row) {
				matchingRowIndex = i;
			}
		}
		Row matchingRow = this.cinema.Rows.get(matchingRowIndex);
		if (matchingBooking.seatIndexEnd + 1 + extraSeatsRequired > matchingRow.numOfSeats) {
			return 0;
		}
		for (i = matchingBooking.seatIndexEnd + 1; i < matchingBooking.seatIndexEnd + 1 + extraSeatsRequired; i++)  {
			Seat currentSeat = matchingRow.Seating.get(i);
			if (currentSeat.taken == 0) {
				cumulative++;
			}
		}
		if (cumulative == extraSeatsRequired) {
			retVal = 1;
		} else {
			retVal = 0;
		}
		return retVal;
	}
	
	/**
	 * This method ensures the bookings are in orders
	 * so that the printing method is correct -
	 * The seating must be printed in correct ascending order
	 */
	public void bubbleSortBookingsInSeatOrder() {
		int n = this.Bookings.size();
		Booking temp1 = null;
		Booking temp2 = null;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n - i; j++) {
				Booking currentBooking = this.Bookings.get(j);
				Booking previousBooking = this.Bookings.get(j - 1);
				if (previousBooking.seatIndexStart > currentBooking.seatIndexStart) {
					temp1 = previousBooking.deepClone();
					temp2 = currentBooking.deepClone();
					this.Bookings.add(j, temp1);
					this.Bookings.add(j - 1, temp2);
					this.Bookings.remove(j + 1);
					this.Bookings.remove(j + 1);
				}
			}
		}
	}
	
	// DEBUG
	/**
	 * Prints information about the session
	 */
	public void printSessionInfo() {
		System.out.println("DEBUG\nPrinting Session Info...");
		System.out.printf("Cinema: %d, Time: %s, Name: %s\n", this.cinema.CinemaNum, this.movieTime, this.movieName);
		System.out.println("Printing Cinema Info...");
		this.cinema.printRowInfo();
	}
}
