import java.awt.Color;

public class Magnet extends Particle
{

    private boolean created;
    private int delay;
    
    public Magnet()
    {
        super(ParticlesProgram.MAGNET, Color.white);
        created = false;
        delay = 0;
    }
    
    public boolean getCreated() {
        return created;
    }
    
    public void setCreated() {
        created = true;
    }

}
