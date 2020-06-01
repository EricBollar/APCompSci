
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class TestProgram extends GraphicsProgram
{

    public void run()
    {
        GPolygon triangle = new GPolygon(200, 300);
        triangle.addVertex(25, 0);
        triangle.addVertex(-10, -20);
        triangle.addVertex(-30, 30);
        triangle.recenter();
        triangle.rotate(75);
        triangle.setFilled(true);
        triangle.setColor(Color.green);
        triangle.scale(1.5, 1.5);
        add(triangle);
        
        GVectorPolygon piece = new GVectorPolygon(getWidth(), getHeight());
        piece.addVertex(0, 0);
        piece.addVertex(100, 0);
        piece.addVertex(100, -50);
        piece.addVertex(50, -50);
        piece.addVertex(50, -100);
        piece.addVertex(0, -100);
        piece.recenter();
        piece.setLocation(237, 95);
        add(piece);
        
        piece.rotate(36);
        piece.increaseVelocity(1.5);
        
        while (true) {
            pause(10);
            piece.updatePosition();
            piece.rotate(1);
        }
    }


}
