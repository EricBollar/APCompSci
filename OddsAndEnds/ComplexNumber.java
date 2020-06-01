

public class ComplexNumber
{
    
    private double realPart;
    private double imagPart;
    
    public ComplexNumber(double realPart, double imagPart)
    {
        this.realPart = realPart;
        this.imagPart = imagPart;
    }
    
    public ComplexNumber add(ComplexNumber other) {
        double realResult = this.realPart + other.realPart;
        double imagResult = this.imagPart + other.imagPart;
        ComplexNumber result = new ComplexNumber(realResult, imagResult);
        return result;
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double realResult = this.realPart * other.realPart - this.imagPart * other.imagPart;
        double imagResult = this.imagPart * other.realPart + this.realPart * other.imagPart;
        return new ComplexNumber(realResult, imagResult);
    }
    
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.realPart, -this.imagPart);
    }
    
    public ComplexNumber squared() {
        return this.multiply(this);
    }
    
    public String toString() {
        return this.realPart + " + " + this.imagPart + "i";
    }

}
