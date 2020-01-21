import java.awt.Color;

public class Fire extends Particle
{

    private int counter;
    
    public Fire()
    {
        super(ParticlesProgram.FIRE, Color.red);
        counter = 0;
    }

    public void incrementCounter() {
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }

}
