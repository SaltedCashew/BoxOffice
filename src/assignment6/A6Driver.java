/*Student Name: Brad Gray, Zachary Subealdea
 *EID: bg22946, 
 *Lab Section: 16805
 *Assignment 6 - Show Tickets Problem
*/

package assignment6;

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
		

	}

}
