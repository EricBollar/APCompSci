

public class VotingMachine
{

    private int numYes;
    private int numNo;
    
    public VotingMachine()
    {
        numYes = 0;
        numNo = 0;
    }
    
    public void voteYes() {
        numYes++;
    }
    
    public void voteNo() {
        numNo++;
    }
    
    public void rigElection(double rigPercent) {
        int stolenVotes = (int)(numNo * rigPercent);
        numYes += stolenVotes;
        numNo -= stolenVotes;
    }

    public double getYesPercent() {
        return 100.0 * numYes/(numYes + numNo);
    }
    
    public double getNoPercent() {
        return 100.0 - getYesPercent();
    }
    
    public String getWinner() {
        if (numYes > numNo) {
            return "Yes";
        } else if (numNo < numYes) {
            return "No";
        }
        return "Tie";
    }
}
