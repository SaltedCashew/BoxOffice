package assignment6;

public class Seat
{
	private int row;
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
	
	public Seat(int r, int s)
	{
		row = r;
		seatNum = s;
		availability = attribute.AVAILABLE;
	}
	
	public Seat(int r, int s, int sect)
	{
		row = r;
		seatNum = s;
		availability = attribute.AVAILABLE;
		section = sect;
		
	}

}
