import java.awt.Color;

public class Bullet extends GVectorPolygon
{
    private int numTurns;
    
    private static final int MAXTURNS=150;
    
    public Bullet(int width, int height)
    {
       super(width, height);
       addVertex(0, 0);
       addVertex(2, 0);
       addVertex(2, 2);
       addVertex(0, 2);
       setColor(Color.white);
    }
    
    // complete this in version 0.5
    public boolean moving() {
        if (numTurns < MAXTURNS) {
            return true;
        }
        return false;
    }
    
    public void updatePosition() {
        numTurns++;
        super.updatePosition();
    }

}
