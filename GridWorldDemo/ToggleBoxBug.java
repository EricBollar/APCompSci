import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;

public class ToggleBoxBug extends BoxBug
{
    private Color colorA;
    private Color colorB;

    public ToggleBoxBug(int sideLength, Color firstColor, Color secondColor)
    {
        super(sideLength);
        colorA = firstColor;
        colorB = secondColor;
        setColor(firstColor);
    }
    
    public void act() {
        if (getColor() == colorA) {
            setColor(colorB);
        } else {
            setColor(colorA);
        }
        super.act();
    }


}
