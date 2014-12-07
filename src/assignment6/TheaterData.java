package assignment6;

import java.util.HashMap;

public class TheaterData
{
	
	public HashMap<String, Integer> theaterSeats;
	public HashMap<String, Integer> theaterRows;
	final int FIRST_SEAT = 101;
	final int LAST_SEAT = 128;
	
	public TheaterData()
	{
		buildTheaterSeats();
		buildTheaterRows();
		buildSeatList();
	}
	
	private void buildTheaterSeats()
	{
		theaterSeats = new HashMap<String, Integer>();
		for(int k = FIRST_SEAT; k < (LAST_SEAT + 1); k++ )
		{
			theaterSeats.put(new String("Seat_" + k), k);
		}
				
	}
	
	private void buildTheaterRows()
	{
		theaterRows = new HashMap<String, Integer>();
		for(int k = 0; k < 25; k++ )
		{
			theaterRows.put(new String("Row_" + k), k);
		}	
	}

	private void buildSeatList()
	{
				
	}

	

}
