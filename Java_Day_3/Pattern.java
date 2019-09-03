
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

// Eric Bollar
// AP CS
// September 3, 2019

public class Pattern extends GraphicsProgram
{
    private static final double STARTX = 60;
    private static final double STARTY = 40;
    private static final int NUMSTRIPES = 100;
    private static final double WIDTH = 380;
    private static final double STRIPEHEIGHT = 4;

    public void run()
    {
        for (int i = 0; i < NUMSTRIPES; i++) {
            GRect stripe = new GRect(STARTX, STARTY + i * STRIPEHEIGHT,
                                     WIDTH, STRIPEHEIGHT);
            stripe.setFilled(true);
            if (i % 2 == 0) {
                stripe.setColor(Color.pink);
            } else {
                stripe.setColor(Color.red);
            }
            add(stripe);
        }
    }

}
