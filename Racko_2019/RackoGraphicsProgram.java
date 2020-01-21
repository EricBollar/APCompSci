
// Eric Bollar & Parinaz Khosravi
// AP CS, Period A
// November 20, 2019

import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class RackoGraphicsProgram extends GraphicsProgram
{
    // constants for graphics layout
    private static final int WINDOWSIZE = 500;
    private static final int OFFSET = 20;  // offset from all edges of screen
    private static final int CARDHEIGHT = 40;
    private static final int MINCARDWIDTH = 60;

    // these are all constants for the private instance variable gameState
    private static final int GAME_OVER = -1;
    private static final int PICK_DECK_OR_DISCARD = 0;
    private static final int PICK_CARD_FROM_HAND = 1;
    private static final int WAITING_FOR_COMPUTER_MOVE = 2;
    private static final int IN_BETWEEN = 3;

    // private instance variables
    private RackoGame game;
    private ComputerPlayer computer;
    private int gameState;  // will be one of four constants above
    private JLabel status;  // use status.setText to provide updates of status of game
    private int replacementCardValue;  // the card drawn from either the discard pile or deck
    private boolean gameTypePVP;
    private boolean titleScreen;
    private boolean p1Turn;
    private GLabel corner;

    public void run()
    {
        menu();
        initWindow();
        initVariables();
        drawGraphics(true);

        if (gameTypePVP) {
            p1Turn = true;
            while (gameState!=GAME_OVER) {
                if (game.playerWins())
                {   
                    gameState = GAME_OVER;
                    status.setText("Player 1 wins!");
                    removeAll();
                    drawGraphics(true);
                } else if (game.computerWins())
                {
                    gameState = GAME_OVER;
                    status.setText("Player 2 wins!");
                    removeAll();
                    drawGraphics(false);
                } else if (gameState == IN_BETWEEN) {
                    inBetween(p1Turn);
                    gameState = PICK_DECK_OR_DISCARD;
                    if (p1Turn) {
                        status.setText("P1's turn! Click on deck or discard pile.");
                    } else {
                        status.setText("P2's turn! Click on deck or discard pile.");
                    }
                    drawGraphics(p1Turn);
                }
                if (!p1Turn) {
                    corner.setText("P2");
                    corner.setColor(Color.orange);
                } else {
                    corner.setText("P1");
                    corner.setColor(Color.MAGENTA);
                }
                pause(5);
            }
        } else {
            while (gameState!=GAME_OVER)
            {   if (game.playerWins())
                {   
                    gameState = GAME_OVER;
                    status.setText("You win!");
                }
                else if (game.computerWins())
                {
                    gameState = GAME_OVER;
                    status.setText("Computer wins!");
                }
                else if (gameState == WAITING_FOR_COMPUTER_MOVE)
                {
                    makeComputerMove();
                    gameState = PICK_DECK_OR_DISCARD;
                    status.setText("Your move! Click on deck or discard pile.");
                }
                pause(5);
            }
        }
    }

    public void menu() {
        drawTitleScreen();
        while (titleScreen) {
            waitForClick();
        }
        removeTitleScreen();
    }

    public void drawTitleScreen() {
        GLabel pvp = new GLabel("Play With Someone", 0, 0);
        GLabel pvai = new GLabel("Play Against Computer", 0, 0);
        pvp.setFont("*-*-"+30);
        pvai.setFont("*-*-"+30);
        pvp.setColor(Color.BLUE);
        pvai.setColor(Color.GREEN);
        pvp.setX(getWidth()/2 - pvp.getWidth()/2);
        pvai.setX(getWidth()/2 - pvai.getWidth()/2);
        pvp.setY(getHeight()/2);
        pvai.setY(getHeight()/2 + 60);
        add(pvp);
        add(pvai);
        titleScreen = true;
    }

    public void removeTitleScreen() {
        removeAll();
        titleScreen = false;
    }

    public void initVariables()
    {
        game = new RackoGame();
        gameState = PICK_DECK_OR_DISCARD;
        computer = new PrettyGoodComputerPlayer();
    }

    public void initWindow()
    {
        setSize(WINDOWSIZE, WINDOWSIZE);
        setTitle("Rack-o");
        if (gameTypePVP) {
            status = new JLabel("P1's turn! Click on deck or discard pile.");
        } else {
            status = new JLabel("Your move!  Click on deck or discard pile.");
        }
        add(status, NORTH);
    }

    public void drawGraphics(boolean turn)
    {
        drawLabel();
        if (turn) {
            drawHand(game.getPlayerHand());
        } else {
            drawHand(game.getComputerHand());
        }
        drawDiscardPile();

        corner = new GLabel("P1", 0, 0);
        corner.setFont("*-*-"+30);
        corner.setColor(Color.orange);
        corner.setX(getWidth() - OFFSET - corner.getWidth());
        corner.setY(getHeight() - OFFSET - corner.getHeight());
        add(corner);
    }

    public void drawLabel()
    {
        GLabel deck = new GLabel("Deck", OFFSET, WINDOWSIZE/2 - 45);
        deck.setColor(Color.red);
        deck.setFont("*-PLAIN-30");
        add(deck);
    }

    public void drawHand(int[] hand)
    {
        for (int i=0; i<10; i++)
        {
            int currentCardValue = hand[i];
            GImage card = getCardForValue(currentCardValue);
            card.setY(OFFSET + CARDHEIGHT * i);
            card.setX(WINDOWSIZE/2);
            card.setColor(Color.black);
            add(card);
        }
    }

    public void drawDiscardPile()
    {
        int currentCardValue = game.peekAtTopCardFromDiscardPile();
        GImage card = getCardForValue(currentCardValue);
        card.setY(WINDOWSIZE/2);
        card.setX(OFFSET);
        card.setColor(Color.blue);
        add(card);
    }

    public void handleTitleScreenEvents(MouseEvent e) {
        GObject obj = getElementAt(e.getX(), e.getY());
        if (obj.getColor() != null) {
            if (obj.getColor() == Color.BLUE) {
                gameTypePVP = true;
            } else {
                gameTypePVP = false;
            }
            titleScreen = false;
        }
    }

    public void mouseClicked(MouseEvent event)
    {
        if (titleScreen) {
            handleTitleScreenEvents(event);
        } else if (gameTypePVP) {
            handlePvPEvents(event);
        } else {
            handlePvAIEvents(event);
        }
    }

    public void handlePvPEvents(MouseEvent e) {
        GObject obj = getElementAt(e.getX(), e.getY());
        if (gameState == PICK_DECK_OR_DISCARD) {
            removeAll();
            drawGraphics(p1Turn);
            if (obj.getColor() == Color.red) {
                replacementCardValue = game.removeTopCardFromDeck();
                gameState = PICK_CARD_FROM_HAND;
                status.setText("Where do you want to put " + replacementCardValue
                    + "? Click on a card in your hand.");
            } else if (obj.getColor() == Color.blue) {
                replacementCardValue = game.removeTopCardFromDiscardPile();
                gameState = PICK_CARD_FROM_HAND;
                status.setText("Where do you want to put " + replacementCardValue
                    + "? Click on a card in your hand.");
            }
        } else if (gameState == PICK_CARD_FROM_HAND) {
            if (obj.getColor() == Color.black) {
                int value = getValueForCard(obj);
                int[] playerHand;
                if (p1Turn) {
                    playerHand = game.getPlayerHand();
                } else {
                    playerHand = game.getComputerHand();
                }
                status.setText("t");
                for (int i=0; i<playerHand.length; i++)
                {
                    if (value == playerHand[i])
                    {
                        game.makeMove(replacementCardValue, i, playerHand);

                        status.setText("Finished turn...");
                        gameState = PICK_DECK_OR_DISCARD;
                        gameState = IN_BETWEEN;
                        p1Turn = !p1Turn;

                        return;
                    }
                }
            }
        }
    }

    public void inBetween(boolean turn) {
        removeAll();
        GLabel txt = new GLabel("asdf", 10, 10);
        add(txt);
        if (turn) {
            txt.setText("Player 1's Turn Next...");
        } else {
            txt.setText("Player 2's Turn Next...");
        }
        txt.setX(getWidth()/2 - txt.getWidth()/2);
        txt.setY(getHeight()/2);
        add(txt);
        GLabel timer = new GLabel("3", 10, 10);
        timer.setFont("*-*-"+30);
        timer.setX(getWidth()/2 - timer.getWidth()/2);
        timer.setY(getHeight()/2 + 40);
        add(timer);
        pause(1000);
        timer.setText("2");
        pause(1000);
        timer.setText("1");
        pause(1000);
        removeAll();
    }

    public void handlePvAIEvents(MouseEvent event) {
        if (gameState == GAME_OVER || gameState == WAITING_FOR_COMPUTER_MOVE)
        {
        }
        else if (gameState == PICK_DECK_OR_DISCARD)
        {
            if (getElementAt(event.getX(), event.getY()).getColor() != null)
            {
                if (getElementAt(event.getX(), event.getY()).getColor() == Color.red)
                {
                    replacementCardValue = game.removeTopCardFromDeck();
                    gameState = PICK_CARD_FROM_HAND;
                    status.setText("Where do you want to put " + replacementCardValue
                        + "? Click on a card in your hand.");
                }
                else if (getElementAt(event.getX(), event.getY()).getColor() == Color.blue)
                {
                    replacementCardValue = game.removeTopCardFromDiscardPile();
                    gameState = PICK_CARD_FROM_HAND;
                    status.setText("Where do you want to put " + replacementCardValue
                        + "? Click on a card in your hand.");
                }
            }
        }
        else if (gameState == PICK_CARD_FROM_HAND)
        {
            GObject element = getElementAt(event.getX(), event.getY());
            if (element == null || element.getColor() == Color.red ||
            element.getColor() == Color.blue)
            {
            }
            else
            {
                int value = getValueForCard(element);
                int[] playerHand = game.getPlayerHand();
                for (int i=0; i<playerHand.length; i++)
                {
                    if (value == playerHand[i])
                    {
                        game.makeMove(replacementCardValue, i, playerHand);
                        status.setText("Waiting for computer to move...");
                        removeAll();
                        drawGraphics(true);
                        gameState = WAITING_FOR_COMPUTER_MOVE;

                        return;
                    }
                }
            }
        }
    }

    private void makeComputerMove()
    {
        pause(1000);
        int replacementCardValue;
        String outputMessage;
        if (computer.shouldDrawFromDiscardPile(game.peekAtTopCardFromDiscardPile(),game.getComputerHand()))
        {
            replacementCardValue = game.removeTopCardFromDiscardPile();
            outputMessage = " from discard pile.";
        }
        else
        {
            replacementCardValue = game.removeTopCardFromDeck();
            outputMessage = " from deck.";
        }
        status.setText("Computer picks " + replacementCardValue + outputMessage);
        pause(1000);
        game.makeMove(replacementCardValue, computer.getIndexForReplacementCard(replacementCardValue,
                game.getComputerHand()), game.getComputerHand());
        removeAll();
        drawGraphics(true);
        gameState = PICK_DECK_OR_DISCARD;
    }

    private GImage getCardForValue(int cardValue)
    {
        return new GImage("card_"+cardValue+".png");
    }

    private int getValueForCard(GObject card)
    {
        return (int)(card.getWidth()-MINCARDWIDTH);
    }

}