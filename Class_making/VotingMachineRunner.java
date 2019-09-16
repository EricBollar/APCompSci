
import acm.program.*;

public class VotingMachineRunner extends ConsoleProgram
{

    public void run()
    {
        VotingMachine machine = new VotingMachine();
        for (int i=0; i<600; i++)
        {
            machine.voteYes();
        }
        for (int i=0; i<800; i++)
        {
            machine.voteNo();
        }
        machine.rigElection(0.2);
        println("YES: " + machine.getYesPercent());
        println("NO: " + machine.getNoPercent());
        println(machine.getWinner());
    }


}
