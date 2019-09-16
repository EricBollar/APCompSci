
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class AnotherVotingMachineRunner extends GraphicsProgram
{
    private GRect yesBox;
    private GRect noBox;
    private GOval rigCircle;
    private GLabel resultLabel;
    private VotingMachine ballot;
    

    public void run()
    {
        yesBox = new GRect(100, 100, 100, 100);
        yesBox.setFilled(true);
        yesBox.setColor(Color.green);
        add(yesBox);
        
        noBox = new GRect(100, 350, 100, 100);
        noBox.setFilled(true);
        noBox.setColor(Color.red);
        add(noBox);
        
        rigCircle = new GOval(400, 225, 20, 20);
        rigCircle.setFilled(true);
        rigCircle.setColor(Color.cyan);
        add(rigCircle);
        
        resultLabel = new GLabel("", 5, 40);
        resultLabel.setFont("times-plain-36");
        add(resultLabel);
        
        ballot = new VotingMachine();
        
        addMouseListeners();        
    }

    public void mouseClicked(MouseEvent event)
    {
        GObject thing = getElementAt(event.getX(), event.getY());
        if (thing == yesBox)
        {
            ballot.voteYes();
        }
        else
        {
            if (thing == noBox)
            {
                ballot.voteNo();
            }
            else
            {
                if (thing == rigCircle)
                {
                    ballot.rigElection(.1);
                }
            }
        }
        String result = "Yes: " + ballot.getYesPercent() + "% " + 
                        "No: " + ballot.getNoPercent() + "%";
        resultLabel.setLabel(result);
    }

}
