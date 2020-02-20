/**
 * Question Class
 */
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
        return this.question;
    }

    public String getCorrectAnswer() {
        return this.correct_answer;
    }

    public String[] getIncorrectAnswers() {
        return incorrect_answers;
    }

    /**
     * Returns the category of the question
     * 
     * @return the category of the question
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the type of the question
     * 
     * @return the type of the question
     */
    public String getType() {
        return type;
    }

    /**
     * Creates an array consisting of the correct answer at index 0 and the 3
     * incorrect answers at index 1 to 3
     * 
     * @return a String array containg all answers
     */
    public String[] getAnswers() {
        String[] answers = new String[4];
        answers[0] = getCorrectAnswer();
        for (int i = 0; i < getIncorrectAnswers().length; i++) {
            answers[i + 1] = getIncorrectAnswers()[i];
        }
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
