
// Eric Bollar
// AP CS Period A
// September 19, 2019

public class RoachPopulation
{
    private int numRoaches;

    public RoachPopulation(int startingNumRoaches)
    {
        numRoaches = startingNumRoaches;
    }
    
    public void breed() {
        numRoaches *= 2;
    }
    
    public void spray() {
        numRoaches /= 10;
    }
    
    public int getNumRoaches() {
       return numRoaches; 
    }


}
