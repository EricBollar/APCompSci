
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
        
        GImage[] vehicles = new GImage[NUMLANES];
        double[] speeds = new double[NUMLANES];
        for (int i = 0; i < vehicles.length; i++) {
            int num = (int)(Math.random()*NUMTYPES);
            vehicles[i] = new GImage("vehicle" + num + ".jpg", (int)(Math.random() * 1200), VEHICLEHEIGHT*i);
            add(vehicles[i]);
        }
    }

}
