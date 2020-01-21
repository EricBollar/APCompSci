
public class PrettyGoodComputerPlayer implements ComputerPlayer
{
    private int[] bestAscendingHand;

    public PrettyGoodComputerPlayer()
    {
        bestAscendingHand = new int[10];
    }

    public boolean shouldDrawFromDiscardPile(int topCardInDiscardPile, int[] hand)
    {
        bestAscendingHand = new int[10]; // Used to keep track of cards to keep

        // Marks the cards that make it impossible to win for replacement; otherwise,
        // keep the card value the same in the array
        for (int i = 0; i < hand.length; i++) {
            if ((i >= 0 && i < (9 - (60 - hand[i]))) || 
            (i <= 9 && hand[i] < i + 1)) {
                bestAscendingHand[i] = -1;
            }
            else {
                bestAscendingHand[i] = hand[i];
            }
        }

        // An array with cards to be kept and cards to be replaced
        bestAscendingHand = findAscendingHand(bestAscendingHand);
        
        return determineUse(topCardInDiscardPile, bestAscendingHand);
    }

    public int getIndexForReplacementCard(int newCardValue, int[] hand)
    {
        if (determineUse(newCardValue, bestAscendingHand))
            return choosePosition(newCardValue, bestAscendingHand);
        else
        {
            return chooseBestPossibleIndex(newCardValue, hand);
        }
    }

    private int[] findAscendingHand(int[] hand) {
        int[] ascendingHand = new int[hand.length];
        int loIndex = 0;    // Index of the lowest valued card kept at a time
        int lower = 0;      // loIndex + 1 to remain noninclusive of that index
        int lowerVal = 0;   // Value of the card at the loIndex but incremented by one to remain noninclusive

        // Make the array take in all the values
        for (int a = 0; a < hand.length; a++) 
        {
            ascendingHand[a] = hand[a];
        }

        // Go through every card except the last card and compare it with all other cards
        // after it that are lower
        for (int i = 0; i < hand.length-1; i++) 
        {
            // If the card is already marked for removal, don't check it
            if (ascendingHand[i] == -1) 
            {
                continue;
            }
            else 
            {
                // Determine whether the card can be kept based on what the previous
                // card's value and position
                if (!checkIfPossible(i, lower, hand[i], lowerVal)) 
                {
                    ascendingHand[i] = -1;
                    continue;
                }
                for (int j = i + 1; j < hand.length; j++) {
                    // Check cards that have lower values than the current card
                    if (hand[j] < hand[i] && ascendingHand[j] != -1) 
                    {
                        // Check if there are more spaces between the card and the end
                        // or more spaces between the card and the last card that was kept
                        // This comparison is done with both the current card and the card
                        // it is being compared with
                        if (j - lower >= 9 - j) 
                        { 
                            // More cards between the card and the last KEPT card
                            if (i - lower >= 9 - i) 
                            { 
                                // More cards between the card and the last KEPT card
                                // Check if the current card or the other card has a
                                // higher range; if the other card is better, make the
                                // current card -1, otherwise keep the current card
                                // and mark the other card -1
                                if (hand[j] - hand[loIndex] > hand[i] - hand[loIndex]) 
                                {
                                    //Check whether other card has a possible place in the hand
                                    if (checkIfPossible(j, lower, hand[j], lowerVal)) 
                                    {
                                        ascendingHand[i] = -1;
                                        break;
                                    }
                                    else {
                                        ascendingHand[i] = hand[i];
                                        ascendingHand[j] = -1;
                                    }
                                }
                                else {
                                    ascendingHand[i] = hand[i];
                                    ascendingHand[j] = -1;
                                }
                            }
                            else if (i - lower < 9 - i) 
                            { 
                                // More cards between the card and the last card
                                if (hand[j] - hand[loIndex] > 60 - hand[i]) 
                                {
                                    //Check whether other card has a possible place in the hand
                                    if (checkIfPossible(j, lower, hand[j], lowerVal)) 
                                    {
                                        ascendingHand[i] = -1;
                                        break;
                                    }
                                    else {
                                        ascendingHand[i] = hand[i];
                                        ascendingHand[j] = -1;
                                    }
                                }
                                else {
                                    ascendingHand[i] = hand[i];
                                    ascendingHand[j] = -1;
                                }
                            }
                        }
                        else if (j - lower < 9 - j) 
                        { 
                            // More cards between the card and the last card
                            if (i - lower >= 9 - i) 
                            {   // More cards between the card and the last KEPT card
                                if (60 - hand[j] > hand[i] - hand[loIndex]) 
                                {
                                    //Check whether other card has a possible place in the hand
                                    if (checkIfPossible(j, lower, hand[j], lowerVal)) 
                                    {
                                        ascendingHand[i] = -1;
                                        break;
                                    }
                                    else 
                                    {
                                        ascendingHand[i] = hand[i];
                                        ascendingHand[j] = -1;
                                    }
                                }
                                else 
                                {
                                    ascendingHand[i] = hand[i];
                                    ascendingHand[j] = -1;
                                }
                            }
                            else if (i - lower < 9 - i) 
                            { 
                                // More cards between the card and the last card
                                if (60 - hand[j] > 60 - hand[i]) 
                                {
                                    //Check whether other card has a possible place in the hand
                                    if (checkIfPossible(j, lower, hand[j], lowerVal)) 
                                    {
                                        ascendingHand[i] = -1;
                                        break;
                                    }
                                    else 
                                    {
                                        ascendingHand[i] = hand[i];
                                        ascendingHand[j] = -1;
                                    }
                                }
                                else 
                                {
                                    ascendingHand[i] = hand[i];
                                    ascendingHand[j] = -1;
                                }
                            }
                        }
                    }
                }

                // If the current card was not marked for replacement, increment the lower
                // index variables to account for the current card being the latest addition
                // to the best ascending hand
                if (ascendingHand[i] != -1) {
                    loIndex = i;
                    lower = i + 1;
                    lowerVal = ascendingHand[i] + 1;
                }
            }
        }
        // Make sure that the last card in the hand can be used
        if (!checkIfPossible(9, lower, hand[9], lowerVal)) {
            ascendingHand[9] = -1;
        }
        return ascendingHand;
    }

