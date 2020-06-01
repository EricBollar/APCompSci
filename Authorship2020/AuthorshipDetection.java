import acm.program.*;
import java.util.ArrayList;

// Eric Bollar
// AP CS
// 10 May 2020

public class AuthorshipDetection extends ConsoleProgram
{
    private static final String PUNCTUATION = "'!\",;:.-?)([]<>*#\n\t\r ";
    private static final double[] WEIGHT = {11.0, 33.0, 50.0, 0.4, 4.0};
    private AuthorSignature[] authors;

    public void run()
    {
        loadAuthorSignatures();

        loadAuthorSignatures();
        String filename = readLine("Enter file name: ");
        String fileContents = FileHelper.getFileContents(filename);
        println("Loading..."); println();
        
        ArrayList<String> sentences = getSentencesFromContents(fileContents);
        ArrayList<String> words = getAllWordsFromSentences(sentences);
        double avgWordLength = computeAverageWordLength(words);
        double diffWordRatio = computeDifferentWordRatio(words);
        double hapaxLegoRatio = computeHapaxLegomannaRatio(words);
        double avgWordsPerSentence = computeAverageWordsPerSentence(sentences);
        double sentenceComplexity = computeSentenceComplexity(sentences);
        AuthorSignature unknownSignature = new AuthorSignature("Unknown", avgWordLength, diffWordRatio, hapaxLegoRatio, avgWordsPerSentence, sentenceComplexity); 
        
        println("Scores");
        ArrayList<Double> scores = new ArrayList<Double>();
        for (int i = 0; i < authors.length - 1; i++) {
            println(authors[i].getAuthorName() + ": " + computeScore(unknownSignature, authors[i]));
            scores.add(computeScore(unknownSignature, authors[i]));
        }
        
        println();
        println("Closest Author: " + detectAuthorship(scores).getAuthorName());
    }

    private ArrayList<String> getSentencesFromContents(String fileContents) {
        ArrayList<String> result = new ArrayList<String>();
        int sentenceStart = 0;
        for (int i = 0; i < fileContents.length() - 1; i++) {
            if (fileContents.substring(i, i+1).equals(".") ||
            fileContents.substring(i, i+1).equals("?") ||
            fileContents.substring(i, i+1).equals("!")) {
                String s = fileContents.substring(sentenceStart, i+1);
                result.add(s);
                sentenceStart = i + 1;
            }
        }
        return result;
    }

    private ArrayList<String> getWordsFromSentence(String sentence) {
        ArrayList<String> words = new ArrayList<String>();
        int start = 0;
        if (sentence.substring(0, 1).equals(" ")) {
            start = 1;
        }
        int wordStart = start;
        for (int i = start; i < sentence.length() - 1; i++) {
            if (sentence.substring(i, i+1).equals(" ")) {
                String word = sentence.substring(wordStart, i+1);
                words.add(word);
                wordStart = i + 1;
            }
        }
        words.add(sentence.substring(wordStart, sentence.length()));
        return words;
    }

    private ArrayList<String> getAllWordsFromSentences(ArrayList<String> sentences) {
        ArrayList<String> allWords = new ArrayList<String>();
        for (String s : sentences) {
            ArrayList<String> currWords = getWordsFromSentence(s);
            for (String currWord : currWords) {
                String c = clean(currWord);
                if (!c.equals(""))
                    allWords.add(c);
            }
        }
        return allWords;
    }

    private String clean(String word) {
        String out = word;
        while (isPunctuation(out.substring(0, 1))) {
            if (out.length() <= 1) // accounts for if word is comprised solely of punctuation
                return "";
            out = out.substring(1, out.length());
        }
        while (isPunctuation(out.substring(out.length() - 1, out.length()))) {
            out = out.substring(0, out.length() - 1);
        }
        out.toLowerCase();
        return out;
    }

    private boolean isPunctuation(String c) {
        for (int i = 0; i < PUNCTUATION.length(); i++) {
            if (c.equals(PUNCTUATION.substring(i, i+1)))
                return true;
        }
        return false;
    }

    private double computeAverageWordLength(ArrayList<String> words) {
        double totalLength = 0.0;
        int numWords = words.size();
        for (String word : words) {
            totalLength += word.length();
        }
        return totalLength / numWords;
    }

    private double computeDifferentWordRatio(ArrayList<String> words) {
        ArrayList<String> uniqueWords = new ArrayList<String>();
        for (String word : words) {
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }
        return 1.0 * uniqueWords.size() / words.size();
    }

