package assignment6;

public class Seat
{
	private String row;
	private int seatNum;
	private enum attribute
	{
		RESERVED (1),
		AVAILABLE (2);
		
		private int value;

      private attribute(int value) 
      {
              this.value = value;
      }
	}
	private attribute availability;
	private int section;
	
	public Seat(String r, int s)
	{
		row = r;
		seatNum = s;
		section = determineSection(r, s);
		availability = attribute.AVAILABLE;
	}
	
	@Override
	public String toString()
	{
		return new String("Row " + row + ", Seat " + seatNum);
	}
	
	public String toKey(){
		return new String (this.row + " " + this.seatNum);
	}
	
	public int getSection()
	{
		return this.section;
	}
	
	public String getRow()
	{
		return this.row;
	}
	
	public int getSeatNumber()
	{
		return seatNum;
	}
	
	private int determineSection(String row, int seat)
	{
		int section = 0;
		if(row.compareTo("A") > -1 && row.compareTo("N") < 0)
		{
			if(seat>107 && seat<122){section = 1;}
			else if(seatNum>100 && seat < 108){section = 2;}
			else{section = 3;}
		}
		else
		{
			if(seat>107 && seat<122){section = 4;}
			else if(seat>100 && seat < 108){section = 5;}
			else{section = 6;}
		}
		return section;
	}

}
