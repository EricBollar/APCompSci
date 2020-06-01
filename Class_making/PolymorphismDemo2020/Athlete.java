
public  class Athlete
{ 
    private String firstName;
    private String lastName;
    private String sport;
    private double hoursTrained;
    
    public Athlete(String first, String last, String sportPlayed)
    {
        firstName = firstName;
        lastName = lastName;
        sport = sportPlayed;
        hoursTrained = 0.0;
    }
    
    public void train(double hours)
    {
        System.out.println(getName() + " training for " + hours + " hours.");
        hoursTrained += hours;
    }
       
    public String getName() {return firstName + " " + lastName;}
    public String getSport() {return sport;}
    public double getHoursTraining() {return hoursTrained;}
    
}
