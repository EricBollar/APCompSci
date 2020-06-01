
public class Bird implements Flier
{
    private String species;
    
    public Bird(String initialSpecies)
    {
        species = initialSpecies;
    }

    public void fly()
    {
        System.out.println("This " + species + " is using its wings to fly.  Whee!");
    }
    

}
