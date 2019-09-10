
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*; 

// Eric Bollar
// AP CS
// September 3, 2019

public class Bullseye extends GraphicsProgram
{
    private static final int NUMRINGS = 10;
    private static final int RINGWIDTH = 20;
    private static final int INITIALX = 260;
    private static final int INITIALY = 210;

    public void run()
    {
        for (int i = 0; i < NUMRINGS; i++) {
            GOval circle = new GOval(INITIALX - (NUMRINGS - i) * RINGWIDTH, INITIALY - (NUMRINGS - i) * RINGWIDTH,
                                     RINGWIDTH * 2 * (NUMRINGS - i), RINGWIDTH * 2 * (NUMRINGS - i));
            circle.setFilled(true);
            if (i % 2 == 0) {
                circle.setColor(Color.red);
            } else {
                circle.setColor(Color.gray);
            }
            add(circle);
        }
        /* normal way
        for (int i = NUMRINGS; i > 0; i--) {
            GOval circle = new GOval(INITIALX - i * RINGWIDTH, INITIALY - i * RINGWIDTH,
                                     RINGWIDTH * 2 * i, RINGWIDTH * 2 * i);
            circle.setFilled(true);
            if (i % 2 == 0) {
                circle.setColor(Color.red);
            } else {
                circle.setColor(Color.gray);
            }
            add(circle);
        }*/
    }

}

