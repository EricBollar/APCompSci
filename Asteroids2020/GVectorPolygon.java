import acm.graphics.*;

public class GVectorPolygon extends GPolygon
{
    private double vx, vy;
    protected double theta;
    protected double maxX, maxY; // dimensions of the "window" to enable "wrapping around"

    private static final double MAXVELOCITY = 3;

    public GVectorPolygon(int width, int height)
    {
       vx = 0;
       vy = 0;
       theta = 0;
       maxX = width;
       maxY = height;
    }

    public void increaseVelocity(double numPixels)
    {
       // to be written in version 0.1
       double thetaInRadians = theta * Math.PI / 180;
       vx += numPixels * Math.cos(thetaInRadians);
       vy += -numPixels * Math.sin(thetaInRadians);
       double v = Math.sqrt(vx*vx + vy*vy);
       if (v > MAXVELOCITY) {
           vx = vx * MAXVELOCITY / v;
           vy = vy * MAXVELOCITY / v;
       }
    }

    public void updatePosition()
    {
        // to be written in version 0.1
        move(vx, vy);
        if (getX() < 0) {
            setLocation(maxX, getY());
        } else if (getX() > maxX) {
            setLocation(0, getY());
        }
        if (getY() < 0) {
            setLocation(getX(), maxY);
        } else if (getY() > maxY) {
            setLocation(getX(), 0);
        }
    }

    public void rotate(double angle)
    {
        theta += angle;
        super.rotate(angle);
    }

    public double getTheta()
    {
        return theta;
    }

    public double getVelocityX()
    {
        return vx;
    }

    public double getVelocityY()
    {
        return vy;
    }
    
    public void stop() {
        vx = 0;
        vy = 0;
    }

    public GRectangle getBoundingRectangle()
    {
        return new GRectangle(0,0,maxX, maxY);
    }
}
