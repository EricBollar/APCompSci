
public class myPlayer implements McCoveyPlayer
{
    
    public boolean rollAgain(int myScore, int yourScore, int turnScore)
    {
        // 16.7% chance of getting doubles (1/6)
        if (myScore + turnScore >= 44) {
            return false;
        }
        return true;
    }

}
