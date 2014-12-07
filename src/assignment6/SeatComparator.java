package assignment6;

import java.util.Comparator;

public class SeatComparator implements Comparator<Seat>
{

	@Override
	public int compare(Seat a, Seat b)
	{
		int hashCodeA = a.getRow().hashCode();
		int hashCodeB = b.getRow().hashCode();
		if ((hashCodeA) > 100){hashCodeA = 95;}
		if ((hashCodeB) > 100){hashCodeB = 95;}
		int seatAValue = a.getSection() * 100000000 + a.getRow().hashCode() * 10000 + a.getSeatNumber();
		int seatBValue = b.getSection() * 100000000 + b.getRow().hashCode() * 10000 + b.getSeatNumber();
		
		/*
		System.out.println("Seat: Section " + a.getSection() + " Row " + a.getRow() + " Seat " + a.getSeatNumber());
		System.out.println("Seat Value : " + seatAValue);
		System.out.println("Seat: Section " + b.getSection() + " Row " + b.getRow() + " Seat " + b.getSeatNumber());
		System.out.println("Seat Value : " + seatBValue);
		*/
		if (seatAValue < seatBValue){return -1;}
		if (seatAValue > seatBValue){return 1;}
		
		return 0;
	}



}

