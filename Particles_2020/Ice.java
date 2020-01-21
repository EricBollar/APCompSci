import java.awt.Color;

public class Ice extends Particle
{
    private int counter;
    
    public Ice()
    {
        super(ParticlesProgram.ICE, Color.CYAN);
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }
    
    public void incrementCounter() {
        counter++;
    }

}
