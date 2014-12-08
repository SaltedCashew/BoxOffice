package assignment6;

/**
 * Main seat class to build and use seat objects. 
 * Creates seats with section number, row string, seat number and availability (Reserved / Available).
 * @author Brad Gray, Zachary Subealdea
 * @version 1.0
 **/
public class Seat
{
	private enum attribute {RESERVED, AVAILABLE; }
	@SuppressWarnings("unused")
	private attribute availability;
	private String row;
	private int seatNum;
	private int section;
	
	/**
	 * Constructor given row string r and seat number int s.
	 * Assigns row and seat number and uses both to determine the house section
	 * Marks the seat as available
	 * @param r row string
	 * @param s seat number integer 
	 **/
	public Seat(String r, int s)
	{
		row = r;
		seatNum = s;
		section = determineSection(r, s);
		availability = attribute.AVAILABLE;
	}
	
	/**
	 * Returns string using seat information: "Row r, Seat s"
	 * @return string "Row r, Seat s"
	 **/
	@Override
	public String toString()
	{
		return new String("Row " + row + ", Seat " + seatNum);
	}
	
	/**
	 * Converts implicit seat to key string - designed for hashmap function.
	 * Returned string is simply "r s" where r is the row number and s is the seat number.
	 * Purpose is to allow for easier searching in the hashmap
	 * @return string "row seat"  
	 **/
	public String toKey(){
		return new String (this.row + " " + this.seatNum);
	}
	
	/**
	 * Getter method to return the section number of implicit seat object
	 * @return section number int
	 **/
	public int getSection()
	{
		return this.section;
	}
	
	/**
	 * Getter method to return the row of implicit seat object as a String.
	 * Rows stored as strings: "A" through "Z" and "AA"
	 * @return row number String
	 **/
	public String getRow()
	{
		return this.row;
	}
	
	/**
	 * Getter method to return the seat number of implicit seat object
	 * @return seat number int
	 **/
	public int getSeatNumber()
	{
		return seatNum;
	}
	
	/**
	 * Marks the implicit seat object as reserved 
	 **/
	public void markSeatReserved()
	{
		this.availability = attribute.RESERVED;
		
	}
	
	/**
	 * Marks the implicit seat object as reserved 
	 **/
	public void markSeatAvailable()
	{
		this.availability = attribute.AVAILABLE;
		
	}

	//Determines the section number using the given row and seat information
	//Divides the house into 6 sections and assigns each sections int values.
	//Lower section value equates to a higher seating priority
	//"Lower" refers to rowsA-M, "Upper" refers to RowsN-AA, "Left" refers to seat 101-107, "Right" refers to seats 122-128
	//Returns Priorities: Lower Middle=1, Lower Left=2, Lower Right=3, Upper Middle=4, Upper Left=5, Upper Right=6
	private int determineSection(String row, int seat)
	{
		int section = 0;
		if (row.equals("AA")){row = "Z";}
		if(row.compareTo("A") > -1 && row.compareTo("N") < 0)
		{
			if(seat > 107 && seat < 122){section = 1;}
			else if(seat > 100 && seat < 108){section = 2;}
			else{section = 3;}
		}
		else
		{
			if(seat>107 && seat<122){section = 4;}
			else if(seat>100 && seat < 108){section = 5;}
			else{section = 6;}
		}
		return section;
	}
}
