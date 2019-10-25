
// Eric Bollar
// AP CS, Period A
// October 18, 2019

import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.lang.*;

public class HangmanGraphicsProgram extends GraphicsProgram
{
/** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 500;
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;
    
    private static final int PHRASE_Y_OFFSET = 100;
    private static final int GUESSED_LETTERS_X_OFFSET = 50;
    private static final int GUESSED_LETTERS_Y_OFFSET = 300;
     
    // task #0 - familiarize yourself with the private instance variables below
    private GLabel phraseLabel;
    private GLabel lettersGuessedLabel;
    private GImage img;
    private HangmanGame man;
    int numTotal;
    int numCorrect;

    public void run()
    {
        man = new HangmanGame();
        doInitialDrawingSetup(); // it's more important to see the labels than the image
        setupLabels();           // so I put the image on before the labels
        numCorrect = 0;
        numTotal = 0;
        
        while (true) {
            pause(10);
            if (man.getGuessesLeft() == 0 || man.getPuzzle().indexOf("-") == -1) {
                handleGameOver();
                waitForClick();
                resetGame();
            }
        }
    }
    
    /** Resets the Game's Visuals with a new HangmanGame Instance */
    public void resetGame() {
        man = new HangmanGame();
        removeAll();
        doInitialDrawingSetup();
        setupLabels();
    }
    
    /** Creates the Labels */
    public void setupLabels() {
        phraseLabel = new GLabel(man.getPuzzle());
        int fontSize = 64;
        phraseLabel.setFont("Monospaced-plain-"+fontSize);
        // the while loop below makes the phrase as large as possible so that it fits
        while(phraseLabel.getWidth()>WIDTH)
        {
            fontSize--;
            phraseLabel.setFont("*-*-"+fontSize);
        }
        phraseLabel.setLocation((WIDTH-phraseLabel.getWidth())/2, PHRASE_Y_OFFSET);
        add(phraseLabel);
 
        lettersGuessedLabel = new GLabel("Letters Guessed: ", GUESSED_LETTERS_X_OFFSET, GUESSED_LETTERS_Y_OFFSET);
        lettersGuessedLabel.setFont("Sansserif-Plain-18");
        lettersGuessedLabel.setColor(Color.red);
        add(lettersGuessedLabel);
    }
    
    /** Handle's Key Press Events */
    public void keyPressed(KeyEvent event)
    {
        String guess = KeyEvent.getKeyText(event.getKeyCode()).toUpperCase();  
        String lettersGuessedLetters = lettersGuessedLabel.getText().substring(16);
        
        char c = guess.charAt(0);
        // add code here to finish task #1
        if (lettersGuessedLetters.indexOf(guess) == -1 && Character.isLetter(c)) {
            if (man.makeGuess(guess)) {
                phraseLabel.setText(man.getPuzzle());
            } else {
                handleBadGuess();
            }
            lettersGuessedLabel.setText(lettersGuessedLabel.getText() + guess);
        }
    }
    
    /** Determines which body part should be drawn */
    public void handleBadGuess() {
        switch (man.getGuessesLeft()) {
            case 6: drawHead(); break;
            case 5: drawBody(); break;
            case 4: drawRightLeg(); break;
            case 3: drawLeftLeg(); break;
            case 2: drawRightArm(); break;
            case 1: drawLeftArm(); break;
        }
    }
    
    /** Determines whether the player has won or lost */
    public void handleGameOver() {
        if (man.getPuzzle().indexOf("-") == -1) {
            winner();
        } else {
            img.setImage("face.jpeg");
            img.scale(0.75);
            pause(1000);
            loser();
        }
    }
    
