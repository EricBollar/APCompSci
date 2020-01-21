import java.awt.Color;

public class Water extends Particle
{

    private int counter;
    
    public Water()
    {
        super(ParticlesProgram.WATER, Color.blue);
        counter = 0;
    }
    
    public void incrementCounter() {
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }


}
