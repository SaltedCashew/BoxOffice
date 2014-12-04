package assignment6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Theater
{
	private String theaterName;
	private PriorityBlockingQueue<Seat> seatQueue;
	private ArrayList<Seat> seatList;

	public Theater()
	{
		seatList = new ArrayList<Seat>();
		seatList = buildSeats();
		Comparator<Seat> seatPriority = (Comparator<Seat>) new SeatComparator();
		seatQueue = new PriorityBlockingQueue<Seat>(seatList.size(), seatPriority);
	}
	
	private static ArrayList<Seat> buildSeats()
	{
		ArrayList<Seat> tempList = new ArrayList<Seat>();
	 	for(int row = 0; row < 25; row++)
	 	{
	 		for(int seat = 101; seat < 128; seat++)
	 		{
	 			tempList.add( new Seat(row, seat) );
	 		}
	 	}
	 	return tempList;
	}

}
