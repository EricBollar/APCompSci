
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// September 5, 2019

public class EventHW extends GraphicsProgram
{
    
    /*
     * This program moves a ball to the cursor's position when the mouse
     * is clicked. The ball then falls at an increasing rate until the mouse
     * is clicked again; then, the ball is moved to the mouse's position and
     * the rate at which it falls is reset.
     */
    
    GOval ball;
    int ballRadius = 20;
    int delayTime = 25; // this is the # of milliseconds of delay
    int gravityFactor = 1;
    
    public void run()
    {
        createBall();
        fall();
    }
    
    public void createBall() {
        ball = new GOval(-ballRadius*3, -ballRadius*3, ballRadius * 2, ballRadius * 2);
        ball.setFilled(true);
        ball.setColor(Color.blue);
        add(ball);
    }
    
    public void fall() {
        while (true) {
            ball.setLocation(ball.getX(), ball.getY() + gravityFactor);
            pause(delayTime);
            gravityFactor += 1;
        }
    }

    public void mousePressed(MouseEvent e) {
        ball.setLocation(e.getX() - ballRadius, e.getY() - ballRadius);
        gravityFactor = 1;
    }

}