    private double computeHapaxLegomannaRatio(ArrayList<String> words) {
        ArrayList<String> seenOnce = new ArrayList<String>();
        ArrayList<String> seenTwice = new ArrayList<String>();
        for (String word : words) {
            if (!seenOnce.contains(word)) {
                seenOnce.add(word);
            } else if (!seenTwice.contains(word)) {
                seenTwice.add(word);
            }
        }
        ArrayList<String> exactlyOnce = new ArrayList<String>();
        for (String word : seenOnce) {
            if (!seenTwice.contains(word)) {
                exactlyOnce.add(word);
            }
        }
        return 1.0 * exactlyOnce.size() / words.size();
    }

    private double computeAverageWordsPerSentence(ArrayList<String> sentences) {
        int numWords = 0;
        for (String sentence : sentences) {
            numWords += getWordsFromSentence(sentence).size();
        }
        return 1.0 * numWords / sentences.size();
    }

    private double computeSentenceComplexity(ArrayList<String> sentences) {
        int numPhrases = sentences.size(); // every sentence has at least 1 phrase
        for (String sentence : sentences) {
            for (int i = 0; i < sentence.length() - 1; i++) {
                if (sentence.substring(i, i+1).equals(":") ||
                sentence.substring(i, i+1).equals(";") ||
                sentence.substring(i, i+1).equals(",")) {
                    numPhrases++;
                }
            }
        }
        return 1.0 * numPhrases / sentences.size();
    }

    private double computeScore(AuthorSignature first, AuthorSignature second) {
        double score = 0.0;
        score += Math.abs(first.getAverageWordLength() - second.getAverageWordLength()) * WEIGHT[0];
        score += Math.abs(first.getDifferentWordRatio() - second.getDifferentWordRatio()) * WEIGHT[1];
        score += Math.abs(first.getHapaxRatio() - second.getHapaxRatio()) * WEIGHT[2];
        score += Math.abs(first.getAverageWordsPerSentence() - second.getAverageWordsPerSentence()) * WEIGHT[3];
        score += Math.abs(first.getAveragePhrasesPerSentence() - second.getAveragePhrasesPerSentence()) * WEIGHT[4];
        return score;
    }
    
    private AuthorSignature detectAuthorship(ArrayList<Double> scores) {
        Double lowScore = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) < lowScore) {
                index = i;
                lowScore = scores.get(i);
            }
        }
        return authors[index];
    }

    // I wrote this method for you
    private void loadAuthorSignatures()
    {
        authors = new AuthorSignature[13];
        authors[0] = new AuthorSignature("Agatha Christie", 4.40212537354, 0.103719383127, 0.0534892315963, 10.0836888743, 1.90662947161);
        authors[1] = new AuthorSignature("Alexandre Dumas", 4.38235547477, 0.049677588873, 0.0212183996175, 15.0054854981, 2.63499369483);
        authors[2] = new AuthorSignature("Brothers Grimm", 3.96868608302, 0.0529378997714, 0.0208217283571, 22.2267197987, 3.4129614094);
        authors[3] = new AuthorSignature("Charles Dickens", 4.34760725241, 0.0803220950584, 0.0390662700499, 16.2613453121, 2.87721723105);
        authors[4] = new AuthorSignature("Douglas Adams", 4.33408042189, 0.238435104414, 0.141554321967, 13.2874354561, 1.86574870912);
        authors[5] = new AuthorSignature("Emily Bronte", 4.35858972311, 0.089662598104, 0.0434307152651, 16.1531664212, 2.93439550141);
        authors[6] = new AuthorSignature("Fyodor Dostoevsky", 4.34066732195, 0.0528571428571, 0.0233414043584, 12.8108273249, 2.16705364781);
        authors[7] = new AuthorSignature("James Joyce", 4.52346300961, 0.120109917189, 0.0682315429476, 10.9663296918, 1.79667373227);
        authors[8] = new AuthorSignature("Jane Austen", 4.41553119311, 0.0563451817574, 0.02229943808, 16.8869087498, 2.54817097682);
        authors[9] = new AuthorSignature("Lewis Caroll", 4.22709528497, 0.111591342227, 0.0537026953444, 16.2728740581, 2.86275565124);
        authors[10] = new AuthorSignature("Mark Twain", 4.33272222298, 0.117254215021, 0.0633074228159, 14.3548573631, 2.43716268311);
        authors[11] = new AuthorSignature("Sir Arthur Conan Doyle", 4.16808311494, 0.0822989796874, 0.0394458485444, 14.717564466, 2.2220872148);
        authors[12] = new AuthorSignature("William Shakespeare", 4.16216957834, 0.105602561171, 0.0575348730848, 9.34707371975, 2.24620146314);
    }

}
