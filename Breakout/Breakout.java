
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
// September 8, 2019

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
     GLabel tipsLabel; // helpful tips that scroll across the bottom below the paddle
     String[] tips = new String[4];
     boolean changeTip = false;
     double dx;
     double dy;
     boolean RUNNING = true;
     int score = 0;
     int highScore = 0;
     boolean newRecord = false;
     AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
     Font font = new Font("Arial", Font.PLAIN, 15); // the font used for all the labels
    
/** Runs the Breakout program. */
    public void run() 
    {
        initBricks(); // creates the rows of bricks
        initPaddle(); // creates the paddle
        initBall(); // creates the ball
        highScore = readHighScore(); // sets the value of highScore to the player's highscore
        setupScore(); // creates the scoreLabel and the highScoreLabel
        setupTips();
        while (RUNNING) { // game loop
            updateBall(); // update ball's position
            checkForCollisions(); // implements the games "physics"
            updateTips();
            pause(5); // delay cause computers are too speedy
        }
    }
    
    public void setupTips() {
        tips[0] = "Tip: The paddle follows your mouse!";
        tips[1] = "Tip: Every brick is worth 100 points!";
        tips[2] = "Tip: Try and bounce the ball behind the bricks!";
        tips[3] = "Tip: Your highest score is: " + highScore + "!";
        
        tipsLabel = new GLabel (tips[(int) (Math.random() * tips.length) - 1], WIDTH * 2, HEIGHT - PADDLE_Y_OFFSET * 2);
        tipsLabel.setFont(font);
        add(tipsLabel);
    }
    
    public void updateTips() {
        int scoreToChangeTip = 500;
        if ((score + 100) % scoreToChangeTip == 0 && changeTip) {
            tipsLabel.setText(tips[(int) (Math.random() * tips.length) - 1]);
            changeTip = false;
        } else if ((score + 100) % scoreToChangeTip != 0) {
            changeTip = true;
        }
        
        int buffer = 900; // basically the pause between when the tip label shows up again
        if (tipsLabel.getX() > WIDTH + buffer) {
            tipsLabel.setLocation(-tipsLabel.getWidth() - buffer , tipsLabel.getY());
        }
        tipsLabel.move(1, 0);
    }
    
    public void setupScore() {
        scoreLabel = new GLabel("Score: 0", 20, 20);
        scoreLabel.setFont(font);
        add(scoreLabel); // make a label for player's score
        
        highScoreLabel = new GLabel("Highscore: " + highScore, WIDTH - 150, 20);
        highScoreLabel.setFont(font);
        add(highScoreLabel); // make a label for player's highscore
    }
    
    public void updateScore() {
        scoreLabel.setText("Score: " + score);
        
        // if a new record is set then set the highscorelabel to the player's current score
        if (score > highScore) {
            highScoreLabel.setText("NEW Highscore: " + score);
            highScore = score;
            newRecord = true;
        }
    }
    
    public void checkForCollisions() {
        checkWallCollisions();
        
        // paddles and bricks
        if (!checkCollisionTopLeft()) {
            if (!checkCollisionTopRight()) {
                if (!checkCollisionBottomLeft()) {
                    checkCollisionBottomRight();
                }
            }
        }
    }
    
    public void checkWallCollisions() {
        if (ball.getX() < 0) { // left wall
            dx = -dx;
        } else if (ball.getX() > WIDTH - 2 * BALL_RADIUS) { // right wall
            dx = -dx;
        }
        if (ball.getY() < 0) { // top wall
            dy = -dy;
        } else if (ball.getY() > HEIGHT) { // bottom wall
            RUNNING = false;
            GLabel gameover = new GLabel("Game Over!", WIDTH/2 - 80, HEIGHT/2);
            Font gFont = new Font("Arial", Font.PLAIN, 36);
            gameover.setFont(gFont);
            add(gameover);
            scoreLabel.setLocation(WIDTH/2 - 50, HEIGHT/2 + 20);
            highScoreLabel.setLocation(WIDTH/2 - 50, HEIGHT/2 + 40);
            if (newRecord) {
                saveHighScore(highScore);
            }
            remove(tipsLabel);
        }
    }
    
    public boolean checkCollisionTopLeft() {
        if (getElementAt(ball.getX(), ball.getY()) != null) { // top-left corner
            GObject g = getElementAt(ball.getX(), ball.getY());
            if (g == paddle) {
                dy = -dy;
                ball.move(0, -2); // this prevents glitching where the ball gets "stuck" in the paddle
            } else if (g != scoreLabel && g != highScoreLabel && g != tipsLabel){ // if it is a brick ...
                remove(getElementAt(ball.getX(), ball.getY()));
                score += 100;
                updateScore();
                dy = -dy;
                bounceClip.play();
            }
            return true;
        }
        return false;
    }
    
    public boolean checkCollisionBottomLeft() {
        if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) { // bottom-left corner
            GObject g = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
            if (g == paddle) {
                dy = -dy;
                ball.move(0, -2); // this prevents glitching where the ball gets "stuck" in the paddle
            } else if (g != scoreLabel && g != highScoreLabel && g != tipsLabel){ // if it is a brick ...
                remove(getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS));
                score += 100;
                updateScore();
                dy = -dy;
                bounceClip.play();
            }
            return true;
        }
        return false;
    }
    
    public boolean checkCollisionTopRight() {
        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) { // top-right corner
            GObject g = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
            if (g == paddle) {
                dy = -dy;
                ball.move(0, -2); // this prevents glitching where the ball gets "stuck" in the paddle
            } else if (g != scoreLabel && g != highScoreLabel && g != tipsLabel) { // if it is a brick ...
                remove(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()));
                score += 100;
                updateScore();
                dy = -dy;
                bounceClip.play();
            }
            return true;
        }
        return false;
    }
    
    public boolean checkCollisionBottomRight() {
        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) { // bottom-right corner
            GObject g = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
            if (g == paddle) {
                dy = -dy;
                ball.move(0, -2); // this prevents glitching where the ball gets "stuck" in the paddle
            } else if (g != scoreLabel && g != highScoreLabel && g != tipsLabel){ // if it is a brick ...
                remove(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS));
                score += 100;
                updateScore();
                dy = -dy;
                bounceClip.play();
            }
            return true;
        }
        return false;
    }
    
    public void updateBall() {
        ball.move(dx, dy);
    }
    
    public void initBall() {
        ball = new GOval(WIDTH/2, HEIGHT/2, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.black);
        add(ball);
        
        dx = 1 + Math.random() * 2;
        if (Math.random()>0.5)
        {
            dx = -dx;
        }
        dy = 1;
    }
    
    
    public void mouseMoved(MouseEvent e) {
        int buffer = 20; // this is how many pixels over the game border the paddle is allowed to go
                         // (this helps with gameplay)
        
        // this just makes paddle stay witihin edges of game border
        if (e.getX() > PADDLE_WIDTH * 0.5 - buffer && e.getX() < WIDTH - PADDLE_WIDTH * 0.5 + buffer) {
            paddle.setLocation(e.getX() - 0.5 * PADDLE_WIDTH, paddle.getY());
        }
    }
    
    public void initPaddle() {
        paddle = new GRect(WIDTH/2 - 0.5 * PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setColor(Color.black);
        paddle.setFilled(true);
        add(paddle);
    }
    
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
    
    public void saveHighScore(int i) // this writes an int to the breakoutScore text file
    {
        String fileContent = "" + i;
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
    
    public int readHighScore() { // this returns the int value of the breakoutScore text file
        int returnVal = 0;
        try {
            String fileName = "breakoutScore.txt";
            String workingDirectory = System.getProperty("user.dir"); // this is just so that it's cross-platform
            String absoluteFilePath = workingDirectory + File.separator + fileName;
        
            File file = new File(absoluteFilePath);

            if (file.createNewFile()) {
                saveHighScore(0);
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
