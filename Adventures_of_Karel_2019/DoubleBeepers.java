import stanford.karel.*;

// Eric Bollar

public class DoubleBeepers extends Karel
{

    public void run()
    {
        move();
        doublePileOfBeepers();
        moveBackward();
        moveBackward();
    }
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void moveBackward() {
        flip();
        move();
        flip();
    }
    
    public void flip() {
        turnLeft();
        turnLeft();
    }
    
    public void doublePileOfBeepers() {
        doublePileSizeNextDoor();
        moveNextDoorPileBack();
    }
    
    public void doublePileSizeNextDoor() {
        while (beepersPresent()) {
            pickBeeper();
            putTwoBeepersNextDoor();
        }
    }
    
    public void putTwoBeepersNextDoor() {
        move();
        for (int multiplier = 2; multiplier > 0; multiplier--) {
            putBeeper();
        }
        moveBackward();
    }
    
    public void moveNextDoorPileBack() {
        move();
        while (beepersPresent()) {
            pickBeeper();
            moveBackward();
            putBeeper();
            move();
        }
    }

}
