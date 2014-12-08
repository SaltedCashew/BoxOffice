/*Student Name: Brad Gray, Zachary Subealdea
 *EID: bg22946,  zas329
 *Lab Section: 16805
 *Assignment 6 - Show Tickets Problem
*/

package assignment6;

/**
 * Main driver for Assignment 6 : Show Tickets Problem 
 * @author Brad Gray, Zachary Subealdea
 * @version 1.1
 **/
public class A6Driver
{

	public static void main(String[] args)
	{
		Theater mainShow = new Theater();
		Runnable boxOfficeA  = new BoxOffice("A", mainShow);
		Runnable boxOfficeB = new BoxOffice("B", mainShow);
		Thread boA = new Thread(boxOfficeA);
		Thread boB = new Thread(boxOfficeB);
		boA.start();
		boB.start();

		while(mainShow.hasTickets()){	}	//waits for all tickets to be sold

		System.out.println("All box offices have sold out; please come back next time. Thank You");
		
		return;
	}

}
