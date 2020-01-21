import java.awt.Color;

public abstract class Particle
{
    private int num;
    private Color color;
    public Particle(int particleType, Color particleColor)
    {
        num = particleType;
        color = particleColor;
    }
    public int getType() {return num;}
    public  Color getColor() {return color;}
}
