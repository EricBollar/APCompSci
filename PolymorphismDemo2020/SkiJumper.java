 
public class SkiJumper extends Athlete implements Flier
{
    private int numJumps;
    
    public SkiJumper(String first, String last)
    {
        super(first, last, "Ski Jumping");
        numJumps = 0;
    }
    
    public void fly()
    {
        System.out.println("Using skis to take " + getName() + " into the air.  Whee!");
        numJumps++;
    }
    
    public void train(double hours)
    {
     System.out.println("I am on the slopes " + hours + " hours today.");
     super.train(hours);
    }
    
    public int getJumps() {return numJumps;}
   
}
