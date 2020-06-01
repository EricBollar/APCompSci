

public class Wrestler extends Athlete
{
    private int weightClassMin;
    private int weightClassMax;
    
    public Wrestler(String first, String last, int minWeight, int maxWeight)
    {
        super(first, last, "wrestling");
        weightClassMin = minWeight;
        weightClassMax = maxWeight;
    }
    
    public void wrestle(int opponentWeight)
    {
        if (weightClassMin <= opponentWeight &&
                              opponentWeight <= weightClassMax)
        {
            System.out.println("Wrestler " + getName() + 
                               " is wrestling a match in his weight class.  Good luck!");
        }
        else
        {
            System.out.println("Sorry " + getName() + 
                               ", but this opponent is outside your weight class, " +
                               " so this will only count as an hour of training.");
            train(1);
        }
    }


}
