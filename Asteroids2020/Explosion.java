import java.awt.Color;

public class Explosion extends GVectorPolygon
{
    private int numTurns;
    private static final int MAXTURNS=30;
    
    public Explosion(int w, int h)
    {
        super(w, h);
        addVertex(0, 0);
        addVertex(10, 0);
        addVertex(5, 10);
        setColor(Color.red); 
    }
    
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