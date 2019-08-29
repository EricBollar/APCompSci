
public class Car
{
    private double mpg;
    private double odometer;
    private double tankSize;
    private double gallons;
    
    public Car(double theMPG, double theTankSize)
    {
        mpg = theMPG;   
        tankSize = theTankSize;
        odometer = 0;
        gallons = 0;
    }
    
    public void fillup(double numGallons)
    {
        if (gallons == tankSize)
        {
            System.out.println("No need to fill up your tank.  It is already full!");
            return;
        }
        gallons += numGallons;
        if (gallons > tankSize)
        {
            double tooMuch = gallons - tankSize;
            gallons = tankSize;
            System.out.println("You overfilled your tank by " + tooMuch + " gallons.  Oops!");
        }
    }
    
    public void drive(double miles)
    {
        double gals = miles/mpg;
        if (gals > gallons)
        {
            double m = gallons * mpg;
            gallons = 0;
            odometer += m;
            System.out.println("Oh no!  You ran out of gas after going " + m + " miles.");
        }
        else
        {
            odometer += miles;
            gallons -= miles/mpg;
        }
    }
    
    public int getGasGauge()
    {
        return (int)(gallons/(tankSize*1.0)*100);   
    }
    
    public double getOdometer()
    {
        return odometer;
    }
}
