
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

// Eric Bollar
// AP CS Period A
// September 16, 2019

public class Breakout extends GraphicsProgram 
{

/** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board in pixels (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
    private static final int BRICK_SEP = 4;

/** Width of a brick */
    private static final int BRICK_WIDTH =
      (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
    private static final int NTURNS = 3;

 /** Global variables declared here.  You should feel free to add others as needed. */
     GRect paddle; // the paddle
     GOval ball; // the ball
     GLabel scoreLabel; // the player's current score
     GLabel highScoreLabel; // the player's highest score
     GLabel livesLabel;
     boolean changeTip = false;
     double dx;
     double dy;
     int lives = 3;
     boolean RUNNING = true;
     int score = 0;
     int highScore = 0;
     boolean newRecord = false;
     int bricksDestroyed = 0;
     AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
     Font font = new Font("Arial", Font.PLAIN, 15); // the font used for all the labels
    
/** Runs the Breakout program. */
    public void run() 
    {
        setupGame();
        while (RUNNING) { // game loop
            updateBall(); // update ball's position
            checkForCollisions(); // implements the games "physics"
            pause(5); // delay cause computers are too speedy
        }
    }
    
    /** Starts the game up*/
    public void setupGame() {
        initBricks(); // creates the rows of bricks
        initPaddle(); // creates the paddle
        initBall(); // creates the ball
        highScore = readHighScore(); // sets the value of highScore to the player's highscore
        setupScore(); // creates the scoreLabel and the highScoreLabel
        setupLives(); // creates the lives label
        RUNNING = true; // for the game loop
        threeSecondCountdown();
    }
    
    /** Resets all variables*/
    public void restartGame() {
        removeAll();
        lives = 3;
        score = 0;
        highScore = readHighScore();
        newRecord = false;
        changeTip = false;
        bricksDestroyed = 0;
        
        setupGame();
    }
    
    /** Resets the lives counter and creates the lives Label*/
    public void setupLives() {
        livesLabel = new GLabel("Lives: " + lives, 20, 40);
        livesLabel.setFont(font);
        add(livesLabel); // make a label for player's score
    }
    
    /** Updates the livesLabel text*/
    public void updateLives() {
        livesLabel.setText("Lives: " + lives);
    }
    
    /** Resets player's score and creates both score Label and highScore Label*/
    public void setupScore() {
        scoreLabel = new GLabel("Score: " + score, 20, 20);
        scoreLabel.setFont(font);
        add(scoreLabel); // make a label for player's score
        
        highScoreLabel = new GLabel("Highscore: " + highScore, WIDTH - 150, 20);
        highScoreLabel.setFont(font);
        add(highScoreLabel); // make a label for player's highscore
        highScoreLabel.setLocation(WIDTH - highScoreLabel.getWidth() - 20, highScoreLabel.getY());
    }
    
    /** Recreates the ball*/
    public void resetBall() {
        // reset ball's position and its velocity
        remove(ball);
        initBall();
        
        threeSecondCountdown();
    }
    
    /** Checks if there is a new highscore and updates score Label text*/
    public void updateScore() {
        scoreLabel.setText("Score: " + score);
        
        // if a new record is set then set the highscorelabel to the player's current score
        if (score > highScore) {
            highScoreLabel.setText("NEW Highscore: " + score);
            highScore = score;
            newRecord = true;
        }
        
        highScoreLabel.setLocation(WIDTH - highScoreLabel.getWidth() - 20, highScoreLabel.getY());
    }
    
    /** Implements the ball's "physics"*/
    public void checkForCollisions() {
        checkWallCollisions();
        
        // paddles and bricks
        GObject g;
        g = getElementAt(ball.getX(), ball.getY()); // top-left corner
        checkCollision(g);
        g = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS); // bottom-left corner
        checkCollision(g);
        g = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS); // bottom-right corner
        checkCollision(g);
        g = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()); // top-right corner
        checkCollision(g);
        
        if (bricksDestroyed == NBRICKS_PER_ROW * NBRICK_ROWS) {
            initBricks();
            resetBall();
            bricksDestroyed = 0;
        }
    }
    
    public boolean checkCollision(GObject other) {
        if (other != null) { // top-right corner
            if (other == paddle) {
                if (dy > 0) {
                    dy = -dy;
                }
            } else if (other != scoreLabel && other != highScoreLabel && other != livesLabel) { // if it is a brick ...
                hitBrick(other);
            }
            return true;
        }
        return false;
    }
    
    /** Checks if the ball hits a wall*/
    public void checkWallCollisions() {
        if (ball.getX() < 0) { // left wall
            dx = -dx;
        } else if (ball.getX() > WIDTH - 2 * BALL_RADIUS) { // right wall
            dx = -dx;
        }
        if (ball.getY() < 0) { // top wall
            dy = -dy;
        } else if (ball.getY() > HEIGHT) { // bottom wall
            if (lives > 1) {
                lives--; // take away a life
                updateLives();
                resetBall();
            } else {
                playerLoses();
            }
        }
    }
    
    /** Removes the brick and adjusts variables*/
    public void hitBrick(GObject g) {
        remove(g);
        score += 100;
        updateScore();
        dy = -dy;
        bounceClip.play();
        bricksDestroyed++;
    }
    
    /** Pauses the game and waits for player to click and play again*/
    public void playerLoses() {
        RUNNING = false; // pause game loop
        
        remove(livesLabel);
        createLoseScreen();
        
        if (newRecord) {
            writeHighScore();
        }
        
        waitForClick();
        restartGame();
    }
    
    /** 3 second countdown...*/
    public void threeSecondCountdown() {
        GLabel countdown = new GLabel("3", -1, -1);
        Font f = new Font("Arial", Font.PLAIN, 28);
        countdown.setFont(f);
        add(countdown);
        countdown.setLocation(WIDTH/2, HEIGHT/2 + 50);
        countdown.move(-countdown.getWidth()/2, 0);
        pause(1000);
        countdown.setText("2");
        pause(1000);
        countdown.setText("1");
        pause(1000);
        remove(countdown);
    }
    
    /** Creates the lose screen when ball falls through floor*/
    public void createLoseScreen() {
        // the larger font
        Font gFont = new Font("Arial", Font.PLAIN, 36);
        
        // Game Over! text label
        GLabel gameover = new GLabel("Game Over!", WIDTH/2, HEIGHT/2);
        gameover.setFont(gFont);
        gameover.move(-gameover.getWidth()/2, 0);
        add(gameover);
        
        // Click to Play Again! text label
        GLabel click = new GLabel("Click to Play Again", WIDTH/2, HEIGHT/2 + 90);
        click.setFont(gFont);
        click.move(-click.getWidth()/2, 0);
        add(click);
            
        // moves the score and high score text labels to the middle
        scoreLabel.setLocation(WIDTH/2, HEIGHT/2 + 20);
        highScoreLabel.setLocation(WIDTH/2, HEIGHT/2 + 40);
        scoreLabel.move(-scoreLabel.getWidth()/2, 0);
        highScoreLabel.move(-highScoreLabel.getWidth()/2, 0);
    }
    
    /** Updates balls position*/
    public void updateBall() {
        ball.move(dx, dy);
        
        // Thread t = new Thread(new Trail (ball.getX(), ball.getY(), 5, 1000));
        // t.start();
    }
    
    /** Creates the ball*/
    public void initBall() {
        // make ball
        ball = new GOval(WIDTH/2 - BALL_RADIUS, HEIGHT/2 + BALL_RADIUS - 10, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.black);
        add(ball);
        
        // set ball velocity
        dx = 1 + Math.random() * 2;
        if (Math.random()>0.5)
        {
            dx = -dx;
        }
        dy = 1;
    }
    
    /** Moves the paddle with the mouse's x position*/
    public void mouseMoved(MouseEvent e) {
        int buffer = 20; // this is how many pixels over the game border the paddle is allowed to go
                         // (this helps with gameplay in my opinion)
        
        // this just makes paddle stay witihin edges of game border
        if (e.getX() > PADDLE_WIDTH * 0.5 - buffer && e.getX() < WIDTH - PADDLE_WIDTH * 0.5 + buffer) {
            paddle.setLocation(e.getX() - 0.5 * PADDLE_WIDTH, paddle.getY());
        }
    }
    
    /** Creates the paddle GObject*/
    public void initPaddle() {
        paddle = new GRect(WIDTH/2 - 0.5 * PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setColor(Color.black);
        paddle.setFilled(true);
        add(paddle);
    }
    
    /** Creates the rows of bricks*/
    public void initBricks() {
        int counter = 0;
        int rowsPerColor = 2;
        for (int r = 1; r < NBRICK_ROWS + 1; r++) {
           for (int c = 0; c < NBRICKS_PER_ROW; c++) {
               GRect brick = new GRect(BRICK_SEP + (BRICK_WIDTH + BRICK_SEP) * c, BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * r,
                                       BRICK_WIDTH, BRICK_HEIGHT);
               switch (counter) {
                   case 0: brick.setColor(Color.red); break;
                   case 1: brick.setColor(Color.orange); break;
                   case 2: brick.setColor(Color.yellow); break;
                   case 3: brick.setColor(Color.green); break;
                   case 4: brick.setColor(Color.cyan); break;
               }
               brick.setFilled(true);
               add(brick);
           }
            if (r % rowsPerColor == 0) {
               if (counter == 4) {
                   counter = 0;
               } else {
                   counter++;
               }
           }
       }
    }
    
    /** Writes the value of highScore to breakoutScore.txt file*/
    public void writeHighScore() // this writes an int to the breakoutScore text file
    {
        String fileContent = "" + highScore;
        try {
            String fileName = "breakoutScore.txt";
            String workingDirectory = System.getProperty("user.dir"); // this is just so that it's cross-platform
            String absoluteFilePath = workingDirectory + File.separator + fileName;
            
            FileWriter fileWriter = new FileWriter(absoluteFilePath);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            // Error rip :(
        }
    }
    
    /** Sets the value of highScore to the value stored in breakoutScore.txt*/
    public int readHighScore() { // this returns the int value of the breakoutScore text file
        int returnVal = 0;
        try {
            String fileName = "breakoutScore.txt";
            String workingDirectory = System.getProperty("user.dir"); // this is just so that it's cross-platform
            String absoluteFilePath = workingDirectory + File.separator + fileName;
        
            File file = new File(absoluteFilePath);

            if (file.createNewFile()) {
                writeHighScore();
            } else {
                String text = "";
                Scanner sc = new Scanner(file);
                while (sc.hasNextInt()) {
                    text += sc.nextInt();
                }
                returnVal = Integer.parseInt(text);
            }
        } catch (IOException e) {
            highScore = -1;
            // error :(
        }
        return returnVal;
    }
   
}
