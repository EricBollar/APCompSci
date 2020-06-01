import info.gridworld.actor.Critter;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.grid.Location;

public class Fish extends Critter
{
    // constants (note that this are "protected", so they are
    // available in subclasses of Fish)
    protected final static int BREEDAGE = 5;
    protected final static int OLDAGE = 15;
    protected final static double PROBOFDYING = 0.20;

    // private instance variales
    private int age;
        
    public Fish()
    {
       age = 0;
    }
    
    public void checkAge() {
        if (age >= OLDAGE) {
            if (Math.random() < PROBOFDYING) {
                removeSelfFromGrid();
            }
        }
    }
    
    public int getAge() {
        return age;
    }
    
    public void act() {
        checkAge();
        age++;
        super.act();
    }
    
    public void processActors(ArrayList<Actor> actors) {
        // do nothing
    }
    
}
