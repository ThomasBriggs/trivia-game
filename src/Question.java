import java.util.Arrays;

import org.apache.commons.text.StringEscapeUtils;

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
        return StringEscapeUtils.unescapeHtml4(correct_answer);
    }

    public void setCorrectAnswer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrectAnswers() {
        return incorrect_answers;
    }

    public void setIncorrectAnswers(String[] incorrect_answers) {
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
        for (int i = 0; i < getIncorrectAnswers().length; i++) {
            answers[i+1] = getIncorrectAnswers()[i];
        }
        return answers;
    }

    public String[] getAnswersSorted(){
        String[] answers = getAnswers();
        Arrays.sort(answers);
        return answers;
    }

    public String toString(String[] answers){
        String output;
        String questionString = getQuestion();
        String ans1 = answers[0];
        String ans2 = answers[1];
        String ans3 = answers[2];
        String ans4 = answers[3];
        output = String.format("%s\n1: %s\n2: %s\n3: %s\n4: %s",questionString, ans1, ans2, ans3, ans4);
        return StringEscapeUtils.unescapeHtml4(output);
    }
}
