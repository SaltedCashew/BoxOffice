package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Theater
{
	private ArrayList<Seat> seatList;
	private HashMap<String, Seat> seatMap;
	private TreeSet<Seat> tempTree;
	private SortedSet<Seat> safeSet;
	

	public Theater()
	{
		seatList = new ArrayList<Seat>();
		seatMap = new HashMap<String, Seat>();
		buildSeats();
		buildSeatMap();
		Comparator<Seat> seatPriority = new SeatComparator();
		tempTree = new TreeSet<Seat>(seatPriority);
		buildTempTree();
		safeSet = Collections.synchronizedSortedSet(tempTree);
		
	}
	
	private void buildTempTree()
	{
		for(Seat s: seatList)
		{
			tempTree.add(s);
		}
	}
	
	private void buildSeats() //excludes handicap seats from Bates Hall seat map
	{
		for(int seat = 104; seat < 126; seat++ ) //row A - start at row 1 for better indexing
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
	 	for(int seat = 101; seat < 105; seat++)
 		{
 			seatList.add( new Seat( "AA", seat) );
 		}
	 	for(int seat = 111; seat < 119; seat++)
 		{
 			seatList.add( new Seat( "AA", seat) );
 		}
	 	for(int seat = 125; seat < 129; seat++)
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

	public void printAllSeats()
	{
		for(Seat s : safeSet){System.out.println(s.toString());}	
	}
	
	public synchronized Seat sellSeat()
	{
		Seat temp = safeSet.first();
		safeSet.remove(temp);
		return temp;
		
	}
	
	public boolean hasTickets()
	{
		return !safeSet.isEmpty();
	}

}
