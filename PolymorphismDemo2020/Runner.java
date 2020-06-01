
public class Runner extends Athlete
{
	private int numRaces;
	private double milesRaced;
	 
	public Runner(String first, String last)
	{
	    super(first, last, "Running");
	    numRaces = 0;
	    milesRaced = 0;
	}
	
	public void race(double raceLength)
	{
	 System.out.println(getName() + " is racing in a " + raceLength + 
	                    " mile race.");
	 numRaces++;
	 milesRaced  += raceLength;
	}
	
	public int getRaces() { return numRaces;}
	public double getMilesRaced() { return milesRaced;}

}
