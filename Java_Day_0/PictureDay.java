
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class PictureDay extends GraphicsProgram
{

    public void run()
    {
        GImage student = new GImage("gurley.jpg", 30, 110);
        add(student);
        
        while (true) {
            student.move(1,0);
            pause(20);
        }
    }


}
