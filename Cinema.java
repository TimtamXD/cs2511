import java.util.ArrayList;
/**
 * The Cinema class contains all the Rows and the information about them.
 * This class does not contain information about the bookings nor sessions.
 * This class is responsible for the creation of the Cinema object.
 * @author timluo1
 */
public class Cinema {
	
	/**
	 * The attributes of the Cinema.
	 * CinemaNum: The number or id of the cinema.
	 * Rows: An array list of the rows in this cinema, which also contains all the seats.
	 */
	public int CinemaNum;
	public ArrayList<Row> Rows = new ArrayList<Row>();
	
	/**
	 * Class constructor.
	 * @param cinemaNum the number or id of the cinema.
	 */
	public Cinema(int cinemaNum) {
		super();
		CinemaNum = cinemaNum;
	}
	
	/**
	 * Gets the cinema id or number.
	 * @return CinemaNum the id or number of this cinema
	 */
	public int getCinemaNum() {
		return CinemaNum;
	}
	
	/**
	 * Sets the cinema id or number.
	 * @param cinemaNum the id or number of this cinema
	 */
	public void setCinemaNum(int cinemaNum) {
		CinemaNum = cinemaNum;
	}
	
	/**
	 * Gets the array list of rows in this cinema.
	 * @return Rows the array list of rows in this cinema
	 */
	public ArrayList<Row> getRows() {
		return Rows;
	}
	
	/**
	 * Sets the array list of rows in this cinema.
	 * @param rows the array list of rows in this cinema
	 */
	public void setRows(ArrayList<Row> rows) {
		Rows = rows;
	}
	
	/**
	 * Specifies that this cinema has a row with a certain number of seats
	 * @precondition rowName is a string with no spaces inserted in alphabetical order
	 * @precondition numOfSeats is >= 0
	 * @param rowName the id or name of the row
	 * @param numOfSeats the number of seats in the row
	 */
	public void insertRowAndSeatDetails (String rowName, int numOfSeats) {
		Row newRow = new Row(rowName, numOfSeats);
		newRow.setSeating(numOfSeats);
		this.Rows.add(newRow);
	}
	
	/**
	 * Deep clones this cinema object; creates not merely a pointer but a new
	 * Cinema object with identical details and content to the original.
	 * @return clone a clone of this cinema and all it's details
	 */
	public Cinema deepClone() {
		Cinema clone = new Cinema(this.CinemaNum);
		int i = 0;
		for (i = 0; i < this.Rows.size(); i++) {
			Row currentRow = this.Rows.get(i);
			String currentRowName = currentRow.rowName;
			int currentRowSeats = currentRow.numOfSeats;
			clone.insertRowAndSeatDetails(currentRowName, currentRowSeats);
		}
		return clone;
	}
	
	// DEBUG
	/**
	 * Prints all the information about the row and it's contents.
	 */
	public void printRowInfo () {
		int i = 0;
		for (i = 0; i < Rows.size(); i++) {
			Row row = Rows.get(i);
			System.out.printf("%s: ", row.rowName);
			row.printSeats();
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}
}
