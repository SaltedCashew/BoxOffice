package assignment6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class BoxOffice implements Runnable
{
	private Deque<Person> line;
	private String name;
	private ArrayList<Person> servedPeople;
	private Theater show;
	
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
	}
	

	private void buildQueue()
	{
		Random r =  new Random();
		int lineSize = r.nextInt(1000);
		line = new ArrayDeque<Person>(lineSize);
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
	
	private void SellTickets()
	{
			Seat temp;
			while(show.hasTickets())
			{
				synchronized(this)
				{
					temp = show.sellSeat();
				}
				
				temp.markSeatReserved();
				Person tempPerson = line.pop();
				tempPerson.setTicket(temp, name);
				servedPeople.add(tempPerson);
			}	
	}

}
