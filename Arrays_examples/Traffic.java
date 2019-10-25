
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Traffic extends GraphicsProgram
{
    private static final int NUMTYPES = 8;
    private static final int NUMLANES = 12;
    private static final int VEHICLEHEIGHT = 80;

    public void run()
    {
        setSize(1200, NUMLANES*VEHICLEHEIGHT);        
    }

}
