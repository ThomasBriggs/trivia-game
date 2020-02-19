package Dao;

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

    public String getQuestion() {
        return StringEscapeUtils.unescapeHtml4(this.question);
    }

    public String getCorrectAnswer() {
        return StringEscapeUtils.unescapeHtml4(this.correct_answer);
    }

    public String[] getIncorrectAnswers() {
        return incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String[] getAnswers() {
        String[] answers = new String[4];
        answers[0] = getCorrectAnswer();
        for (int i = 0; i < getIncorrectAnswers().length; i++) {
            answers[i + 1] = getIncorrectAnswers()[i];
        }
        return answers;
    }

    public String[] getAnswersSorted() {
        String[] answers = getAnswers();
        Arrays.sort(answers);
        return answers;
    }

    /**
     * Returns the Topic Area of the question as well as the difficulty in the form
     * of Topic Area - Difficulty
     * 
     * @return string of the question title
     */
    public String getQuestionTitle() {
        return getCategory() + " - " + getDifficulty().substring(0, 1).toUpperCase() + getDifficulty().substring(1);
    }

    @Override
    public String toString() {
        return getQuestion();
    }
}
