package assignment6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoxOffice implements Runnable
{
	private Deque<Person> line;
	private String name;
	private ArrayList<Person> servedPeople;
	private Theater show;
	private Lock showLock;
	private Condition OfficeBusyCondition;
	
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
		OfficeBusyCondition = showLock.newCondition();
	}
	

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

	@Override
	public void run()
	{
		SellTickets();
		
	}
	
	private void SellTickets()
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

}
