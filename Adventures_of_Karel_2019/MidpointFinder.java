import stanford.karel.*;

// Eric Bollar
// AP CS Period A
// August 26, 2019

public class MidpointFinder extends Karel
{

    public void run()
    {
        fillRowWithBeepers();
        shortenRowFromSides();
        flip(); // adjusts
        move();
        flip();
    }
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void fillRowWithBeepers() {
        move();
        while (frontIsClear()) {
            putBeeper();
            move();
        }
        flip();
        move();
    }
    
    public void flip() {
        turnLeft();
        turnLeft();
    }
    
    public void shortenRowFromSides() {
        while (beepersPresent()) {
            removeEdgeBeeper();
        }
    }
    
    public void removeEdgeBeeper() {
        while (beepersPresent()) {
            move();
        }
        flip();
        move();
        pickBeeper();
        move();
    }

}
