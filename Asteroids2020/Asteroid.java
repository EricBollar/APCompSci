import acm.graphics.*;
import java.awt.Color;

public class Asteroid extends GVectorPolygon
{
    private double rotation;

    public Asteroid(int width, int height)
    {
       super(width, height);
       addVertex((int)(Math.random() * 40) - 20, (int)(Math.random() * 40) - 20);
       addVertex((int)(Math.random() * 40) + 20, (int)(Math.random() * 40) - 60);
       addVertex((int)(Math.random() * 40) + 60, (int)(Math.random() * 40) - 60);
       addVertex((int)(Math.random() * 40) + 100, (int)(Math.random() * 40) - 20);
       addVertex((int)(Math.random() * 40) + 100, (int)(Math.random() * 40) + 20);
       addVertex((int)(Math.random() * 40) + 60, (int)(Math.random() * 40) + 60);
       addVertex((int)(Math.random() * 40) + 20, (int)(Math.random() * 40) + 60);
       addVertex((int)(Math.random() * 40) - 20, (int)(Math.random() * 40) + 20);
       recenter();
       rotation = Math.random();
       setColor(Color.yellow);
    }

    public void updatePosition() {
        rotate(rotation);
        super.updatePosition();
    }
    // complete this in version 0.2
}
