
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

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
     GRect paddle;
     GOval ball;
     GLabel scoreLabel;
     GLabel highScoreLabel;
     double dx;
     double dy;
     boolean RUNNING = true;
     int score = 0;
     int highScore = 0;
     boolean newRecord = false;
    
/** Runs the Breakout program. */
    public void run() 
    {
        initBricks();
        initPaddle();
        initBall();
        //addMouseListeners();
        addKeyListeners();
        highScore = readHighScore();
        setupScore();
        saveHighScore();
        while (RUNNING) {
            updateBall();
            checkForCollisions();
            pause(5);
        }
    }
    
    public void setupScore() {
        scoreLabel = new GLabel("Score: 0", 20, 20);
        Font font = new Font("Arial", Font.PLAIN, 15);
        scoreLabel.setFont(font);
        add(scoreLabel);
        highScoreLabel = new GLabel("Highscore: " + highScore, WIDTH - 150, 20);
        highScoreLabel.setFont(font);
        add(highScoreLabel);
    }
    
    public void updateScore() {
        scoreLabel.setText("Score: " + score);
        if (score > highScore) {
            highScoreLabel.setText("NEW Highscore: " + score);
            highScore = score;
            newRecord = true;
        }
    }
    
    public void checkForCollisions() {
        if (ball.getX() < 0) {
            dx = -dx;
        } else if (ball.getX() > WIDTH - 2 * BALL_RADIUS) {
            dx = -dx;
        }
        if (ball.getY() < 0) {
            dy = -dy;
        } else if (ball.getY() > HEIGHT) {
            RUNNING = false;
            GLabel gameover = new GLabel("Game Over!", WIDTH/2 - 80, HEIGHT/2);
            Font font = new Font("Arial", Font.PLAIN, 36);
            gameover.setFont(font);
            add(gameover);
            scoreLabel.setLocation(WIDTH/2 - 50, HEIGHT/2 + 20);
            highScoreLabel.setLocation(WIDTH/2 - 50, HEIGHT/2 + 40);
            if (newRecord) {
                saveHighScore();
            }
        }
        
        // paddles and bricks
        if (getElementAt(ball.getX(), ball.getY()) != null) { // top-left corner
            if (getElementAt(ball.getX(), ball.getY()) == paddle) {
                dy = -dy;
            } else {
                remove(getElementAt(ball.getX(), ball.getY()));
                score += 100;
                updateScore();
                dy = -dy;
            }
        } else if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null) { // bottom-left corner
            if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) == paddle) {
                dy = -dy;
            } else {
                remove(getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS));
                score += 100;
                updateScore();
                dy = -dy;
            }
        }else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) { // top-right corner
            if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) == paddle) {
                dy = -dy;
            } else {
                remove(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()));
                score += 100;
                updateScore();
                dy = -dy;
            }
        }else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null) { // bottom-right corner
            if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) == paddle) {
                dy = -dy;
            } else {
                remove(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS));
                score += 100;
                updateScore();
                dy = -dy;
            }
        }
    }
    
    public void updateBall() {
        ball.setLocation(ball.getX() + dx, ball.getY() + dy);
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
        int buffer = 20;
        if (e.getX() > PADDLE_WIDTH * 0.5 - buffer && e.getX() < WIDTH - PADDLE_WIDTH * 0.5 + buffer) {
            paddle.setLocation(e.getX() - 0.5 * PADDLE_WIDTH, paddle.getY());
        }
    }
    
    /* below does not work yet
    public void keyPressed(KeyEvent k) {
        int keyCode = k.getKeyCode();
        int buffer = 20;
        if (keyCode == 0x7C) {
            if (PADDLE_WIDTH * 0.5 - buffer > -buffer) {
                paddle.setLocation(paddle.getX() - 2, paddle.getY());
            }
        }
    }
    */
    
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
    
    public void saveHighScore()
    {
        String fileContent = "" + highScore;
        try {
            FileWriter fileWriter = new FileWriter("/Users/ericbollar/Desktop/APCompSci/Breakout/breakoutScore.txt");
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error :)");
        }
    }
    
    public int readHighScore() {
        int s = 0;
        try {
            Scanner sc = new Scanner(new File ("/Users/ericbollar/Desktop/APCompSci/Breakout/breakoutScore.txt"));
            int i;
            String text = "";
            while (sc.hasNextInt()) {
                text += sc.nextInt();
            }
            s = Integer.parseInt(text);
        } catch (IOException e) {
            System.out.println("Error :)");
        }
        return s;
    }
   
}
