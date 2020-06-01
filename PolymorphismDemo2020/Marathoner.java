 
public class Marathoner extends Runner
{
    private int bibNumber;

    public Marathoner(String first, String last, int number)
    {
        super(first, last);
        bibNumber = number;
    }

    public int getBibNumber()
    { return bibNumber; }
    
    public void race(double raceLength)
    {
     if (raceLength >=10)
     {
      super.race(raceLength);   
     }
     else
     {
       System.out.println("Race too short for " +  getName() + "!  Counting it as a training run.");
       train(raceLength);
     }
    }    

}
