package Dao;

public class APIData {

    private int response_code;
    private Question[] results;
    private Catagory[] trivia_categories;

    public Catagory[] getCatagorys() {
        return this.trivia_categories;
    }

    public int getResponseCode() {
        return this.response_code;
    }

    public Question[] getQuestions () {
        return this.results;
    }

}
