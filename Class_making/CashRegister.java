
public class CashRegister
{
    private double totalPurchased;
    private double totalPaid;

    public CashRegister()
    {
        totalPurchased = 0;
        totalPaid = 0;
    }

    public void recordPurchase(double price)
    {
        if (price < 0)
        {
            return;
        }
        totalPurchased = totalPurchased + price;
    }

    public void enterPayment(double amount)
    {
        if (amount < 0)
        {
            return;
        }
        totalPaid = totalPaid + amount;
    }

    public double giveChange()
    {
        double change = totalPaid - totalPurchased;
        if (change < 0)
        {
            return -999;
        }
        totalPaid = 0;
        totalPurchased = 0;
        return change;

    }
}
