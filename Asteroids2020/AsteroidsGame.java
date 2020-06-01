
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.applet.AudioClip;
import acm.util.MediaTools;
import java.io.*;

public class AsteroidsGame extends GraphicsProgram
{
    private ArrayList<Asteroid> asteroids;
    private boolean playing;
    private GLabel notificationLabel, scoreLabel,levelLabel, livesLabel, highScoreLabel;
    // uncomment out the line below in version 0.4.1
    private Ship ship;
    // uncomment out the line below in version 0.5.2
    // (and don't forget to write bullets = new ArrayList<Bullet>() in the init method!)
    private ArrayList<Bullet> bullets; 
    private ArrayList<Explosion> explosions;
    private int level;
    private int ships;
    private int score;
    private int lives;
    private int highScore = 0;
    private boolean paused;
    private boolean running;
    private AudioClip thrustClip, fireClip, bigBangClip, mediumBangClip, smallBangClip;

    public void init()
    {
        thrustClip = MediaTools.loadAudioClip("sounds/thrust.wav");   
        fireClip = MediaTools.loadAudioClip("sounds/fire.wav");   
        bigBangClip = MediaTools.loadAudioClip("sounds/bangLarge.wav");   
        mediumBangClip = MediaTools.loadAudioClip("sounds/bangMedium.wav");   
        smallBangClip = MediaTools.loadAudioClip("sounds/bangSmall.wav");   

        level = 0;
        ships = 3;
        score = 0;
        lives = 3;
        highScore = readHighScore();
        paused = false;
        running = true;

        setBackground(Color.BLACK);

        notificationLabel = new GLabel("(i) = thrust, (j) = rotate left, (k) = rotate right, (space) = fire, (p) = pause. Click mouse to start.");
        notificationLabel.setColor(Color.WHITE);
        notificationLabel.setFont("Courier-Plain-12");
        notificationLabel.setLocation((getWidth()-notificationLabel.getWidth())/2, getHeight()/2-40);
        add(notificationLabel);

        scoreLabel = new GLabel("Score:"+score);
        scoreLabel.setColor(Color.WHITE);
        scoreLabel.setFont("Courier-Plain-10");
        scoreLabel.setLocation(16, 16);
        add(scoreLabel);

        levelLabel = new GLabel("Level:"+(level+1));
        levelLabel.setColor(Color.WHITE);
        levelLabel.setFont("Courier-Plain-10");
        levelLabel.setLocation(100, 16);
        add(levelLabel);

        livesLabel = new GLabel("Lives:"+lives);
        livesLabel.setColor(Color.WHITE);
        livesLabel.setFont("Courier-Plain-10");
        livesLabel.setLocation(184, 16);
        add(livesLabel);
        
        highScoreLabel = new GLabel("Highscore:"+highScore);
        highScoreLabel.setColor(Color.WHITE);
        highScoreLabel.setFont("Courier-Plain-10");
        highScoreLabel.setLocation(16, 32);
        add(highScoreLabel);

        bullets = new ArrayList<Bullet>();
        explosions = new ArrayList<Explosion>();

        asteroids = new ArrayList<Asteroid>();
        makeAsteroids(3);

        ship = new Ship(getWidth(), getHeight());
        ship.setLocation(getWidth()/2, getHeight()/2);
        add(ship);

        addKeyListeners();
        addMouseListeners();
    }

