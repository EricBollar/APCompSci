
public class RockClimber extends Athlete
{

    private int numClimbs;
    private int highestClimb;

    public RockClimber(String first, String last)
    {
        super(first, last, "Rock climbing");
        numClimbs = 0;
        highestClimb = 0;
    }
    
    public void climb(int height)
    {
        System.out.println("Rock climber " + getName() + 
                           " climbs to a height of " + height + 
                           "  Wow, that's tall!");
        numClimbs++;
        if (height > highestClimb)
            highestClimb = height;
    }
    
    public int getHighestClimb()
    {  return highestClimb; }

}
