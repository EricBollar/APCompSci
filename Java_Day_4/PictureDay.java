
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class PictureDay extends GraphicsProgram
{

    public void run()
    {
        GImage student;
        student = new GImage("hardy.jpg", 50, 100);
        add(student);
        
        while (2 < 3)
        {
            student.move(1, 1);  
            pause(50);
        }
    }


}
