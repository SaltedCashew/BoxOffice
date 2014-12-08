/*Student Name: Brad Gray, Zachary Subealdea
 *EID: bg22946, 
 *Lab Section: 16805
 *Assignment 6 - Show Tickets Problem
*/
package assignment6;

import java.util.Comparator;

/**
 * Comparator class for Seats: 
 * Designed for comparator use when using sorted data structures
 * @author Brad Gray, Zachary Subealdea
 * @version 1.0
 **/
public class SeatComparator implements Comparator<Seat>
{
	/**
	 * Compare method to compare two seat objects.
	 * Generates integer values for each seat based off of the seat's section, row, and seat number.
	 * (section * SECTION MULTIPLIER + row * ROW MULTIPLIER + seat number).
	 * Compares the resulting integer values. 
	 * Lower number = higher priority. Comparator assumes lower section values are of higher priority.
	 * Returns -1 if value from a is less than value from seat b, 1 if a is greater than seat b,
	 * or 0 if value from seat a equals value from seat b.
	 * @param a - First seat object for comparison
	 * @param b - Second seat object for comparison
	 * @return -1 if a less than b, 1 of a greater than b, 0 if a equal to b
	 **/
	@Override
	public int compare(Seat a, Seat b)
	{
		final int SECT_MULT = 100000000; //multiplier for the seat's section (10^8)
		final int ROW_MULT = 10000; //multiplier for the seat's row (10^4)
		int hashCodeA = a.getRow().hashCode();
		int hashCodeB = b.getRow().hashCode();
		
		//Row AA returns a hash code of over 2000. add special case to set it under 100 but over 90 (hash code of Z)
		if ((hashCodeA) > 100){hashCodeA = 95;}
		if ((hashCodeB) > 100){hashCodeB = 95;}
		
		//section is 1 digit. row, and seat number are 3 digits 
		//algorithms below generate 9 digit numbers: digits with 0 between
		//Section 1, Row A, Seat 114 = 100650114
		//Section 6, Row AA, Seat 128 = 600950128
		int seatAValue = a.getSection() * SECT_MULT + a.getRow().hashCode() * ROW_MULT + a.getSeatNumber();
		int seatBValue = b.getSection() * SECT_MULT + b.getRow().hashCode() * ROW_MULT + b.getSeatNumber();
		
		if (seatAValue < seatBValue){return -1;}
		if (seatAValue > seatBValue){return 1;}
		
		return 0;
	}



}

