package assignment6;

public class Seat
{
	private String row;
	private int rowVal;
	private int seatNum;
	private enum attribute
	{
		HANDICAP (1),
		RESERVED (2),
		AVAILABLE (3);
		
		private int value;

      private attribute(int value) 
      {
              this.value = value;
      }
	}
	private attribute availability;
	private int section;

	public Seat()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Seat(String r, int s)
	{
		row = r;
		//rowVal = Integer.parseInt(r);
		seatNum = s;
		availability = attribute.AVAILABLE;
	}
	
	public Seat(String r, int s, int sect)
	{
		row = r;
	//	rowVal = Integer.parseInt(r);
		seatNum = s;
		availability = attribute.AVAILABLE;
		section = sect;
		
	}
	
	public void markSeatHandicap()
	{
		availability = attribute.HANDICAP;
	}
	
	@Override
	public String toString()
	{
		return new String("Row " + row + ", Seat " + seatNum);
	}

}
