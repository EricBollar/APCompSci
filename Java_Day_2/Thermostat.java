
public class Thermostat
{

private int currentTemp;
private int maxTemp;
private int minTemp;

public Thermostat(int min, int max)
{
    maxTemp = max;
    minTemp = min;
    currentTemp = (min+max)/2;
}

public void warmer()
{
    if(currentTemp<maxTemp)
    {
        currentTemp++;
    }    
}

public void colder()
{
    if(currentTemp>minTemp)
    {
        currentTemp--;
    }            
}

public int getValue()
{
    return currentTemp;
}
}