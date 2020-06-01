import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;
import java.util.ArrayList;

public class Crab extends Critter
{

    public Crab()
    {
        setColor(Color.orange);
    }

    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> result = new ArrayList<Location>();
        Location current = getLocation();
        int dir = getDirection();
        Grid<Actor> gr = getGrid();

        Location left = current.getAdjacentLocation(dir + Location.LEFT);
        if (gr.isValid(left) && gr.get(left) == null) {
            result.add(left);
        }
        Location right = current.getAdjacentLocation(dir + Location.RIGHT);
        if (gr.isValid(right) && gr.get(right) == null) {
            result.add(right);
        }

        return result;
    }

    public void makeMove(Location loc) {
        Location current = getLocation();
        if (current.equals(loc)) {
            if (Math.random() < 0.5) {
                setDirection(getDirection() + Location.LEFT);
            } else {
                setDirection(getDirection() + Location.RIGHT);
            }
        } else {
            super.makeMove(loc);
        }
    }

    public void processActors(ArrayList<Actor> actors) {
        if (actors.size() == 0) {
            return;
        }
        int index = (int)(Math.random() * actors.size());
        Actor prey = actors.get(index);
        prey.removeSelfFromGrid();
    }

    public ArrayList<Actor> getActors() {
        ArrayList<Actor> result = new ArrayList<Actor>();

        Location current = getLocation();
        int dir = getDirection();
        Grid<Actor> gr = getGrid();

        Location left = current.getAdjacentLocation(dir + Location.HALF_LEFT);
        if (gr.isValid(left) && gr.get(left) != null) {
            Actor a = gr.get(left);
            if (!(a instanceof Crab || a instanceof Rock)) {
                result.add(a);
            }
        }
        
        Location right = current.getAdjacentLocation(dir + Location.HALF_RIGHT);
        if (gr.isValid(right) && gr.get(right) != null) {
            Actor a = gr.get(right);
            if (!(a instanceof Crab || a instanceof Rock)) {
                result.add(a);
            }
        }
        
        Location ahead = current.getAdjacentLocation(dir);
        if (gr.isValid(ahead) && gr.get(ahead) != null) {
            Actor a = gr.get(ahead);
            if (!(a instanceof Crab || a instanceof Rock)) {
                result.add(a);
            }
        }

        return result;
    }
}
