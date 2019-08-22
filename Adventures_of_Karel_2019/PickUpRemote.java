import stanford.karel.*;

public class PickUpRemote extends Karel
{

    public void run()
    {
        move();
        move();
        pickBeeper();
        turnLeft();
        move();
        turnLeft();
        turnLeft();
        turnLeft();
        move();
        putBeeper();
        move();
    }
   
}
