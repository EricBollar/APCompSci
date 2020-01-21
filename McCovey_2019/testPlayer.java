
// Eric Bollar
// AP CS, Period A
// October 26, 2019

public class testPlayer implements McCoveyPlayer
{
    private int currentTurnRolls;
    private int targetTurnRolls;
    // expected: 35/18
    // p[x=k]=(1/6)(1 - 1/6)^(k-1) where k is # of dice throws
    
    public testPlayer() {
        targetTurnRolls = 6;
        currentTurnRolls = 0;
    }
    
    public boolean rollAgain(int myScore, int theirScore, int turnScore)
    {
        if (turnScore == 0) {
            currentTurnRolls = 0;
        } else {
            currentTurnRolls++;
        }
        if (myScore + turnScore >= 44) {
            return false; // win
        }
        if (currentTurnRolls == targetTurnRolls) {
            return false;
        }
        return true;
    }
}
