import java.awt.Color;

public class Ship extends GVectorPolygon
{

    public Ship(int w, int h)
    {
        super(w, h);
        addVertex(0, 0);
        addVertex(10, 5);
        addVertex(0, 10);
        addVertex(2, 5);
        setColor(Color.white); 
    }

    // you'll add a method here in version 0.5.1
    public Bullet makeBullet() {
        Bullet b = new Bullet((int)maxX, (int)maxY);
        b.setLocation(getLocation());
        b.rotate(theta);
        b.increaseVelocity(3);
        return b;
    }
}
