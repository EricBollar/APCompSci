
// Eric Bollar
// AP CS
// 6 February 2020

public class PresidentQuestion implements Question
{
    private String place;
    private String name;

    public PresidentQuestion()
    {
        String[] places = {"1st", "2nd", "3rd", "4th", "5th"};
        String[] names = {"George Washington", "John Adams", "Thomas Jefferson", "James Madison", "James Monroe"};
        int index = (int)(Math.random() * places.length);
        place = places[index];
        name = names[index];
    }

    public String getQuestion() {
        return "Who was the " + place + " president of the US?";
    }

    public String getAnswer() {
        return name;
    }

    public boolean isCorrect(String response) {
        return response.equals(name);
    }
}
