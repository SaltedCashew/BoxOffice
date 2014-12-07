package assignment6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Theater
{
	private String theaterName;
	//private static TheaterData seatingChart;
	private PriorityBlockingQueue<Seat> seatQueue;
	private ArrayList<Seat> seatList;

	public Theater()
	{
		seatList = new ArrayList<Seat>();
		seatList = buildSeats();
		for(Seat s : seatList)
		{
			System.out.println(s.toString());
		}
		//Comparator<Seat> seatPriority = (Comparator<Seat>) new SeatComparator();
		//seatQueue = new PriorityBlockingQueue<Seat>(seatList.size(), seatPriority);
		//seatingChart = new TheaterData();
	}
	
	private static ArrayList<Seat> buildSeats()
	{
		ArrayList<Seat> tempList = new ArrayList<Seat>();
		for(int seat = 101; seat < 126; seat++ ) //row A - start at row 1 for better indexing
		{
			tempList.add(new Seat("A", seat));
		}
	 	for(int row = 1; row < 24; row++) //rows B - AA
	 	{
	 		for(int seat = 101; seat < 129; seat++)
	 		{
	 			int AsciiValue =  row + 65;
	 			tempList.add( new Seat( Character.toString((char) AsciiValue), seat) );
	 		}
	 	}

	 	return tempList;
	}

}
