package assignment6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Box Office Class as a runnable -  
 * Generates customer queues and sells tickets to theater passed in constructor.
 * @author Brad Gray, Zachary Subealdea
 * @version 1.1
 **/
public class BoxOffice implements Runnable
{
	private Deque<Person> line;
	private String name;
	private ArrayList<Person> servedPeople;
	private Theater show;
	private Lock showLock;
	private Condition OfficeBusyCondition;

	/**
	 * Empty Constructor - should not be used.  
	 * Results in empty box office - no customers, no theater, no name.
	 * Prints out message stating as much - sets object data to null
	 **/
	public BoxOffice()
	{
		System.out.println("Box office not given name or theater. Useless Box Office!");
		name = null;
		show = null;
		line = null;
	}
	
	/**
	 * Constructor that should be used - passed name of Box Office and Theater object.  
	 * Box office name helps when printing out tickets. Know which B.O. sells which ticket.
	 * Theater object links the box office to a theater and its seats.
	 * Also generates a queue of customers and sets up a list of customers served
	 * @param n Box Office name as a string
	 * @param t Theater object to link box office to a theater
	 **/
	public BoxOffice(String n, Theater t)
	{
		buildQueue();
		name = n;
		servedPeople = new ArrayList<Person>();
		show = t;
		showLock = new ReentrantLock();
		OfficeBusyCondition = showLock.newCondition();
	}

	
	/**
	 * Box office data will be null if empty constructor was used.
	 * If Box Office data null, does nothing and prints corresponding message 
	 * Otherwise, It will begin to sell all tickets until they have sold out.
	 * Print out each ticket to the console and a sold out message when it is done.  
	 **/
	@Override
	public void run()
	{
		if(show == null || name==null || line == null)
		{
			System.out.println("This Box office has nothing to do!");
			return;
		}
		else
		{ 
			boolean repeat=true;
			while(repeat)
			{
				if(show.hasTickets()==true)
				{
					sellTickets();
				}
				else{repeat=false;}
			}
			System.out.println("Sorry, there aren't any more tickets for " +name+" to sell");
		}
	}
	
	
	/*
  	Checks for the next best available seat. 
  	If a seat exists, it is reserved and assigned to the person next in line.
  	The Office keeps track of each person it sells tickets to as well. 
  	If the line becomes empty, more customers are added in this simulation 
	*/
	private void sellTickets()
	{
		Seat temp;
		try
		{
			showLock.lock();
			temp = show.bestAvailableSeat();
			if (temp!=null)
			{
				temp.markSeatReserved();
				if(line.isEmpty()) {buildQueue();}
				Person tempPerson = line.pop();
				tempPerson.setTicket(temp, name);
				servedPeople.add(tempPerson);
			}
		}
		finally{	showLock.unlock();	}
		
	}
	
	
	/*
  	Adds people to the line to simulate a constant flow of customers for the box offices.
  	A random number of people between 100 and 1000 are added
  	*/
	private void buildQueue()
	{
		Random r =  new Random();
		int lineSize = r.nextInt((1000 - 100) + 1) + 100; //randome number between 100-1000
		line = new ArrayDeque<Person>(lineSize);
		for(int k = 0; k < lineSize; k++)
		{
			line.add(new Person());
		}
		
	}
}

