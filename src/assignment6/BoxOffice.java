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
 * @version 1.0
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
	 * If Box Office data null, does nothing - else runs to sell all tickets.
	 * Box office data will be null if empty constructor was used. 
	 * If null data, will print out message "This Box office has nothing to do!"
	 * Else runs and sells tickets to the linked theater until no more seats available.  
	 **/
	@Override
	public void run()
	{
		if(show == null || name==null || line == null)
		{
			System.out.println("This Box office has nothing to do!");
			return;
		}
		else{ sellTickets();}
		
	}
	
	//PUT COMMENTS HERE!
	private void sellTickets()
	{
		Seat temp;
		try{
			showLock.lock();
			while(show.hasTickets())
			{
				temp = show.bestAvailableSeat();
				temp.markSeatReserved();
				if(line.isEmpty()) {buildQueue();}
				Person tempPerson = line.pop();
				tempPerson.setTicket(temp, name);
				servedPeople.add(tempPerson);
			}
		}
		finally{
			showLock.unlock();
		}
	}
	
	//PUT COMMENTS HERE!
	private void buildQueue()
	{
		Random r =  new Random();
		int lineSize = r.nextInt(1000);
		line = new ArrayDeque<Person>(lineSize);
		for(int k = 0; k < lineSize; k++)
		{
			line.add(new Person());
		}
		
	}
}
