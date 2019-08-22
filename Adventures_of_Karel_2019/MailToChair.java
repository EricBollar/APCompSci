import stanford.karel.*;

// Eric Bollar
// APCS Homework
// August 20, 2019

public class MailToChair extends Karel
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
        turnLeft();
        move();
        turnRight();
        move();
        move();
        pickBeeper(); // on mailbox
        flip();
        move();
        move();
        move();
        putBeeper(); // on chair
        flip();
        move();
        turnRight();
        move();
        turnLeft();
    }

}
