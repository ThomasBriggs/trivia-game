import java.util.Arrays;

public class Question {

    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
    private String category;
    private String type;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getAnswers(){
        String[] answers = new String[4];
        answers[0] = getCorrect_answer();
        for (int i = 0; i < getIncorrect_answers().length; i++) {
            answers[i+1] = getIncorrect_answers()[i];
        }
        return answers;
    }

    public String[] getAnswersSorted(){
        String[] answers = getAnswers();
        Arrays.sort(answers);
        return answers;
    }

    public String toString(){
        String questionString = getQuestion();
        String ans1 = getAnswersSorted()[0];
        String ans2 = getAnswersSorted()[1];
        String ans3 = getAnswersSorted()[2];
        String ans4 = getAnswersSorted()[3];
        return String.format("%s\n1: %s\n2: %s\n3: %s\n4: %s",questionString, ans1, ans2, ans3, ans4);
    }
}
