import java.awt.Color;

public class HeatedMetal extends Particle
{
    
    private int counter;
    
    public HeatedMetal()
    {
        super(ParticlesProgram.HEATEDMETAL, new Color(232, 226, 107));
        counter = 0;
    }
    
     public void incrementCounter() {
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }

}
