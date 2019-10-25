import javax.swing.*;

public class McCoveyGame 
{
    private String name1, name2;
    private boolean whoseTurn; // true = player #1, false = player #2
    private int score1, score2, turnTotal;
    private int wins1, wins2;
    private int gamesLeft;

    public McCoveyGame(String n1, String n2, int numGamesToPlay)
    {
        name1 = n1;
        name2 = n2;
        whoseTurn = true;
        score1 = 0; 
        score2 = 0;
        turnTotal = 0;
        gamesLeft = numGamesToPlay;
        wins1=0;
        wins2=0;
    }

    public String getGameStatus()
    {
        String temp = "";
        if (whoseTurn)
            temp = name1 + " is rolling. ";
        else
            temp = name2 + " is rolling. ";
        temp += "[" + name1 + " = " + score1 + ", " + name2 + " = " + score2 + ", Turn = " + turnTotal + "]";
        return temp;
    }

    public void roll(McCoveyGUI gui)
    {
        int num1 = (int)(Math.random() * 6) +1;
        int num2 = (int)(Math.random() * 6) +1;
        if (gui != null)
            gui.setDiceIcon(num1, num2);
        turnTotal += Math.abs(num1-num2);
        if (num1==num2)
        {
            whoseTurn = !whoseTurn;
            turnTotal = 0;
        }
    }

    public void pass()
    {
        // complete this

        if (whoseTurn)
            score1 += turnTotal;
        else
            score2 += turnTotal;
        turnTotal = 0;
        whoseTurn = !whoseTurn;

    }

    public boolean whoseTurn() {return whoseTurn;}

    public int getPlayer1Score() {return score1;}

    public int getPlayer2Score() {return score2;}

    public int getTurnScore() {return turnTotal;}

    public String getWinner()
    {
        if (score1>=44) return name1;
        if (score2>=44) return name2;
        return "";
    }

    public String getWinStatus() 
    {
        return "   {"+ name1 + " = " + wins1 + " wins, " + name2 + " = " + wins2 + " wins}" ;
    }

    public void reset()
    {
        if (score1>=44) wins1++;
        else if (score2>=44) wins2++;
        score1=0;
        score2=0;
        turnTotal=0;
        whoseTurn = true;
        gamesLeft--;
    }

    public int getGamesLeft()
    {
        return gamesLeft;
    }

    public int getGames1Won() {return wins1;}

    public int getGames2Won() {return wins2;}
}
