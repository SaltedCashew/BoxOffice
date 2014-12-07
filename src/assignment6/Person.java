package assignment6;

public class Person
{
	private String ticket;
	private String sellingBoxOffice;
	
	public Person()
	{
		ticket = new String ();
	}
	
	public void setTicket(Seat s, String n)
	{
		ticket = s.toString();
		sellingBoxOffice = n;
		System.out.println("Box Office " + sellingBoxOffice + ": Reserved " + ticket);
	}

}
