import acm.program.*;
import acm.graphics.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class McCoveyGUI extends Program
{
    //private JLabel score1Label, score2Label, turnLabel, games1Label, games2Label;
    private JTextField player1Field, player2Field, numGamesField;
    private JTextArea textArea;
    private JComboBox player1Menu, player2Menu;
    private JButton rollButton, passButton, playButton;
    private JSlider player1Slider, player2Slider;
    private JLabel die1, die2;
    private McCoveyGame game;
    private Set<String> playerClassNames;
    private McCoveyPlayer player1, player2; // null is human
    private boolean needToUpdateTextDisplay;

    public void init()
    { 
        playerClassNames = new TreeSet<String>();
        addPlayerClass("RandomPlayer");
        addPlayerClass("GetTo9Player");
        setSize(900,300);
        setLayout(new GridLayout(2,1));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        JPanel player1 = new JPanel();
        player1.add(new JLabel("Player 1 name:"));
        player1Field = new JTextField(16);
        player1Field.setText("Buster");
        player1.add(player1Field);
        player1Menu = new JComboBox();
        player1Menu.addItem("Human");
        for (String name: playerClassNames)
            player1Menu.addItem(name);
        player1.add(player1Menu);
        player1.add(new JLabel("Pause Length: "));
        player1Slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        player1Slider.setEnabled(true);
        player1.add(player1Slider);
        top.add(player1);
        JPanel player2 = new JPanel();
        player2.add(new JLabel("Player 2 name:"));
        player2Field = new JTextField(16);
        player2Field.setText("Posey");
        player2.add(player2Field);
        player2Menu = new JComboBox();
        player2Menu.addItem("Human");
        for (String name: playerClassNames)
            player2Menu.addItem(name);
        player2.add(player2Menu);
        player2.add(new JLabel("Pause length: "));
        player2Slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        player2Slider.setEnabled(true);
        player2.add(player2Slider);
        top.add(player2);
        JPanel game = new JPanel();
        game.add(new JLabel("Number of games: "));
        numGamesField = new JTextField(4);
        numGamesField.setText("1");
        game.add(numGamesField);
        playButton = new JButton("Play McCovey");
        game.add(playButton);
        top.add(game);
        add(top);

        JPanel bottom = new JPanel();

        textArea = new JTextArea(8,36);
        JScrollPane scroll = new JScrollPane(textArea);
        textArea.setEditable(false);
        bottom.add(scroll);

        die1 = new JLabel(new ImageIcon("die5.jpg"));
        bottom.add(die1);
        die2 = new JLabel(new ImageIcon("die6.jpg"));
        bottom.add(die2);

        rollButton = new JButton("Roll");
        rollButton.setEnabled(false);
        bottom.add(rollButton);
        passButton = new JButton("Pass");
        passButton.setEnabled(false);
        bottom.add(passButton);            
        add(bottom);
    }

    public void run()
    {
        addActionListeners();
        needToUpdateTextDisplay = false;
        while ((game==null) || (game.getGamesLeft()>0))
        {            
            if (needToUpdateTextDisplay)
            {
                needToUpdateTextDisplay = false;
                textArea.append(game.getGameStatus()+ "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
                repaint(); // somehow this fixes an updating bug
            }
            if ((game!= null) && game.whoseTurn() && (player1!=null))
            {
                rollButton.setEnabled(false);
                passButton.setEnabled(false);
                if (player1.rollAgain(game.getPlayer1Score(), game.getPlayer2Score(), game.getTurnScore()))
                {game.roll(this);}
                else
                {game.pass(); 
                    textArea.append(player1Field.getText() + " passes.\n");
                }

                textArea.append(game.getGameStatus()+ "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
                try {Thread.sleep(player1Slider.getValue()*10);} catch (Exception ex) {}
            }
            else if ((game!=null)&& !game.whoseTurn() && (player2!=null))
            {
                rollButton.setEnabled(false);
                passButton.setEnabled(false);
                if (player2.rollAgain(game.getPlayer2Score(), game.getPlayer1Score(), game.getTurnScore()))
                {game.roll(this);}
                else
                {game.pass(); 
                    textArea.append(player2Field.getText() + " passes.\n");
                }
                textArea.append(game.getGameStatus()+ "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
                try {Thread.sleep(player2Slider.getValue()*10);} catch (Exception ex) {}
            }
            else if (game != null)
            {
                rollButton.setEnabled(true);
                passButton.setEnabled(true);
            }
            if ((game!= null) && (!game.getWinner().equals("")))
            {
                textArea.append(game.getWinner() + " won!\n");
                game.reset();
                textArea.append(game.getWinStatus() + "\n");                
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
            pause(5);
            //try {Thread.sleep(1);} catch (Exception ex) {}            
        }
        textArea.append(game.getWinStatus() + "\n");                
        rollButton.setEnabled(false);
        passButton.setEnabled(false);
    }

    private void addPlayerClass(String name)
    {
        playerClassNames.add(name);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == playButton)
        {
            playButton.setEnabled(false); 
            beginGame();
        }
        else if (e.getSource() == rollButton)
        {
            game.roll(this);
        }
        else if (e.getSource() == passButton)
        {
            game.pass();    
        }
        else if (e.getSource() == player1Menu)
        {
            player1Slider.setEnabled(player1Menu.getSelectedItem().equals("Human"));
        }
        else if (e.getSource() == player2Menu)
        {
            player2Slider.setEnabled(player2Menu.getSelectedItem().equals("Human"));
        }
        if (game != null)
        {
            needToUpdateTextDisplay = true;
        }

    }

    private void beginGame()
    {
        updateGUIforGame();
        if (player1Menu.getSelectedItem().equals("Human"))
            player1 = null;
        else
        {
            try {player1 = (McCoveyPlayer)(Class.forName((String)(player1Menu.getSelectedItem())).newInstance());}
            catch (ClassNotFoundException ex) {} 
            catch (InstantiationException ex) {} 
            catch (IllegalAccessException ex) {} 
        }   
        if (player2Menu.getSelectedItem().equals("Human"))
            player2 = null;
        else
        {
            try {player2 = (McCoveyPlayer)(Class.forName((String)(player2Menu.getSelectedItem())).newInstance());}
            catch (ClassNotFoundException ex) {} 
            catch (InstantiationException ex) {} 
            catch (IllegalAccessException ex) {} 
        }   
        game = new McCoveyGame(player1Field.getText(), player2Field.getText(), Integer.parseInt(numGamesField.getText()));

    }

    private void updateGUIforGame()
    {
        passButton.setEnabled(true);
        rollButton.setEnabled(true);

        player1Field.setEnabled(false);        
        player2Field.setEnabled(false);        
        numGamesField.setEnabled(false);        
        player1Slider.setEnabled(false);        
        player2Slider.setEnabled(false);        
        player1Menu.setEnabled(false);        
        player2Menu.setEnabled(false);        
    }

    public void setDiceIcon(int num1, int num2)
    {
        die1.setIcon(new ImageIcon("die" + num1 + ".jpg"));
        die2.setIcon(new ImageIcon("die" + num2 + ".jpg"));
    }
}
