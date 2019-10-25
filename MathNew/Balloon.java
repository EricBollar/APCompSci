
public class Balloon
{
    
    private static final double MAX_VOLUME = 8000000;
    
    private double radius;
    private boolean popped;
    
    public Balloon()
    {
        radius = 0;
        popped = false;
    }
    
    public void addAir(double ccAdded) {
        if (!popped) {
            double v = getVolume();
            v += ccAdded;
            if (v > MAX_VOLUME) {
                popped = true;
                radius = 0;
            } else {
                radius = Math.pow((3 * v)/(4 * Math.PI), 1.0/3);
            }
        }
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
    
    public double getVolume() {
        return 4.0/3 * Math.PI * Math.pow(radius, 3);
    }
    
    public boolean isPopped() {
        return popped;
    }
    
    
}