    private  boolean checkIfPossible(int hi, int lo, int higherVal, int lowerVal) 
    {
        // Checks if there are enough possible cards to put between the
        // lower valued card and higher valued card
        if (hi - lo > higherVal - lowerVal) {
            return false;
        }

        return true;
    }
    
    private boolean determineUse(int value, int[] ascHand) {
            int i = 0;
            int lo = 0;
            int lowerIndex = 0;
            int hi = 0;
            while (i < 10) 
            {
                // Look for cards that are to be kept and determine whether there are
                // cards that need to be replaced between the two
                int numOfCards = 0;
                if (ascHand[i] != -1) 
                {
                    hi = i;
                    numOfCards = hi - lowerIndex;
                    lowerIndex = i + 1;
                }
                else 
                {
                    if (i == 9) {
                        hi = 9;
                        numOfCards = hi + 1 - lowerIndex;
                    }
                }
                // Determine whether there are cards that need to be replaced between the
                // two cards and if the current card's value falls between the two cards'
                if (numOfCards != 0) 
                {
                    if (ascHand[lo] != -1 && ascHand[hi] != -1) {
                        if (withinRange(value, ascHand[hi], ascHand[lo])) {
                            return true;
                        }
                    }
                    // Check looking at the cards from 0 to the current card's value or the
                    // current card's value to 60
                    else if ((ascHand[lo] == -1 && withinRange(value, ascHand[hi], 0)) || 
                            (ascHand[hi] == -1 && withinRange(value, 61, ascHand[lo]))) {
                        return true;
                    }
                }

                // Update to check for the next range
                if (ascHand[i] != -1) lo = i;
                i++;
            }
            return false;
        }
        
         private  boolean withinRange(int drawCard, int hi, int lo) {
            if (drawCard < hi && drawCard > lo) {
                return true;
            }
            return false;
        }
        
        /*
         * Choose a position for the card to be placed. This is assuming that it has
         * already been determined that the card can be used. Look for the range it falls
         * in and place the card next to the card with the closer value.
         */
        private int choosePosition(int card, int[] bestAscendingHand) {
            int i = 0;
            int lo = 0;
            int hi = 0;
            while (i < 10) 
            {
                if (bestAscendingHand[i] != -1) {
                    hi = i;
                    if (lo == 0 && bestAscendingHand[lo] == -1) 
                    {
                        if (withinRange(card, bestAscendingHand[hi], 0)) 
                        {
                            // If the card is closer in value to the higher card,
                            // return the index of the card behind the higher card
                            if (bestAscendingHand[hi] - card < card) 
                            {
                                bestAscendingHand[hi - 1] = card;
                                return hi - 1;
                            }
                            else {
                                bestAscendingHand[0] = card;
                                return 0;
                            }
                        }
                    }
                    else 
                    {
                        if (withinRange(card, bestAscendingHand[hi], bestAscendingHand[lo])) {
                            // If the card is closer in value to the lower card,
                            // return the index of the card in front the lower card
                            if (bestAscendingHand[hi] - card < 
                                    card - bestAscendingHand[lo]) {
                                bestAscendingHand[hi - 1] = card;
                                return hi - 1;
                            }
                            else 
                            {
                                bestAscendingHand[lo + 1] = card;
                                return lo + 1;
                            }
                        }
                    }
                }
                // Determine whether the card should be in the last slot or closer to the
                // lower card
                else if (i == 9 && bestAscendingHand[9] == -1) 
                {
                    if (withinRange(card, 61, bestAscendingHand[lo])) 
                    {

                        if (60 - card < card - bestAscendingHand[lo]) 
                        {
                            bestAscendingHand[9] = card;
                            return 9;
                        }
                        else {
                            bestAscendingHand[lo + 1] = card;
                            return lo + 1;
                        }
                    }
                }

                // Update to check for the next range
                if (bestAscendingHand[i] != -1) lo = i;

                i++;
            }
            return -1;
        }

        private int chooseBestPossibleIndex(int card, int[] hand)
        {
            int champIndex = 0;
            int champDiff = hand[1] - card;
            if (champDiff < 0)
               champDiff = 61;
            int diff = card-hand[8];
            if (diff > 0 && diff < champDiff)
            {
                champIndex = 9;
                champDiff = diff;
            }
            for (int i=1; i<hand.length-1; i++)
            {
                if (hand[i-1]<card && card<hand[i+1])
                {
                    diff = Math.min(card-hand[i-1], hand[i+1]-card);
                    if (diff < champDiff) 
                    {
                        champIndex = i;
                        champDiff = diff;
                    }
                }
            }
            return champIndex;
        }
        
}
