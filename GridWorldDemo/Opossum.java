import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;
import java.util.ArrayList;

public class Opossum extends Critter
{
    private int turnsDead;
    
    public Opossum()
    {
        super();
        turnsDead = 0;
    }
    
    public void act() {
        int numFriends = 0;
        int numFoes = 0;
        ArrayList<Actor> neighbors = getActors();
        for (int i = 0; i < neighbors.size(); i++) {
            if (isFriend(neighbors.get(i))) {
                numFriends++;
            }
            if (isFoe(neighbors.get(i))) {
                numFoes++;
            }
        }
        if (numFriends > numFoes) {
            turnsDead = 0;
            super.act();
        }
    }

    public boolean isFriend(Actor actor) {
        return actor.getColor() == Color.red || actor instanceof Opossum;
    }
    
    public boolean isFoe(Actor actor) {
        return actor.getColor() == Color.green || actor instanceof Bug;
    }

}
