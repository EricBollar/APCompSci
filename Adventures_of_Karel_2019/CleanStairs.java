import stanford.karel.*;

// Eric Bollar
// AP CS
// August 21, 2019

public class CleanStairs extends Karel
{

    public void run()
    {
        climbStaircase();
    }

    public void climbStaircase() {
        while (frontIsBlocked()) {
            stepUp();
            pickBeeper();
        }
    }
    
    public void stepUp() {
        turnLeft();
        move();
        turnRight();
        move();
    }
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
