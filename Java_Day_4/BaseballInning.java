
public class BaseballInning
{
    private String[] roster;
    private int outs;
    private int[] bases;
    private int runs;

    public BaseballInning(int initialBatterPosition)
    {
        roster = new String[]{"Span", "Pence", "Parker", "Posey", "Crawford", "Sandoval", "Panik", "Jones", "Bumgarner"};
        bases = new int[]{-1, -1, -1, -1};
        bases[0] = initialBatterPosition-1;
        runs = 0;
        outs = 0;
    }

    public int getRuns() { return runs; }

    public int getOuts() { return outs; }

    public void batterOut()
    {
        if (outs > 2) 
        {
            printOutErrorMessage();
            return;
        }
        outs++;
        nextBatter();
    }

    public void batterBaseHit(int numBases)
    {
        if (outs > 2) 
        {
            printOutErrorMessage();
            return;
        }
        if (numBases<1 || numBases>4)
        {
            printHitErrorMessage(numBases);
            return;
        }
        if (numBases==1) 
        {
            if (bases[3]!=-1) runs++;
            bases[3] = bases[2];
            bases[2] = bases[1];
            bases[1] = bases[0];
        }
        if (numBases==2) 
        {
            if (bases[3]!=-1) runs++;
            if (bases[2]!=-1) runs++;
            bases[3] = bases[1];
            bases[2] = bases[0];
            bases[1] = -1;
        }
        if (numBases==3) 
        {
            if (bases[3]!=-1) runs++;
            if (bases[2]!=-1) runs++;
            if (bases[1]!=-1) runs++;
            bases[3] = bases[0];
            bases[2] = -1;
            bases[1] = -1;
        }
        if (numBases==4) 
        {
            if (bases[3]!=-1) runs++;
            if (bases[2]!=-1) runs++;
            if (bases[1]!=-1) runs++;
            bases[3] = -1;
            bases[2] = -1;
            bases[1] = -1;
            runs++;
        }
        nextBatter();
    }

    public String getCurrentBatter() { return roster[bases[0]];}

    public String getBaseRunners() 
    {
        String result = "";
        if (bases[1] != -1) result += "First base: " + roster[bases[1]] + " ";
        if (bases[2] != -1) result += "Second base: " + roster[bases[2]] + " ";
        if (bases[3] != -1) result += "Third base: " + roster[bases[3]] + " ";
        if (result.length()==0)
            return "No one is on base.";
        else
            return result;
    }

    private void nextBatter()
    {
        bases[0]++;
        if (bases[0]==9)
            bases[0] = 0;
    }

    private void printHitErrorMessage(int numBases)
    {
        System.out.println("Error: There is no such thing as a " + numBases + "-base hit.");
        System.out.println("The number of bases must be between 1 and 4, inclusive.");
    }

    private void printOutErrorMessage() 
    {
        System.out.println("Error: There are already three outs in this inning.");
        System.out.println("The inning is over.");  
    }

}
