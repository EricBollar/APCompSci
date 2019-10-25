public class HangmanGame
{
    private String lettersGuessed;
    private String actualAnswer;
    private int guessesLeft;
    
    public HangmanGame() {
        lettersGuessed = "";
        actualAnswer = RandomWordGenerator.getRandomWord();
        guessesLeft = 7;
    }
    
    public boolean makeGuess(String letter) {
        lettersGuessed += letter;
        if (actualAnswer.indexOf(letter) == -1) {
            guessesLeft--;
            return false;
        }
        return true;
    }
    
    public String getActualAnswer() {
        return actualAnswer;
    }
    
    public String getPuzzle() {
        String result = "";
        for (int i = 0; i < actualAnswer.length(); i++) {
            String letter = actualAnswer.substring(i, i + 1);
            if (lettersGuessed.indexOf(letter) == -1) {
                result += "_";
            } else {
                result += letter;
            }
        }
        return result;
    }
    
    public int getGuessesLeft() {
        return guessesLeft;
    }
    
    public String getLettersGuessed() {
        return lettersGuessed;
    }
}
