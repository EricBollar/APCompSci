
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class Main extends GraphicsProgram
{
    mesh meshCube;
    
    public void run()
    {
        createCube();
        setHeight(240);
        setWidth(256);
    }
    
    public void createCube() {
        triangle south1 = new triangle();
        south1.p[0] = (new vec3D(0.0f, 0.0f, 0.0f));
        south1.p[1] = (new vec3D(0.0f, 1.0f, 0.0f));
        south1.p[2] = (new vec3D(1.0f, 1.0f, 0.0f));
        meshCube.tris.add(south);
        
        triangle east = new triangle();
        east.p[0] = (new vec3D(0.0f, 0.0f, 0.0f));
        east.p[1] = (new vec3D(1.0f, 1.0f, 0.0f));
        east.p[2] = (new vec3D(1.0f, 0.0f, 0.0f));
        meshCube.tris.add(east);
        
        triangle north = new triangle();
        north.p[0] = (new vec3D(1.0f, 0.0f, 1.0f));
        north.p[1] = (new vec3D(1.0f, 1.0f, 1.0f));
        north.p[2] = (new vec3D(0.0f, 1.0f, 0.0f));
        meshCube.tris.add(south);
        
        triangle south = new triangle();
        south.p[0] = (new vec3D(0.0f, 0.0f, 0.0f));
        south.p[1] = (new vec3D(0.0f, 1.0f, 0.0f));
        south.p[2] = (new vec3D(1.0f, 1.0f, 0.0f));
        meshCube.tris.add(south);
        
        triangle south = new triangle();
        south.p[0] = (new vec3D(0.0f, 0.0f, 0.0f));
        south.p[1] = (new vec3D(0.0f, 1.0f, 0.0f));
        south.p[2] = (new vec3D(1.0f, 1.0f, 0.0f));
        meshCube.tris.add(south);
        
        triangle south = new triangle();
        south.p[0] = (new vec3D(0.0f, 0.0f, 0.0f));
        south.p[1] = (new vec3D(0.0f, 1.0f, 0.0f));
        south.p[2] = (new vec3D(1.0f, 1.0f, 0.0f));
        meshCube.tris.add(south);
    }


}
