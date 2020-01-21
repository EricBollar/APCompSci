import java.awt.Color;

public class Steam extends Particle
{

    private int counter;
    
    public Steam()
    {
        super(ParticlesProgram.STEAM, new Color(164, 242, 242));
        counter = 0;
    }
    
    public void incrementCounter() {
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }


}
