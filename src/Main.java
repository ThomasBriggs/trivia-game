import com.google.gson.Gson;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //mount=1&category=9&difficulty=easy&type=multiple
        String json = Trivia.get("amount=3", "category=9", "difficulty=hard", "type=multiple");
        Question[] questions = new Gson().fromJson(json, APIData.class).getQuestions();
        for (Question question : questions) {
            System.out.println(question);
            System.out.println();
            System.out.println(question.getCorrect_answer());
            System.out.println();
        }
    }
}