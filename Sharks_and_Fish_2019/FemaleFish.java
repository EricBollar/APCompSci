import java.awt.Color;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class FemaleFish extends Fish
{
    // constants
    private final static int GESTATIONPERIOD = 5;
    private final static int MAXBABIES = 4;

    // private instance variables
    private int numBabies;
    private int turnsPregnant;

    public FemaleFish()
    {
        super();
        setColor(Color.PINK);
        turnsPregnant = 0;
        numBabies = 0;
    }

    public void processActors(ArrayList<Actor> actors) {
        if (getGrid() == null) {
            return;
        }
        if (getColor() == Color.pink) { // not pregnant
            if (getAge() >= BREEDAGE) {
                ArrayList<Actor> males = getActors();
                if (males.size() > 0) {
                    for (int i = 0; i < males.size(); i++) {
                        Fish f = (MaleFish)males.get(i);
                        if (f.getAge() >= BREEDAGE) {
                            becomePregnant();
                        }
                    }
                }
            }
        } else if (turnsPregnant >= GESTATIONPERIOD) {
            giveBirth();
        } else {
            setColor(Color.red);
            turnsPregnant++;
        }
    }

    public void giveBirth() {
        ArrayList<Location> freeSpaces = getFreeSpaces();
        int babiesLeft = numBabies;

        for (int i = 0; i < freeSpaces.size(); i++) {
            if (babiesLeft > 0) {
                if (Math.random() < 0.5) {
                    MaleFish f = new MaleFish();
                    f.putSelfInGrid(getGrid(), freeSpaces.get(i));
                } else {
                    FemaleFish f = new FemaleFish();
                    f.putSelfInGrid(getGrid(), freeSpaces.get(i));
                }
                babiesLeft--;
            }
        }

        if (babiesLeft == 0) {
            turnsPregnant = 0;
            numBabies = 0;
            setColor(Color.pink);
        } else {
            numBabies = babiesLeft;
        }
    }

    public ArrayList<Location> getFreeSpaces() {
        ArrayList<Location> result = new ArrayList<Location>();
        Location curr = getLocation();
        int dir = getDirection();
        Grid<Actor> gr = getGrid();

        Location loc = curr.getAdjacentLocation(dir + Location.AHEAD);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.HALF_RIGHT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.HALF_LEFT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.RIGHT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.LEFT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.HALF_CIRCLE);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.HALF_CIRCLE - Location.HALF_RIGHT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }
        loc = curr.getAdjacentLocation(dir + Location.HALF_CIRCLE - Location.HALF_LEFT);
        if (gr.isValid(loc) && gr.get(loc) == null) {
            result.add(loc);
        }

        return result;
    }

    public void becomePregnant() {
        turnsPregnant = 1;
        numBabies = (int)(Math.random() * MAXBABIES + 1);
        setColor(Color.red);
    }

    public Actor getActorAdj(int adj) {
        Location curr = getLocation();
        int dir = getDirection();
        Grid<Actor> gr = getGrid();

        Location loc = curr.getAdjacentLocation(dir + adj);
        if (gr.isValid(loc) && gr.get(loc) != null) {
            Actor a = gr.get(loc);
            return a;
        }
        return null;
    }

    public ArrayList<Actor> getActors() {
        ArrayList<Actor> result = new ArrayList<Actor>();

        Actor a = getActorAdj(Location.AHEAD);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_RIGHT);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.RIGHT);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE - Location.HALF_RIGHT);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE - Location.HALF_LEFT);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.LEFT);
        if (a instanceof MaleFish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_LEFT);
        if (a instanceof MaleFish) {
            result.add(a);
        }

        return result;
    }
}
