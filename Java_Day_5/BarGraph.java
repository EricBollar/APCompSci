
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class BarGraph extends GraphicsProgram
{
    private static final int NUMBARS=12;
    private static final int BARWIDTH=20;
    private static final int TALLESTBARHEIGHT=256;
    private static final int DECREASEBARAMOUNT=10;
    private static final int BOTTOMLEFT_X = 45;
    private static final int BOTTOMLEFT_Y = 310;

    public void run()
    {
       for (int i=0; i<NUMBARS; i++)
       {
           GRect bar = new GRect(BOTTOMLEFT_X + BARWIDTH*i, 
                    BOTTOMLEFT_Y - TALLESTBARHEIGHT + (DECREASEBARAMOUNT*i), 
                                BARWIDTH, 
                                TALLESTBARHEIGHT - DECREASEBARAMOUNT*i);
           bar.setColor(Color.blue);
           add(bar);
        }
    }
    }