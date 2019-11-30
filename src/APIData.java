public class APIData {

    private int response_code;
    private Question[] results;

    public int getResponseCode(){
        return this.response_code;
    }

    public Question[] getQuestions ()
    {
        return this.results;
    }

}
