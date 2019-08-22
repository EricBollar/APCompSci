
// Eric Bollar

import stanford.karel.*;

public class MountainClimber extends Karel
{
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void run() {
        moveToWall();
        scaleMountain();
        moveToWall();
    }
    
    public void scaleMountain() {
        ascendMountain();
        putBeeper(); // plant flag
        move();
        descendMountain();
    }
    
    public void moveToWall() {
        while (frontIsClear()) {
            move();
        }
    }
    
    public void ascendMountain() {
        while (frontIsBlocked()) {
            stepUp();
        }
    }
    
    public void stepUp() {
        turnLeft();
        move();
        turnRight();
        move();
    }
    
    public void descendMountain() {
        while (rightIsClear()) {
            stepDown();
        }
    }
    
    public void stepDown() {
        turnRight();
        move();
        turnLeft();
        move();
    }
}
