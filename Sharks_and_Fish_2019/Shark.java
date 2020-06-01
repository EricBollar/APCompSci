
import info.gridworld.actor.Critter;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.grid.Location;

// Eric Bollar
// AP CS
// 2 March 2020

public class Shark extends Critter
{
    // constants
    private static final int MUSTEAT = 8;

    // private instance variables
    private int turnsSinceEaten;

    public Shark()
    {
        setColor(Color.black);
        turnsSinceEaten = 0;
    }

    public void processActors(ArrayList<Actor> actors) {
        turnsSinceEaten = 0;
        int index = (int)(Math.random() * actors.size());
        Actor prey = actors.get(index);
        prey.removeSelfFromGrid();
    }

    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> result = new ArrayList<Location>();
        Location current = getLocation();
        int dir = getDirection();
        Grid<Actor> gr = getGrid();

        Location left = current.getAdjacentLocation(dir + Location.HALF_LEFT);
        if (gr.isValid(left) && gr.get(left) == null) {
            result.add(left);
        }
        Location right = current.getAdjacentLocation(dir + Location.HALF_RIGHT);
        if (gr.isValid(right) && gr.get(right) == null) {
            result.add(right);
        }
        Location front = current.getAdjacentLocation(dir + Location.AHEAD);
        if (gr.isValid(front) && gr.get(front) == null) {
            result.add(front);
        }

        return result;
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
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_RIGHT);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.RIGHT);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE - Location.HALF_RIGHT);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_CIRCLE - Location.HALF_LEFT);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.LEFT);
        if (a instanceof Fish) {
            result.add(a);
        }
        a = getActorAdj(Location.HALF_LEFT);
        if (a instanceof Fish) {
            result.add(a);
        }

        return result;
    }

    public void makeRandomTurn() {
        if (Math.random() < 0.5) {
            setDirection(getDirection() + Location.HALF_RIGHT);
        } else {
            setDirection(getDirection() + Location.HALF_LEFT);
        }
    }

    public void makeTurn(boolean left) {
        if (left) {
            setDirection(getDirection() + Location.HALF_LEFT);
        } else {
            setDirection(getDirection() + Location.HALF_RIGHT);
        }
    }

    public void act() {
        if (getGrid() == null) {
            return;
        }
        ArrayList<Actor> actors = getActors();
        if (actors.size() > 0) {
            processActors(actors);
        } else {
            if (turnsSinceEaten >= MUSTEAT) {
                removeSelfFromGrid();
            } else {
                turnsSinceEaten++;
                ArrayList<Location> moveLocs = getMoveLocations();
                if (moveLocs.size() > 0) {
                    Location loc = selectMoveLocation(moveLocs);
                    if (getLocation().getAdjacentLocation(getDirection() + Location.HALF_RIGHT).compareTo(loc) == 0) {
                        makeTurn(false);
                    } else if (getLocation().getAdjacentLocation(getDirection() + Location.HALF_LEFT).compareTo(loc) == 0) {
                        makeTurn(true);
                    }
                    makeMove(loc);
                } else {
                    makeRandomTurn();
                }
            }
        }
    }

}
