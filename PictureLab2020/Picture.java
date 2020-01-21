import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** ADD ALL NEW METHODS HERE */
 
    public void zeroBlue() {
        Pixel[] pixels = getPixels();
        
        for (int i = 0; i < pixels.length; i++) {
            pixels[i].setBlue(0);
        }
    }
    
    public void swapRedGreen() {
        Pixel[] pixels = getPixels();
        
        for (int i = 0; i < pixels.length; i++) {
            int g = pixels[i].getGreen();
            pixels[i].setGreen(pixels[i].getRed());
            pixels[i].setRed(g);
        }
    }
    
    public void negative() {
        Pixel[] pixels = getPixels();
        
        for (int i = 0; i < pixels.length; i++) {
            Pixel p = pixels[i];
            p.setRed(255-p.getRed());
            p.setGreen(255-p.getGreen());
            p.setBlue(255-p.getBlue());
        }
    }
    
    public void grayScale() {
        Pixel[] pixels = getPixels();
        
        for (Pixel p : pixels) {
            int sum = p.getRed() + p.getGreen() + p.getBlue();
            int avg = sum / 3;
            p.setRed(avg);
            p.setGreen(avg);
            p.setBlue(avg);
        }
    }
    
    public void solveGold() {
        Pixel[] pixels = getPixels();
        
        for (Pixel p : pixels) {
            p.setRed(p.getRed() * 10);
            p.setGreen(0);
            p.setBlue(0);
        }
    }
    
    public void upsideDown() {
        Pixel[][] pixels = getPixels2D();
        
        for (int r = 0; r < pixels.length/2; r++) {
            for (int c = 0; c < pixels[0].length; c++) {
                Color temp = pixels[r][c].getColor();
                pixels[r][c].setColor((pixels[pixels.length-r-1][c]).getColor());
                pixels[pixels.length-r-1][c].setColor(temp);
            }
        }
    }
    
    public void symmetric() {
        Pixel[][] pixels = getPixels2D();
        
        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[0].length/2; c++) {
                pixels[r][c].setColor((pixels[r][pixels[0].length-c-1]).getColor());
            }
        }
    }
    
    public void edgeDetection() {
        Pixel[][] pixels = getPixels2D();
        
        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[r].length-1; c++) {
                if (pixels[r][c].colorDistance(pixels[r][c+1].getColor()) > 50) {
                    pixels[r][c].setColor(Color.black);
                } else {
                    pixels[r][c].setColor(Color.white);
                }
            }
        }
    }

} // this } is the end of class Picture, put all new methods before this
