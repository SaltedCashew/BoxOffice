package assignment6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

public class Theater
{
	private String theaterName;
	//private static TheaterData seatingChart;
	private PriorityBlockingQueue<Seat> seatQueue;
	private ArrayList<Seat> seatList;
	private HashMap<String, Seat> seatMap;
	

	public Theater()
	{
		seatList = new ArrayList<Seat>();
		seatMap = new HashMap<String, Seat>();
		buildSeats();
		buildSeatMap();
		Comparator<Seat> seatPriority = new SeatComparator();
		seatQueue = new PriorityBlockingQueue<Seat>(seatList.size(), seatPriority);
		buildSeatQueue();
		for(Seat s : seatQueue){System.out.println(s.toString());}
	}
	
	private void buildSeats() //excludes handicap seats from Bates Hall seat map
	{
		for(int seat = 105; seat < 126; seat++ ) //row A - start at row 1 for better indexing
		{
			seatList.add(new Seat("A", seat));
		}
	 	for(int row = 1; row < 26; row++) //rows B - Z
	 	{
	 		if(row!=8 && row!=14)
	 		{
	 			for(int seat = 101; seat < 129; seat++)
		 		{
		 			int AsciiValue =  row + 65;
		 			seatList.add( new Seat( Character.toString((char) AsciiValue), seat) );
		 		}
	 		}
	 		
	 	}

	 	//split row AA into parts to build around handicap seats
	 	for(int seat = 101; seat < 104; seat++)
 		{
 			seatList.add( new Seat( "AA", seat) );
 		}
	 	for(int seat = 111; seat < 118; seat++)
 		{
 			seatList.add( new Seat( "AA", seat) );
 		}
	 	for(int seat = 125; seat < 128; seat++)
 		{
 			seatList.add( new Seat( "AA", seat) );
 		}
	 	
	}
	
	private void buildSeatMap()
	{
		for(Seat s: seatList)
		{
			seatMap.put(s.toKey(), s);
		}
	 	
	}
	
	private void buildSeatQueue()
	{
		for(Seat s : seatList)
		{
			seatQueue.put(s);
		}
	}
	

}
