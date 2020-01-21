import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics;
import javax.swing.Timer;

/**
This frame contains a panel that displays a GameOfLife
and a panel of text fields to specify the rectangle position.
 */
public class GameOfLifeFrame extends JFrame implements ActionListener
{
    private GameOfLifePanel gamePanel;
    private Timer timer;
    private int myTimesToCount, myCount;
    /**
    Constructs the frame.
     */
    public GameOfLifeFrame()
    {
        // the panel that draws the game
        gamePanel = new GameOfLifePanel();

        // add panel to content Pane
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        createControlPanel();

        pack();

        timer = new Timer(5, this);
        timer.setInitialDelay(0);
        timer.setCoalesce(true);
    }

    /**
    Creates the control panel with the text fields
    at the bottom of the frame.
     */
    public void playTheGame(int numTurns)
    {
        for (int k=0; k < numTurns; k++)
        {
            gamePanel.playOneTurn();
        }
    }

    private void createControlPanel()
    {

        // the button to move the rectangle
        final JButton playButton = new JButton("Start the Game of Life");
        final JTextField turnField = new JTextField("1000",6);

        final JCheckBox foreverBox = new JCheckBox("Play forever");
        final JLabel turnLabel = new JLabel("Number of turns: ");     
        class ForeverListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                turnField.setEnabled(!foreverBox.isSelected());
                turnLabel.setEnabled(!foreverBox.isSelected());
            }
        }

        ActionListener foreverListener = new ForeverListener();
        foreverBox.addActionListener(foreverListener);

        class PlayButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                if (timer.isRunning())
                {
                    stopAnimation();
                    playButton.setText("Play the Game of Life");
                }
                else 
                {
                    playButton.setText("Stop the Game of Life");               
                    if (foreverBox.isSelected())
                    {myTimesToCount=-1;}
                    else
                    {myTimesToCount = (Integer.parseInt(turnField.getText()));}
                    myCount=0;
                    timer.start();
                }
            }
        }

        ActionListener listener = new PlayButtonListener();
        playButton.addActionListener(listener);

      
        // the panel for holding the user interface components
        JPanel controlPanel = new JPanel();

        controlPanel.add(turnLabel);
        controlPanel.add(turnField);
        controlPanel.add(foreverBox);
        controlPanel.add(playButton);

        getContentPane().add(controlPanel, BorderLayout.SOUTH);

    }

    public void startAnimation() {
        if (!timer.isRunning()) {
            myCount=0;
            timer.start();
        }
    }

    //Can be invoked by any thread (since timer is thread-safe).
    public void stopAnimation() {
        //Stop the animating thread.
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        //Advance the animation frame.
        gamePanel.playOneTurn();
        if (myTimesToCount >0)
        {
            myCount++;
            if (myCount==myTimesToCount) {stopAnimation();}
        }
    }

}


