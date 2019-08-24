import stanford.karel.*;

// Eric Bollar
// AP CS Period A
// August 24, 2019

public class SpringTime extends Karel
{
    
    public void run()
    {
        for (int treesLeft = 5; treesLeft > 0; treesLeft--) {
            findStump();
            climbTree();
            makeTree();
            returnToGround();
        }
    }
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void findStump() {
        while (frontIsClear()) {
            move();
        }
    }
    
    public void climbTree() {
        while (frontIsBlocked()) {
            ascend();
        }
    }
    
    public void ascend() {
        turnLeft();
        move();
        turnRight();
    };
    
    public void descend() {
        turnRight();
        move();
        turnLeft();
    }
    
    public void makeTree() {
        putBeeper();
        ascend();
        putBeeper();
        move();
        putBeeper();
        descend();
        putBeeper();
    }
    
    public void returnToGround() {
        turnRight(); // facing down
        while (frontIsClear()) {
            move();
        }
        turnLeft();
    }

}
