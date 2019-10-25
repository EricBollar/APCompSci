

public class Paycheck
{
    private double hoursWorked;
    private double hourlyRate;

    public Paycheck(double hours, double rate)
    {
        hoursWorked = hours;
        hourlyRate = rate;
    }

    public double getAmount()
    {
          double basePay = hoursWorked * hourlyRate;
          if (hoursWorked > 40) {
              basePay += 0.5 * hourlyRate * (hoursWorked - 40);
          }
          if (basePay < 200) {
              basePay *= 0.89;
          } else if (basePay < 400) {
              basePay *= 0.85;
          } else if (basePay < 600) {
              basePay *= 0.82;
          } else {
              basePay *= 0.76;
          }
          
          if (basePay < 14) {
              return 0;
          } else {
              return basePay - 14;
          }
    }

}
