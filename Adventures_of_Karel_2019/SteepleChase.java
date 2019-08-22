//
// Eric Bollar

import stanford.karel.*;

public class SteepleChase extends Karel
{
    int totalDist = 9; // fixed
    
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void jumpHurdle() {
        ascendHurdle();
        move(); // cross hurdle
        descendHurdle();
    }
    
    public void ascendHurdle() {
        turnLeft();
        while (rightIsBlocked()) {
            move();
        }
        turnRight(); // facing east
    }
    
    public void descendHurdle() {
        turnRight();
        while(frontIsClear()) {
            move();
        }
        turnLeft(); // facing east
    }
    
    public void run()
    {
       for (int x = 1; x < totalDist; x++) {
           if (frontIsBlocked()) {
               jumpHurdle();
           } else {
               move();
           }
       }
    }

}
