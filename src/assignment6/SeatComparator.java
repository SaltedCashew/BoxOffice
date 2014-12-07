package assignment6;

import java.util.Comparator;

public class SeatComparator implements Comparator<Seat>
{

	@Override
	public int compare(Seat a, Seat b)
	{
		int seatAValue = a.getSection() * 1000000 + a.getRow().hashCode() * 10000 + a.getSeatNumber();
		int seatBValue = b.getSection() * 1000000 + b.getRow().hashCode() * 10000 + b.getSeatNumber();
		
		if (seatAValue < seatBValue){return -1;}
		if (seatAValue > seatBValue){return 1;}
		
		return 0;
	}



}