    public void keyPressed(KeyEvent e) {
        if (running) {
            if (e.getKeyCode() == KeyEvent.VK_P) {
                paused = true;
                notificationLabel.setText("Paused. Click mouse to continue...");
            }
            if (playing && !paused) {
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    ship.increaseVelocity(1);
                    //thrustClip.play();
                } else if (e.getKeyCode() == KeyEvent.VK_J) {
                    ship.rotate(15);
                } else if (e.getKeyCode() == KeyEvent.VK_K) {
                    ship.rotate(-15);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Bullet b = ship.makeBullet();
                    add(b);
                    bullets.add(b);
                    //fireClip.play();
                } 
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (running) {
            playing = true;
            paused = false;
            notificationLabel.setText("(i) = thrust, (j) = rotate left, (k) = rotate right, (space) = fire, (p) = pause.");
        }
    }

    private void makeAsteroids(int count)
    {
        // code for version 0.3.1 goes here
        for (int i = 0; i < count; i++) {
            Asteroid a = new Asteroid(getWidth(), getHeight());
            a.setLocation(Math.random() * getWidth(), Math.random() * getHeight());
            a.rotate(Math.random() * 360);
            a.increaseVelocity(1);
            add(a);
            asteroids.add(a);
        }
    }

    public void run()
    {

        while (running)
        {
            pause(10);
            // code for version 0.3 goes here
            if (!paused) {
                for (int i = 0; i < asteroids.size(); i++) {
                    asteroids.get(i).updatePosition();
                }
                for (int i = 0; i < explosions.size(); i++) {
                    if (explosions.get(i).moving()) {
                        explosions.get(i).updatePosition();
                    } else {
                        remove(explosions.get(i));
                        explosions.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < bullets.size(); i++) {
                    if (bullets.get(i).moving()) {
                        bullets.get(i).updatePosition();
                        if (checkForCollisions(bullets.get(i)) != null) {
                            shotAsteroid(checkForCollisions(bullets.get(i)));
                            remove(bullets.get(i));
                            bullets.remove(i);
                            i--;
                        }
                    } else {
                        remove(bullets.get(i));
                        bullets.remove(i);
                        i--;
                    }
                }
                if (asteroids.size() == 0) {
                    nextLevel();
                    ship.setLocation(getWidth()/2, getHeight()/2);
                    playing = false;
                    notificationLabel.setText("You are now on level " + (level + 1) + ". Click to start...");
                    levelLabel.setText("Level:" + (level + 1));
                }
                if (playing) {
                    if (checkForCollisions(ship) != null) {
                        shipCollided();
                    }

                    ship.updatePosition();
                    scoreLabel.setText("Score:"+score);
                }
            }
        } 
    }

    public void nextLevel() {
        level++;
        makeAsteroids(level + 3);
    }

    private Asteroid checkForCollisions(GVectorPolygon obj)
    {
        for (Asteroid a:asteroids)
            if (a.getBounds().intersects(obj.getBounds()))
            {
                return a;
            }
        return null;       
    }

    private void shipCollided() {
        ship.setLocation(getWidth()/2, getHeight()/2);
        playing = false;
        lives--;
        if (lives == 0) {
            running = false;
            if (score > highScore) {
                highScore = score;
                writeHighScore();
                notificationLabel.setText("You have lost all your lives. NEW Highscore:" + score + " Restart the program to play again!");
            } else {
                notificationLabel.setText("You have lost all your lives. Final Score:" + score + " Restart the program to play again!");
            }
        } else {
            notificationLabel.setText("You have lost a life. Click to continue...");
        }
        livesLabel.setText("Lives:" + lives);
    }

    private void shotAsteroid(Asteroid a) {
        makeExplosion(a);
        asteroids.remove(a);
        remove(a);
        score += 100;
        if (a instanceof MediumAsteroid) {
            mediumBangClip.play();
            SmallAsteroid m1 = new SmallAsteroid(getWidth(), getHeight());
            SmallAsteroid m2 = new SmallAsteroid(getWidth(), getHeight());
            SmallAsteroid m3 = new SmallAsteroid(getWidth(), getHeight());
            m1.setLocation(a.getLocation());
            m2.setLocation(a.getLocation());
            m3.setLocation(a.getLocation());
            m1.rotate(Math.random() * 360);
            m2.rotate(Math.random() * 360);
            m3.rotate(Math.random() * 360);
            m1.increaseVelocity(2);
            m2.increaseVelocity(2);
            m3.increaseVelocity(2);
            add(m1);
            add(m2);
            add(m3);
            asteroids.add(m1);
            asteroids.add(m2);
            asteroids.add(m3);
        } else if (!(a instanceof SmallAsteroid)) { // large asteroid
            bigBangClip.play();
            MediumAsteroid m1 = new MediumAsteroid(getWidth(), getHeight());
            MediumAsteroid m2 = new MediumAsteroid(getWidth(), getHeight());
            MediumAsteroid m3 = new MediumAsteroid(getWidth(), getHeight());
            m1.setLocation(a.getLocation());
            m2.setLocation(a.getLocation());
            m3.setLocation(a.getLocation());
            m1.rotate(Math.random() * 360);
            m2.rotate(Math.random() * 360);
            m3.rotate(Math.random() * 360);
            m1.increaseVelocity(2);
            m2.increaseVelocity(2);
            m3.increaseVelocity(2);
            add(m1);
            add(m2);
            add(m3);
            asteroids.add(m1);
            asteroids.add(m2);
            asteroids.add(m3);
        } else {
            smallBangClip.play();
        }
    }
   
    public void writeHighScore() 
    {
        String fileContent = "" + highScore;
        try {
            String fileName = "breakoutScore.txt";
            String workingDirectory = System.getProperty("user.dir");
            String absoluteFilePath = workingDirectory + File.separator + fileName;
            
            FileWriter fileWriter = new FileWriter(absoluteFilePath);
            fileWriter.write(fileContent);
            fileWriter.close();
        } catch (IOException e) {
            // error
        }
    }
    
    public int readHighScore() { 
        int returnVal = 0;
        try {
            String fileName = "breakoutScore.txt";
            String workingDirectory = System.getProperty("user.dir");
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
            // error
        }
        return returnVal;
    }
    
    public void makeExplosion(GVectorPolygon g) {
        Explosion e1 = new Explosion(getWidth(), getHeight());
        e1.setLocation(g.getLocation());
        e1.rotate(0);
        e1.increaseVelocity(8);
        add(e1);
        explosions.add(e1);
        Explosion e2 = new Explosion(getWidth(), getHeight());
        e2.setLocation(g.getLocation());
        e2.rotate(120);
        e2.increaseVelocity(8);
        add(e2);
        explosions.add(e2);
        Explosion e3 = new Explosion(getWidth(), getHeight());
        e3.setLocation(g.getLocation());
        e3.rotate(240);
        e3.increaseVelocity(8);
        add(e3);
        explosions.add(e3);
    }

}
