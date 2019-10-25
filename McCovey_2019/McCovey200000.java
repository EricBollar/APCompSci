
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;

public class McCovey200000 extends GraphicsProgram
{
    private McCoveyPlayer p1, p2;
    private McCoveyGame game, game2;
    private GRect bar1, bar2;
    private GLabel text;
    
    public void run()
    {
        
        // all you should need to do is change the next two lines
        p1 = new myPlayer();
        p2 = new PrettyGoodPlayer();
        
        bar1 = new GRect(0,100,0,100);
        bar1.setColor(Color.RED);
        bar1.setFilled(true);
        add(bar1);
        bar2 = new GRect(0,200,0,100);
        bar2.setColor(Color.ORANGE);
        bar2.setFilled(true);
        add(bar2);
        text = new GLabel("", 40,40);
        text.setFont("Serif-Bold-36");
        add(text);
         
        game = new McCoveyGame("One", "Two", 100000);        
                
        while (game.getGamesLeft()>0)
        {
           while (game.whoseTurn())
           {
                if (p1.rollAgain(game.getPlayer1Score(), game.getPlayer2Score(), game.getTurnScore()))
                    game.roll(null);
                else
                    game.pass();
           }
           if (game.getWinner().equals("One"))
           {
                 game.reset();
                 updateBars();
           }
           while (!game.whoseTurn())
           {
                if (p2.rollAgain(game.getPlayer2Score(), game.getPlayer1Score(), game.getTurnScore()))
                    game.roll(null);
                else
                    game.pass();
           }
           if (game.getWinner().equals("Two"))
           {
                 game.reset();
                 updateBars();
           }
           
        }
        game2 = new McCoveyGame("One", "Two", 100000);         
        
        waitForClick();
        
        while (game2.getGamesLeft()>0)
        {
           while (game2.whoseTurn())
           {
                if (p2.rollAgain(game2.getPlayer1Score(), game2.getPlayer2Score(), game2.getTurnScore()))
                    game2.roll(null);
                else
                    game2.pass();
           }
           if (game2.getWinner().equals("One"))
           {
                 game2.reset();
                 updateBars2();
           }
           while (!game2.whoseTurn())
           {
                if (p1.rollAgain(game2.getPlayer2Score(), game2.getPlayer1Score(), game2.getTurnScore()))
                    game2.roll(null);
                else
                    game2.pass();
           }
           if (game2.getWinner().equals("Two"))
           {
                 game2.reset();
                 updateBars2();
           }
           
        }        
        
    }
        
    public void init() {        setSize(650,350);    }
    
    private void updateBars()
    {
        int num1 = game.getGames1Won();
        int num2 = game.getGames2Won();
        double percent1 = (1.0*num1)/(num1+num2);
        double percent2 = (1.0*num2)/(num1+num2);
        bar1.setSize(percent1*600, 100);
        bar2.setSize(percent2*600, 100);
        text.setLabel("One = " + num1 + " wins, Two =  " + num2 + " wins");     
    }
 
    private void updateBars2()
    {
        int num1 = game2.getGames2Won() + game.getGames1Won();
        int num2 = game2.getGames1Won() + game.getGames2Won();
        double percent1 = (1.0*num1)/(num1+num2);
        double percent2 = (1.0*num2)/(num1+num2);
        bar1.setSize(percent1*600, 100);
        bar2.setSize(percent2*600, 100);
        text.setLabel("One = " + num1 + " wins, Two =  " + num2 + " wins");     
    }
    
}
