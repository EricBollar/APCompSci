

public class Product
{
    private String name;
    private double price;
    
    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    
    public void reducePrice(double amount) {
        price -= amount;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }


}
