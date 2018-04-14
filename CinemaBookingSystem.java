import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The main driver class for this system.
 * @author timluo1
 */
public class CinemaBookingSystem {
	
	private static Scanner sc = null;
	
	public static void main (String args[]) {
		
		try {
			sc = new Scanner (new File (args[0]));
			readFile();
			}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			}
		finally {
	        if (sc != null) sc.close();
		}
	}
	
	/**
	 * This readFile handles all commands from the file so long as there is
	 * an input. It assumes all inputs are correct.
	 * @invariable All inputs are correct, and the only errors that can be
	 * made are handled by "Booking rejected", "Change rejected" or
	 * "Cancel rejected"
	 * @invariable Cinemas are declared before Sessions, followed by
	 * everything else.
	 * @invariable All inputs are declared in their correct type. (ie
	 * Cinema cannot have cinemaID of ASdkjashd which is a string, when
	 * it is meant to be an int)
	 */
	public static void readFile() {
		
		/*
		 * The entire system is dependent on an array list of cinemas
		 * and an array list of sessions, which are initialised
		 * in this function.
		 */
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		// While the file has another line
		while(sc.hasNext()) {
			String a = sc.next();
			// If it is a cinema command
			if (a.equals("Cinema")) {
				int cinemaNum = sc.nextInt();
				String rowName = sc.next();
				int numOfSeats = sc.nextInt();
				// First cinema
				if (cinemas.size() == 0) {
					Cinema firstCinema = new Cinema(cinemaNum);
					firstCinema.insertRowAndSeatDetails(rowName, numOfSeats);
					cinemas.add(firstCinema);
				} else {
					int i = 0;
					int alreadyExists = 0;
					int indexOfExistingCinema = 0;
					for (i = 0; i < cinemas.size(); i++) {
						Cinema currentCinema = cinemas.get(i);
						if (currentCinema.getCinemaNum() == cinemaNum) {
							alreadyExists = 1;
							indexOfExistingCinema = i;
						}
					}
					//1. If cinema does not already exist
					//2. If cinema does exist - it is added to array list of cinemas
					Cinema newCinema = new Cinema(cinemaNum);
					if (alreadyExists == 1) {
						Cinema existingCinema = cinemas.get(indexOfExistingCinema);
						existingCinema.insertRowAndSeatDetails(rowName, numOfSeats);
					} else {
						newCinema.insertRowAndSeatDetails(rowName, numOfSeats);
						cinemas.add(newCinema);
					}
				}
			// If it is a session command
			} else if (a.equals("Session")) {
				int cinemaNum = sc.nextInt();
				String movieTime = sc.next();
				String movieName = sc.nextLine();
				// Check validity and get cinema
				int i = 0;
				int alreadyExists = 0;
				int indexOfExistingCinema = 0;
				for (i = 0; i < cinemas.size(); i++) {
					Cinema currentCinema = cinemas.get(i);
					if (currentCinema.getCinemaNum() == cinemaNum) {
						alreadyExists = 1;
						indexOfExistingCinema = i;
					}
				}
				assert (alreadyExists == 1);
				// Cinema is deep copied into session and session added to array list of sessions.
				Cinema existingCinema = cinemas.get(indexOfExistingCinema);
				Cinema existingCinemaCopy = existingCinema.deepClone();
				Session newSession = new Session(movieName, movieTime, existingCinemaCopy);
				sessions.add(newSession);
			// If command is for a booking request
			} else if (a.equals("Request")) {
				int bookingID = sc.nextInt();
				int cinemaNum = sc.nextInt();
				String time = sc.next();
				int numOfTickets = sc.nextInt();
				int i = 0;
				int sessionIndex = 0;
				int valid = 0;
				// Check validity
				for (i = 0; i < sessions.size(); i++) {
					Session currentSession = sessions.get(i);
					if (currentSession.cinema.CinemaNum == cinemaNum && currentSession.movieTime.equals(time)) {
						sessionIndex = i;
						valid = 1;
					}
				}
				if (valid != 1) {
					System.out.println("Booking rejected");
				// Insert valid booking into corresponding session
				} else {
					Session matchingSession = sessions.get(sessionIndex);
					matchingSession.insertBooking(bookingID, numOfTickets, 0);
				}
			// If command is for a change
			} else if (a.equals("Change")) {
				int bookingID = sc.nextInt();
				int cinemaNum = sc.nextInt();
				String time = sc.next();
				int numOfTickets = sc.nextInt();
				int i = 0;
				int sessionIndex = 0;
				int valid = 0;
				// Check validity
				if (sessions.size() == 0) {
					System.out.println("Change rejected");
				} else {
					for (i = 0; i < sessions.size(); i++) {
						Session currentSession = sessions.get(i);
						if (currentSession.cinema.CinemaNum == cinemaNum && currentSession.movieTime.equals(time)) {
							sessionIndex = i;
							valid = 1;
						}
					}
					Session matchingSession = sessions.get(sessionIndex);
					if (valid != 1 && matchingSession.checkChangeAvailability(bookingID, numOfTickets) == 0) {
						System.out.println("Change rejected");
					} else {
						/*
						 *  If there is enough space for change, cancel the original booking
						 *  and insert a booking with the same details but now it's ticket
						 *  size is bigger / smaller.
						 */
						for (i = 0; i < sessions.size(); i++) {
							Session currentSession = sessions.get(i);
							for (int j = 0; j < currentSession.Bookings.size(); j++) {
								Booking currentBooking = currentSession.Bookings.get(j);
								if (currentBooking.bookingID == bookingID) {
									currentSession.cancelBooking(bookingID, 1);
								}
							}
						}
						matchingSession.cancelBooking(bookingID, 1);
						matchingSession.insertBooking(bookingID, numOfTickets, 1);
					}
				}
			// If the command is for a cancel
			} else if (a.equals("Cancel")) {
				int bookingID = sc.nextInt();
				int i = 0;
				int j = 0;
				int valid = 0;
				// Check validity
				for (i = 0; i < sessions.size(); i++) {
					Session currentSession = sessions.get(i);
					for (j = 0; j < currentSession.Bookings.size(); j++) {
						Booking currentBooking = currentSession.Bookings.get(j);
						if (currentBooking.bookingID == bookingID) {
							currentSession.cancelBooking(bookingID, 0);
							valid = 1;
						}
					}
				}
				if (valid == 0) {
					System.out.println("Cancel rejected");
				}
			// If the command is to print
			} else if (a.equals("Print")) {
				int cinemaNum = sc.nextInt();
				String time = sc.next();
				int i = 0;
				int sessionIndex = 0;
				/*
				 *  Navigate to the correct session and print
				 *  all the information
				 */
				for (i = 0; i < sessions.size(); i++) {
					Session currentSession = sessions.get(i);
					if (currentSession.cinema.CinemaNum == cinemaNum && currentSession.movieTime.equals(time)) {
						sessionIndex = i;
					}
				}
				Session matchingSession = sessions.get(sessionIndex);
				matchingSession.printBookingInfo();
			}
		}
		/*
		 * If end is reached and there are no more lines to be read,
		 * the memory for the array list of cinemas is cleared
		 * and the memory for the array list of sessions is also cleared.
		 */
		cinemas.clear();
		sessions.clear();
	}
	
}
