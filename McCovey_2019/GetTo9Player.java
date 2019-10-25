

public class GetTo9Player implements McCoveyPlayer
{
 
    public boolean rollAgain(int myScore, int yourScore, int turnScore)
    {
        if (myScore + turnScore >= 44)
            return false;
        return (turnScore < 9);
    }
}
