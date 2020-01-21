
// Eric Bollar & Parinaz Khosravi
// AP CS, Period A
// November 20, 2019

import java.util.ArrayList;

public class RackoGame
{
    private ArrayList<Integer> deck;
    private ArrayList<Integer> discardPile;
    private int[] playerHand;
    private int[] computerHand;

    public RackoGame()
    {
        deck = new ArrayList<Integer>();
        playerHand = new int[10];
        computerHand = new int[10];
        discardPile = new ArrayList<Integer>();

        fillDeck();
        shuffleDeck();
        dealCards();
        discardPile.add(deck.remove(0));
    }

    public void dealCards()
    {
        for (int i = 0; i < 10; i++)
        {
            playerHand[i] = deck.remove(0);
            computerHand[i] = deck.remove(0);
        }
    }

    public void fillDeck()
    {
        for (int i=1; i<=60; i++)
        {
            deck.add(i);
        }
    }

    public void shuffleDeck()
    {
        for (int i=0; i<deck.size(); i++)
        {
            Integer swap = deck.get(i);
            int secondIndex = (int)(Math.random()*deck.size());
            deck.set(i, deck.get(secondIndex));
            deck.set(secondIndex, swap);
        }
    }

    public void makeMove(int newCardValue, int handIndex, int[] hand)
    {
        discardPile.add(hand[handIndex]);
        hand[handIndex] = newCardValue;
    }

    public void resetDiscardPile()
    {
        for (int i=0; i<discardPile.size(); i=0)
        {
            deck.add(discardPile.remove(i));
        }
    }

    public boolean playerWins()
    {
        for (int i=0; i<playerHand.length-1; i++)
        {
            if (playerHand[i]>playerHand[i+1])
                return false;
        }
        return true;
    }

    public boolean computerWins()
    {
        for (int i=0; i<computerHand.length-1; i++)
        {
            if (computerHand[i]>computerHand[i+1])
                return false;
        }
        return true;
    }

    public int removeTopCardFromDeck()
    {
        return deck.remove(0);
    }

    public int removeTopCardFromDiscardPile()
    {
        return discardPile.remove(discardPile.size() - 1);
    }

    public int peekAtTopCardFromDiscardPile()
    {
        return discardPile.get(discardPile.size() - 1);
    }

    public int[] getPlayerHand()
    {
        return playerHand;
    }

    public int[] getComputerHand()
    {
        return computerHand;
    }
}