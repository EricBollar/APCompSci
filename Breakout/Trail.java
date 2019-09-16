
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Trail extends GraphicsProgram
{

    double x;
    double y;
    int radius;
    int lifetime; // milliseconds
    
    public void run()
    {
        GOval g = new GOval(x, y, 50, 50);
        g.setColor(Color.blue);
        add(g);
    }
    
    public Trail(double x, double y, int radius, int milliseconds) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.lifetime = milliseconds;
    }
}
