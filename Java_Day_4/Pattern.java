
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Pattern extends GraphicsProgram
{
    private static final double STARTX = 60;
    private static final double STARTY = 40;
    private static final int NUMSTRIPES = 15;
    private static final double WIDTH = 380;
    private static final double STRIPEHEIGHT = 20;

    public void run()
    {
        Color stripeColor = Color.pink;
        for (int i=0; i<NUMSTRIPES; i++)
        {
            GRect stripe = new GRect(STARTX, STARTY + STRIPEHEIGHT*i,
                                     WIDTH, STRIPEHEIGHT);
            stripe.setFilled(true);
            stripe.setColor(stripeColor);
            add(stripe);
            if (stripeColor == Color.pink)
            {
                stripeColor = Color.red;
            }
            else
            {
                stripeColor = Color.pink;
            }
        }
    }

}