    /** Creates the Graphics for a winning game*/
    public void winner() {
        remove(img);
        numCorrect++;
        numTotal++;
        
        remove(lettersGuessedLabel);
        GImage confetti1 = new GImage("confetti.gif", 0, 0);
        confetti1.scale(1.75);
        GImage confetti2 = new GImage("confetti.gif", getWidth()/2, 0);
        confetti2.scale(1.75);
        GImage kirbyLeft = new GImage("kirbyLeft.gif", 2, 0);
        kirbyLeft.scale(0.3);
        kirbyLeft.setY(getHeight() - kirbyLeft.getHeight() - 2);
        GImage kirbyRight = new GImage("kirbyRight.gif", 0, 0);
        kirbyRight.scale(0.3);
        kirbyRight.setX(getWidth() - kirbyRight.getWidth() - 2);
        kirbyRight.setY(getHeight() - kirbyRight.getHeight() - 2);
        GImage correct = new GImage("correct.png", 0, 0);
        correct.scale(0.25);
        correct.setX(getWidth()/2 - correct.getWidth()/2);
        correct.setY(getHeight()/2 - correct.getHeight()/2);
        add(confetti1);
        add(confetti2);
        add(kirbyLeft);
        add(kirbyRight);
        add(correct);
        
        GLabel text = new GLabel("You won!", 0, 0);
        text.setFont("Sansserif-Plain-36");
        text.setX(getWidth()/2 - text.getWidth()/2);
        text.setY(getHeight()/2 - text.getHeight()/2 + 125);
        add(text);
        
        GLabel cont = new GLabel("Click to Play Again", 0, 0);
        cont.setFont("Sansserif-Plain-36");
        cont.setX(getWidth()/2 - cont.getWidth()/2);
        cont.setY(getHeight()/2 - cont.getHeight()/2 + 160);
        add(cont);
    }
    
    /** Creates the graphics for a losing game */
    public void loser() {
        remove(img);
        numTotal++;
        
        remove(lettersGuessedLabel);
        GImage rain = new GImage("rain.gif", 0, 0);
        rain.scale(1.7);
        GImage tryagainLeft = new GImage("tryagain.gif", 2, 0);
        tryagainLeft.scale(0.3);
        tryagainLeft.setY(getHeight() - tryagainLeft.getHeight() - 2);
        GImage tryagainRight = new GImage("tryagain.gif", 2, 0);
        tryagainRight.scale(0.3);
        tryagainRight.setY(getHeight() - tryagainRight.getHeight() - 2);
        tryagainRight.setX(getWidth() - tryagainRight.getWidth() - 2);
        GImage wrong = new GImage("wrong.png", 0, 0);
        wrong.scale(0.25);
        wrong.setX(getWidth()/2 - wrong.getWidth()/2);
        wrong.setY(getHeight()/2 - wrong.getHeight()/2);
        add(rain);
        add(tryagainLeft);
        add(tryagainRight);
        add(wrong);
        
        GLabel text = new GLabel("The word was: " + man.getActualAnswer(), 0, 0);
        text.setFont("Sansserif-Plain-36");
        text.setColor(Color.red);
        text.setX(getWidth()/2 - text.getWidth()/2);
        text.setY(getHeight()/2 - text.getHeight()/2 + 125);
        add(text);
        
        GLabel cont = new GLabel("Click to Play Again", 0, 0);
        cont.setFont("Sansserif-Plain-36");
        cont.setColor(Color.red);
        cont.setX(getWidth()/2 - cont.getWidth()/2);
        cont.setY(getHeight()/2 - cont.getHeight()/2 + 160);
        add(cont);
    }
    
    /** Draws the gallows image and creates the score label */
    public void doInitialDrawingSetup() {
        img = new GImage("gallows.jpeg", 100, 100);
        img.scale(0.75);
        img.setX(getWidth()/2 - img.getWidth()/2 + 100);
        add(img);
        
        GLabel score = new GLabel("Score: " + numCorrect + "/" + numTotal, 0, 0);
        score.setFont("Sansserif-Plain-18");
        score.setX(getWidth() - score.getWidth() - 10);
        score.setY(getHeight() - 10);
        add(score);
    }
    
    /** Draws the head */
    public void drawHead() {
        img.setImage("head.jpeg");
        img.scale(0.75);
    }
    
    /** Draws the body */
    public void drawBody() {
        img.setImage("body.jpeg");
        img.scale(0.75);
    }
    
    /** Draws the right leg */
    public void drawRightLeg() {
        img.setImage("rightleg.jpeg");
        img.scale(0.75);
    }
    
    /** Draws the left leg */
    public void drawLeftLeg() {
        img.setImage("leftleg.jpeg");
        img.scale(0.75);
    }
    
    /** Draws the right arm */
    public void drawRightArm() {
        img.setImage("rightarm.jpeg");
        img.scale(0.75);
    }
    
    /** Draws the left arm */
    public void drawLeftArm() {
        img.setImage("leftarm.jpeg");
        img.scale(0.75);
    }
}
