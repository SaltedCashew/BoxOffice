package assignment6;

/**
 * Person Class - allows a person to have a ticket and info of the selling Box Office
 * @author Zachary Subealdea, Brad Gray 
 * @version 1.0
 **/
public class Person
{
	private String ticket;
	private String sellingBoxOffice;
	
	/**
	 * Constructor creates a new Person who acts as a customer for the theater.
	 * Each person has a ticket with their seating information plus which box office serviced them.
	 **/
	public Person()
	{
		ticket = new String ();
	}
	
	/**
	 * Sets the person's ticket information as well as which Box office serviced them. 
	 * prints out the ticket information to the console.
	 * @param s Seat object
	 * @param n Box office that served this person
	 **/
	public void setTicket(Seat s, String n)
	{
		ticket = s.toString();
		sellingBoxOffice = n;
		System.out.println("Box Office " + sellingBoxOffice + ": Reserved " + ticket);
	}

}
