package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Main seat class to build and use seat objects. 
 * Creates seats with section number, row string, seat number and availability (Reserved / Available).
 * @author Brad Gray, Zachary Subealdea
 * @version 1.1
 **/
public class Theater
{
	private ArrayList<Seat> seatList;
	private HashMap<String, Seat> seatMap;
	private TreeSet<Seat> tempTree;
	private SortedSet<Seat> safeSet;
	

	/**
	 * Builds a new theater representing Bates Recital Hall
	 * Generates a hashmap and treeset for organizing seats and priorities
	 */
	public Theater()
	{
		seatList = new ArrayList<Seat>();
		seatMap = new HashMap<String, Seat>(); 
		buildSeats(); //builds arraylist of seats - not sorted
		buildSeatMap(); //builds hashmap of seats for quick access
		Comparator<Seat> seatPriority = new SeatComparator();
		tempTree = new TreeSet<Seat>(seatPriority);
		buildTempTree(); //treeset built/sorted based off of comparator
		safeSet = Collections.synchronizedSortedSet(tempTree); //synchronized set with same sorting as the treeset
		
	}
	
	

	/**
	 * prints the list of every seat's string within the sorted set
	 */
	public void printAllSeats()
	{
		for(Seat s : safeSet){System.out.println(s.toString());}	
	}
	
	/**
	 * Obtains the best available seat based on priority
	 * @return highest priority seat or null if the set of available seats is empty
	 */
	public synchronized Seat bestAvailableSeat()
	{
		if (safeSet.size()!=0)
		{
			Seat temp = safeSet.first();
			safeSet.remove(temp);
			return temp;	
		}
		else return null;
		
	}
	
	/**
	 * Checks to see if there are still available seats remaining 
	 * @return true if tickets remain
	 */
	public boolean hasTickets()
	{
		return !safeSet.isEmpty();
	}
	
	/*
	 Builds a temporary tree from the seats
	 */
	private void buildTempTree()
	{
		for(Seat s: seatList)
		{
			tempTree.add(s);
		}
	}

	/*
	 *builds all seats for rows A through AA. Accounts for seats and rows that should be excluded
	 */
	private void buildSeats() //excludes handicap seats from Bates Hall seat map
	{
		final int ASCII_OFFSET = 65;
		final int ROW_I = 8;
		final int ROW_O = 14;

		for(int seat = 104; seat < 126; seat++ ) //row A 
		{
			seatList.add(new Seat("A", seat));
		}
	 	for(int row = 1; row < 26; row++) //rows B - Z
	 	{
	 		if(row!= ROW_I && row != ROW_O) //skip rows I & O
	 		{
	 			for(int seat = 101; seat < 129; seat++)
		 		{
		 			int AsciiValue =  row + ASCII_OFFSET;
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
	
	
	/*
	 * Builds hashmap for seats using their strings
	 */
	private void buildSeatMap()
	{
		for(Seat s: seatList)
		{
			seatMap.put(s.toKey(), s);
		}
	}

}
