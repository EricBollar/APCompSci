
// Eric Bollar
// AP CS, Period A
// October 27, 2019

public class myPlayer implements McCoveyPlayer
{
    private int targetNumRolls;
    private int currentTurnRolls;

    // expected value per roll: 35/18
    // p[x=k]=1-(1-(1/6))^x where k is # of dice throws
    // p is chance of failure 

    // https://en.wikipedia.org/wiki/Geometric_distribution
    // # of Wins against TestPlayer & PrettyGood (rolls 7 times every turn)
    // for each target val
    // rolls | test | PrettyGoodPlayer
    //   4    ~483k    ~455k
    //   5    ~500k    ~471k
    //   6    ~504k    ~479k  Data shows that 6 rolls yields best results (it's 7 really)
    //   7    ~488k    ~467k
    //   8    ~471k    ~455k

    public myPlayer() {
        targetNumRolls = 6; // see data
        currentTurnRolls = 0;
    }

    public boolean rollAgain(int myScore, int theirScore, int turnScore)
    {
        updateTurnRolls(myScore, turnScore);
        
        // basecase: winner
        if (myScore + turnScore >= 44) {
            return false;
        }
        
        // go to end
        int reachValue = 41;
        if (myScore > reachValue) {
            return true;
        }
        
        // they are close to 44
        int loseRiskValue = 15; // tested from 1-30 and 15 yielded best results
        if (theirScore + loseRiskValue >= 44) {
            return true;
        }
        
        // if at zero
        int firstRollPointsTarget = 14;
        if (myScore == 0) {
            if (turnScore < firstRollPointsTarget) {
                return true;
            }
            return false;
        }
        
        // if ahead roll 1 less as safety
        int minimumLeadValue = 4;
        if (theirScore + minimumLeadValue < myScore) {
            if (currentTurnRolls == targetNumRolls - 1) {
                return false;
            }
            return true;
        }
        
        // base rolls per turn
        return !(currentTurnRolls == targetNumRolls);
    }

    public void updateTurnRolls(int myScore, int turnScore) {
        if (turnScore == 0) {
            currentTurnRolls = 0;
        } else {
            currentTurnRolls++;
        }
    }
}
