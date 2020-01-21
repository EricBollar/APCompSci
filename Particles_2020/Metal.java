import java.awt.Color;

public class Metal extends Particle
{

    private int counter;
    
    public Metal()
    {
        super(ParticlesProgram.METAL, Color.gray);
        counter = 0;
    }
    
    public void incrementCounter() {
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }


}
