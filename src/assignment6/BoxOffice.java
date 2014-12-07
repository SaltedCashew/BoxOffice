package assignment6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoxOffice implements Runnable
{
	private Deque<Person> line;
	private String name;
	private ArrayList<Person> servedPeople;
	private Theater show;
	private Lock showLock;
	
	public BoxOffice()
	{
		buildQueue();
	}
	
	public BoxOffice(String n, Theater t)
	{
		buildQueue();
		name = n;
		servedPeople = new ArrayList<Person>();
		show = t;
		showLock = new ReentrantLock();
	}
	

	private void buildQueue()
	{
		line = new ArrayDeque<Person>(500);
		for(int k = 0; k < 500; k++)
		{
			line.add(new Person());
		}
		
	}

	@Override
	public void run()
	{
		SellTickets();
		
	}
	
	private synchronized void SellTickets()
	{
	
			while(show.hasTickets())
			{
				Seat temp = show.sellSeat();
				temp.markSeatReserved();
				Person tempPerson = line.pop();
				tempPerson.setTicket(temp, name);
				servedPeople.add(tempPerson);
				System.out.println("Box office " + name + " just sold " + temp.toString());
			}	
	}

}
