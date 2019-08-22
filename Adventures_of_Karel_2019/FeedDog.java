import stanford.karel.*;

public class FeedDog extends Karel
{

    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    
    public void flip() {
        turnLeft();
        turnLeft();
    }
    
    public void run()
    {
        move();
        move();
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnLeft();
        move();
        move();
        turnRight();
        move();
        turnRight();
        move();
        putBeeper();
        flip();
        move();
        turnLeft();
        move();
        turnLeft();
        move();
        move();
        turnRight();
        move();
        move();
        move();
        move();
        turnLeft();
        move();
        turnLeft();
    }

}
